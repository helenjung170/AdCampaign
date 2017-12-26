package com.ad.campaign.server;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Map;

public class CampaignService {

	
	CampaignDao campaignDao;
	
	// constructor
	public CampaignService() {
		campaignDao = CampaignDao.instance;
	}

	/**
	 * Add a new campaign into the CampaignDao campaign list
	 * 
	 * @param campaign
	 */
	public void createCampaign(Campaign campaign) {
		Map<String, Campaign> campaigns = campaignDao.getCampaigns();
		campaigns.put(campaign.getPartnerId(), campaign);
	}
	/**
	 * Get campaign from campaignDao campaign list with associated
	 * partner id.
	 * 
	 * @param partnerId
	 * @return campaign
	 */
	public Campaign getCampaign(String partnerId) {
		return campaignDao.getCampaigns().get(partnerId);
	}
	
	/**
	 * Get a list of campaign
	 * 
	 * @return campaign list
	 */
	public List<Campaign> getCampaignList() {
		List<Campaign> campaignList = new ArrayList<Campaign>();
		campaignList.addAll(campaignDao.getCampaigns().values());
		return campaignList;
	}

	/**
	 * Check whether the campaign exist for a 
	 * given partner id.
	 * 
	 * @param partnerId
	 * @return
	 */
	public boolean campaignExist (String partnerId) {
		if(campaignDao.getCampaigns().get(partnerId) != null) {
			return true;
		}
		return false;
	}
	
	/**
	 * Check whether a campaign that a given partner id is
	 * active or not. A campaign is active is the current time
	 * is before the duration time + the creation time.
	 * 
	 * @param partnerId
	 * 
	 * @return true or false
	 */
	public boolean isActiveCampaign (String partnerId) {
		Campaign campaign = campaignDao.getCampaigns().get(partnerId);
		if(campaign != null) {
			Timestamp creationTime =  campaign.getCreationTime();
			Calendar cal = Calendar.getInstance();
		    cal.setTimeInMillis(creationTime.getTime());
		 
		    // add the duration time
		    cal.add(Calendar.SECOND, campaign.getDuration());
		    Timestamp expirationTime = new Timestamp(cal.getTime().getTime());
		    
			if(expirationTime.before(new Timestamp(System.currentTimeMillis()))) {
				return false;
			}
		}
		else {
			return false;
		}
		return true;
	}
}
