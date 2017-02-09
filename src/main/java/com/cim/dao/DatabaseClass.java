package com.cim.dao;

import java.util.HashMap;
import java.util.Map;

import com.cim.beans.Advertisment;

public class DatabaseClass {

	private static Map<String, Advertisment> ads = new HashMap<> ();
	
	static{
		
		Advertisment ad1 = new Advertisment(new Long(ads.size() + 1), "1", 10, "ad content from first partner");
		Advertisment ad2 = new Advertisment(new Long(ads.size() + 1), "2", 20, "ad content from Second partner");
		
		ads.put(ad1.getPartner_id(),ad1 );
		ads.put(ad2.getPartner_id(),ad2 );
	}

	public static Map<String, Advertisment> getAdvertisments() {
		return ads;
	}

}
