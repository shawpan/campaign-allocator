package com.oo.campaignallocator.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.ArrayList;
import com.oo.campaignallocator.core.Campaign;

public class CampaignAllocationRequest {
    private int monthlyInventory;
    private ArrayList<Campaign> campaigns;

    public CampaignAllocationRequest() {
        // Jackson deserialization
        this.campaigns = new ArrayList<Campaign>();
    }

    public int getMonthlyInventory() {
        return monthlyInventory;
    }

    public ArrayList<Campaign> getCampaigns() {
        return campaigns;
    }

    public void setMonthlyInventory(int monthlyInventory) {
        this.monthlyInventory = monthlyInventory;
    }

    public void setCampaigns(ArrayList<Campaign> campaigns) {
        this.campaigns = new ArrayList<Campaign>(campaigns);
    }
}
