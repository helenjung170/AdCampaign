package com.ad.campaign.server;

import java.sql.Timestamp;
import java.util.Calendar;

import javax.xml.bind.annotation.XmlRootElement;

/**
 * 
 * This is the class for campaign object which contains the following:
 * partner id as String
 * duration as integer in 
 * content as string
 *
 */
@XmlRootElement
public class Campaign {
	private String partnerId;
	private int duration;
	private String content;
	private Timestamp creationTime;
	
	// Default constructor
	public Campaign() {

	}
	/**
	 * Constructor with parameters
	 * 
	 * @param partnerId
	 * @param duration
	 * @param content
	 */
	public Campaign(String partnerId, int duration, String content) {
		super();
		this.partnerId= partnerId;
		this.duration = duration;
		this.content = content;
		this.creationTime= new Timestamp(System.currentTimeMillis());
	}
	
	/**
	 * Getter for partner id
	 * 
	 * @return partner id
	 */
	public String getPartnerId() {
		return partnerId;
	}
	
	/**
	 * Setter for partner id
	 * 
	 * @param partnerId
	 */
	public void setPartnerId(String partnerId) {
		this.partnerId = partnerId;
	}

	/**
	 * Getter for duration
	 * 
	 * @return duration
	 */
	public int getDuration() {
		return duration;
	}
	
	/**
	 * Setter for duration
	 * 
	 * @param duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Getter for content
	 * 
	 * @return content
	 */
	public String getContent() {
		return content;
	}
	
	/**
	 * Setter for content
	 * 
	 * @param content
	 */
	public void setContent(String content) {
		this.content = content;
	}
    
	/**
	 * Getter for creation time
	 * 
	 * @return creation
	 */
	public Timestamp getCreationTime() {
		return creationTime;
	}
	
	/**
	 * Setter for creation time
	 * 
	 * @param creationTime
	 */
	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	
}