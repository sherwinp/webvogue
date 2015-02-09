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
@Path("/payMent")
@Produces(MediaType.APPLICATION_JSON)
public Response setPayment(@Context HttpServletRequest request){
	
	ResponseBuilder rsBuilder = Response.status(Response.Status.OK);
	
	HttpSession session = request.getSession(false);
	if( session != null )
		log.info(String.format("%s", session.getId()));
	
	Enumeration<String> names = request.getParameterNames();
	for(;names.hasMoreElements();){
		Object o = names.nextElement();
		log.info(String.format("%s", o));
	}
	log.info(String.format("querystring: %s", request.getQueryString()));
	int beginIndex = request.getQueryString().lastIndexOf("jsessionid=");
	String sessionString = request.getQueryString().substring(beginIndex + 11, request.getQueryString().length());
	log.info(String.format("sessionstring: %s", sessionString));
	HttpSession userSession = HttpSessionCollector.find(sessionString);
	if( userSession != null )
		log.info(String.format(" User Session: %s", userSession.getId()));
	
	return rsBuilder.entity(new String("Accepted")).build();
}
}