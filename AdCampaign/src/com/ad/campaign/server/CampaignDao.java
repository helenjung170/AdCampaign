package com.ad.campaign.server;

import java.util.HashMap;
import java.util.Map;

/**
 * This is the Dao for storing the list
 * of campaigns. 
 * 
 *
 */
public enum CampaignDao {
	instance;

	/**
	 * Map of the stored campaign list
	 */
	private Map<String, Campaign> campaigns = new HashMap<String, Campaign>();

	/**
	 * Default Constructor
	 */
	private CampaignDao() {

	}

	/**
	 * Get the map of the stored campaign list
	 * 
	 * @return campaign
	 */
	public Map<String, Campaign> getCampaigns() {
		return campaigns;
	}

}
