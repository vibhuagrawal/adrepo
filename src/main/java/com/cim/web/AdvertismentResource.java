package com.cim.web;


import java.util.List;
import java.util.Map;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cim.beans.Advertisment;
import com.cim.services.AdvertismentService;


@Path("/ad")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AdvertismentResource {

	AdvertismentService adservice = new AdvertismentService();
	
	@GET
	public List<Advertisment> getAllAds()
	{
		return adservice.getAllAds();
		
	}
	
	@GET
	@Path("/{partner_id}")
	public Response getAd(@PathParam("partner_id") String partner_id)
	{
		 Advertisment newAd =  adservice.getAd(partner_id);
		 return Response.status(Status.OK)
					.entity(newAd)
					.build();
	}
	
	@POST
	public Response CreateAd(Advertisment ad)
	{
		Advertisment newAd = adservice.CreateAd(ad);
		return Response.status(Status.CREATED)
				.entity(newAd)
				.build();
	}
}
