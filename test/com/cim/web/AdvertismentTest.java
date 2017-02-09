package com.cim.web;

import static org.junit.Assert.assertEquals;

import javax.ws.rs.Consumes;
import javax.ws.rs.Produces;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.test.JerseyTest;
import org.glassfish.jersey.test.TestProperties;
import org.junit.Test;

import com.cim.beans.Advertisment;


@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)

public class AdvertismentTest extends JerseyTest {
	
	@Override
    protected Application configure() {
		enable(TestProperties.LOG_TRAFFIC);
		enable(TestProperties.DUMP_ENTITY);
		return new ResourceConfig(AdvertismentResource.class);
    }

	private Advertisment PopulateAdObject(String partner_id, int duration, String ad_content)
	{
		
		Advertisment ad = new Advertisment();
		ad.setPartner_id(partner_id);
		ad.setDuration(duration);
		ad.setAd_content(ad_content);
		return ad ;
	}
	
	@Test()
	public void PostAd()
	{
		Advertisment ad = this.PopulateAdObject("Partner1", 1, "this ad will expire in 1 second");
		
		Response response = target("/ad")
				.request()
				.post(Entity.entity(ad, MediaType.APPLICATION_JSON));
		
		assertEquals("ad should have been created"
				, Status.CREATED.getStatusCode(),response.getStatus());
				
	}
	
	@Test
	public void GetAd()
	{
		final Advertisment ad =  target("/ad/1").request().get(Advertisment.class);
		assertEquals("Should return PartnerID 1 ","1", ad.getPartner_id());
	}
	
	@Test
	public void CreateMultipileActiveAdsforSamePartner()
	{
		
		Advertisment ad = this.PopulateAdObject("PartnerX", 10, "First Active ad for PartnerX");
		
		Response response = target("/ad")
				.request()
				.post(Entity.entity(ad, MediaType.APPLICATION_JSON));
		
		assertEquals("First Active ad for PartnerX should have been created"
				, Status.CREATED.getStatusCode(),response.getStatus());
		
		Advertisment ad2 = this.PopulateAdObject("PartnerX", 20, "Second Active ad for PartnerX");
		
		Response response2 = target("/ad")
				.request()
				.post(Entity.entity(ad2, MediaType.APPLICATION_JSON));
		
		assertEquals("First Active ad for PartnerX should have been created"
				, Status.NOT_ACCEPTABLE.getStatusCode(),response2.getStatus());
		
	}
	

}
