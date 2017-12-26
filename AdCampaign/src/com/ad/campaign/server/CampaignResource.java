
package com.ad.campaign.server;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import org.json.simple.JSONObject;

/**
 * This is the REST EndPoint class. 
 * Can be access through $host/ad
 *
 */
@Path("/ad")
public class CampaignResource {

	CampaignService campaignService;

	/**
	 * Constructor to create a new instance of campaign service
	 * 
	 */
	public CampaignResource() {	
		campaignService = new CampaignService();
	}
	
	/**
	 * Get the list of campaigns
	 * 
	 * @return List of campaigns
	 */
	@GET
	@Produces({MediaType.APPLICATION_JSON })
	public List<Campaign> getCampaigns() {
		return campaignService.getCampaignList();
	}
    
	/**
	 * Get the campaign that is has
	 * the given partner id.
	 * 
	 * @param partnerId
	 * 
	 * @return campaign that has the parterId
	 */
	@GET
	@Path("{partnerId}")
	@Produces({MediaType.APPLICATION_JSON })
	public Campaign getCampaign(@PathParam("partnerId") String partnerId) {
		Campaign campaign =campaignService.getCampaign(partnerId);
		if(campaign != null) {
			if(!campaignService.isActiveCampaign(partnerId)) {
				campaign.setDuration(0);
				campaign.setContent("Error: No active campaign exist for partner id: " + partnerId + " ErrorCode: " + Response.Status.NOT_FOUND);
			}
		}
		return campaign;
	}

	/**
	 * Create a campaign from the RESTFUL POST, add
	 * the campaign into the campaign list if it is not
	 * a campaign of a same partner or it is not active.
	 * Else it will return an error message.
	 * 
	 * @param partnerId
	 * @param duration
	 * @param content
	 * @param servletResponse
	 * @return
	 * @throws IOException
	 */
	@POST
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON )
	public Campaign createCampaign(Campaign inputCampaign,
			@Context HttpServletResponse servletResponse) throws IOException {
		String partnerId = inputCampaign.getPartnerId();
		int duration = inputCampaign.getDuration();
		String content = inputCampaign.getContent();
		if (!campaignService.campaignExist(partnerId) || (campaignService.campaignExist(partnerId) && !campaignService.isActiveCampaign(partnerId))) {
			Campaign campaign = new Campaign(partnerId, duration, content);
			campaignService.createCampaign(campaign);
			servletResponse.sendRedirect("ad/");
		}
		else {
				Campaign campaign = new Campaign(partnerId, 0, "Error: An active campaign exist for partner id: " + partnerId + " " + Response.Status.FOUND);
				return campaign;	
		}
		return null;
	}

}