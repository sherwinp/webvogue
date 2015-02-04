package com.niksoft.ws;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import com.niksoft.dao.PayDAService;

@Path("/pay")
public class PayForItem  {

	PayDAService dao = new PayDAService();
	
	public PayForItem(){
		
	}
	
@GET
@Produces(MediaType.APPLICATION_JSON)
public Response getPayments() {
	ResponseBuilder rsBuilder = Response.status(Response.Status.OK);
	
	return rsBuilder.entity(dao.findAll()).build();
}
@GET
@Path("/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response getPayment(@PathParam("id") int paymentId){
	ResponseBuilder rsBuilder = Response.status(Response.Status.OK);
	
	// find id
	
	return rsBuilder.entity(new Object()).build();
	
}
@PUT
@Path("/{id}")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public Response setPayment(@PathParam("id") int voucherId){
	return Response.status(Response.Status.CREATED).build();
}
}