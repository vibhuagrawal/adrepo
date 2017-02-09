package com.cim.services;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.ws.rs.NotAcceptableException;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.cim.beans.Advertisment;
import com.cim.dao.DatabaseClass;
import com.cim.exception.AdvertismentExpiredException;
import com.cim.exception.DataNotFoundExecption;

public class AdvertismentService {
	
	private Map<String,Advertisment> ads = DatabaseClass.getAdvertisments();
	
	public List<Advertisment> getAllAds() {
		return new ArrayList<>(ads.values());
	}
	
	public Advertisment CreateAd(Advertisment ad){
		Advertisment ExistingAd = ads.get(ad.getPartner_id());
		if(ExistingAd != null)
		{
			long timeDifference = (new Date().getTime() - ExistingAd.getCreation_time().getTime())/1000;
			if(timeDifference < ad.getDuration())
			{
				throw new NotAcceptableException("Only one active campaign can exist for a given partner");				
			}
		}
		ad.setAd_id(new Long(ads.size() + 1));
		ads.put( ad.getPartner_id(), ad);
		return ad;
	}

	public Advertisment getAd(String partner_id) {
		Advertisment ad = ads.get(partner_id);
		if(ad == null)
			throw new DataNotFoundExecption( "no ad Campaign exist for Partner_id " + partner_id);
		
		long timeDifference = (new Date().getTime() - ad.getCreation_time().getTime())/1000;
		if(timeDifference > ad.getDuration())
			throw new AdvertismentExpiredException( "ad campaign with Partner_id " + partner_id + " has been expired" );
		return ad;
	}

}
