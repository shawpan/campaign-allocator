package com.oo.campaignallocator.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.ArrayList;
import com.oo.campaignallocator.core.Campaign;

/**
 * CampaignAllocationResponse represents the structure of output object
 * @author Akiz
 */
public class CampaignAllocationResponse {
    /**
     * Holds the total number of impressions that can be served with maximum
     * revenue
     */
    private long totalImpressions;

    /**
     * Holds the total revenue amount that can be obtained
     */
    private long totalRevenue;

    /**
     * Holds the campaigns with computed frequency information
     */
    private List<Campaign> campaigns;

    /**
     * Creates campaign allocation response object for final output
     */
    public CampaignAllocationResponse() {
        campaigns = new ArrayList<Campaign>();
        totalRevenue = 0;
        totalImpressions = 0;
    }

    /**
     * Set campaigns to the output object
     * @param campaigns list of campaign objects
     */
    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    /**
     * Get campaign objects
     * @return campaigns list of campaign and a field in final json response
     */
    @JsonProperty
    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    /**
     * Get total impressions that can be served with maximum revenue
     * @return totalImpressions total impressions and a field in final json response
     */
    @JsonProperty
    public long getTotalImpressions() {
        return totalImpressions;
    }

    /**
     * Set total impressions
     * @param totalImpressions total number of impressions that can be served
     */
    public void setTotalImpressions(long totalImpressions) {
        this.totalImpressions = totalImpressions;
    }

    /**
     * Get total revenue that can be obtained
     * @return totalRevenue total revenue and a field in final json response
     */
    @JsonProperty
    public long getTotalRevenue() {
        return totalRevenue;
    }

    /**
     * Set total revenue
     * @param totalRevenue total revenue that can be obtained
     */
    public void setTotalRevenue(long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
