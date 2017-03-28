package com.oo.campaignallocator.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.ArrayList;
import com.oo.campaignallocator.core.Campaign;

/**
 * CampaignAllocationRequest represents the structure of input object
 * @author Akiz
 */
public class CampaignAllocationRequest {
    /**
     * Holds the number of available impressions for the month
     */
    private int monthlyInventory;

    /**
     * Holds the campaign imputs
     */
    private ArrayList<Campaign> campaigns;

    /**
     * Creates the campaign allocation input object
     */
    public CampaignAllocationRequest() {
        this.campaigns = new ArrayList<Campaign>();
    }

    /**
     * Gets the available impressions for the month
     * @return monthlyInventory
     */
    public int getMonthlyInventory() {
        return monthlyInventory;
    }

    /**
     * Gets campaign objects
     * @return campaigns list of Campaign object
     */
    public ArrayList<Campaign> getCampaigns() {
        return campaigns;
    }

    /**
     * Sets available impressions for the month
     * @param monthlyInventory inventory for the month
     */
    public void setMonthlyInventory(int monthlyInventory) {
        this.monthlyInventory = monthlyInventory;
    }

    /**
     * Sets campaigns to the input object
     * @param campaigns list of campaign object
     */
    public void setCampaigns(ArrayList<Campaign> campaigns) {
        this.campaigns = new ArrayList<Campaign>(campaigns);
    }
}
