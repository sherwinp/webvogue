package com.niksoft.ws;

import java.util.Enumeration;
import java.util.logging.Logger;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Request;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.niksoft.dao.PayDAService;
import com.niksoft.dao.User;
import com.niksoft.dao.UserDAService;
import com.niksoft.dao.VoucherDAService;
import com.niksoft.web.HttpSessionCollector;

@Path("/pay")
public class PayForItem  {
	
	private static final Logger log = Logger.getLogger(PayForItem.class.getName());
	
	PayDAService dao = new PayDAService();
	
	public PayForItem(){
		
	}
	
@GET
@Path("/findAllForVendor")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response getPayment(@Context HttpServletRequest request){
	
	ResponseBuilder rsBuilder = Response.status(Response.Status.OK);
	
	// find vendor session
	HttpSession session = request.getSession(false);
	if( session != null )
		log.info(String.format("%s", session.getId()));
	VoucherDAService service = new VoucherDAService();
	
	return rsBuilder.entity(service.findAllForVendor()).build();
	
}
@POST
@Path("/payMent/{item}/{userdsessionid}")
@Produces(MediaType.APPLICATION_JSON)
public Response setPayment(@PathParam("item") int itemId, @PathParam("userdsessionid") String usersessionString, @Context HttpServletRequest request) throws Exception{
	
	ResponseBuilder rsBuilder = Response.status(Response.Status.OK);
	
	log.info(String.format("User selected ItemId: %s", itemId));
	
	log.info(String.format("User sessionString to find: %s", usersessionString));
	
	HttpSession session = request.getSession(false);
	if( session != null )
		log.info(String.format("Vendor jsessionid: %s", session.getId()));
	else throw new Exception("Argument Expected.");
	
	HttpSession userSession = HttpSessionCollector.find(usersessionString);
	if( userSession != null )
		log.info(String.format(" User Session: %s", userSession.getId()));
	else throw new Exception("Argument Expected.");
	
	User vendor= (User) session.getAttribute("user");
	User user = (User) userSession.getAttribute("user");
	
	VoucherDAService service = new VoucherDAService();
	service.claimVoucher(itemId, user.getId(), vendor.getId());
	
	return rsBuilder.entity(new String("Accepted")).build();
}
}