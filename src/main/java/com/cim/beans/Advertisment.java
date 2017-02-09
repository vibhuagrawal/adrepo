package com.cim.beans;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
@XmlRootElement
public class Advertisment {
	
	private long ad_id;
	private String partner_id;
	private int duration;
	private String ad_content;
	private Date creation_time;
	
	public Advertisment() { this.creation_time = new Date();}
	
	public Advertisment(long ad_id, String partner_id, int duration, String ad_content) {
		this.ad_id = ad_id;
		this.partner_id = partner_id;
		this.duration = duration;
		this.ad_content = ad_content;
		this.creation_time = new Date();
	}
	
	public String getPartner_id() {
		return partner_id;
	}
	public void setPartner_id(String partner_id) {
		this.partner_id = partner_id;
	}
	public int getDuration() {
		return duration;
	}
	public void setDuration(int duration) {
		this.duration = duration;
	}
	public String getAd_content() {
		return ad_content;
	}
	public void setAd_content(String ad_content) {
		this.ad_content = ad_content;
	}
	public long getAd_id() {
		return ad_id;
	}
	public void setAd_id(long ad_id) {
		this.ad_id = ad_id;
	}
	
	public Date getCreation_time() {
		return creation_time;
	}

	public void setCreation_time(Date creation_time) {
		this.creation_time = creation_time;
	}
	
}
