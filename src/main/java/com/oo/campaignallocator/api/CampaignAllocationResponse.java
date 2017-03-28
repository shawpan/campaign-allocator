package com.oo.campaignallocator.api;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.base.MoreObjects;
import java.util.List;
import java.util.ArrayList;
import com.oo.campaignallocator.core.Campaign;

public class CampaignAllocationResponse {
    private long totalImpressions;
    private long totalRevenue;
    private List<Campaign> campaigns;

    public CampaignAllocationResponse() {
        campaigns = new ArrayList<Campaign>();
    }

    public CampaignAllocationResponse(long totalImpressions, long totalRevenue) {
        this.totalImpressions = totalImpressions;
        this.totalRevenue = totalRevenue;
    }

    public void setCampaigns(List<Campaign> campaigns) {
        this.campaigns = campaigns;
    }

    @JsonProperty
    public List<Campaign> getCampaigns() {
        return campaigns;
    }

    @JsonProperty
    public long getTotalImpressions() {
        return totalImpressions;
    }

    public void setTotalImpressions(long totalImpressions) {
        this.totalImpressions = totalImpressions;
    }

    @JsonProperty
    public long getTotalRevenue() {
        return totalRevenue;
    }

    public void setTotalRevenue(long totalRevenue) {
        this.totalRevenue = totalRevenue;
    }
}
