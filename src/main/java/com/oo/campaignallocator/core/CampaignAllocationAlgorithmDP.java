package com.oo.campaignallocator.core;

import com.oo.campaignallocator.api.CampaignAllocationRequest;
import com.oo.campaignallocator.api.CampaignAllocationResponse;
import java.lang.Math;
import java.util.ArrayList;

public class CampaignAllocationAlgorithmDP implements CampaignAllocationAlgorithmInterface {
    private CampaignAllocationResponse campaignAllocationResponse;

    public CampaignAllocationAlgorithmDP() {
        this.campaignAllocationResponse = new CampaignAllocationResponse();
    }

    private void prepareResponse(CampaignAllocationAlgorithmDPStore dpStore, CampaignAllocationRequest campaignAllocationRequest) {
        campaignAllocationResponse = new CampaignAllocationResponse();
        ArrayList<Campaign> campaigns = campaignAllocationRequest.getCampaigns();
        int totalCampaigns = campaigns.size();
        long totalImpressions = 0;
        int monthlyInventory = campaignAllocationRequest.getMonthlyInventory();
        campaignAllocationResponse.setTotalRevenue(dpStore.dpVector[monthlyInventory]);
        for (int inventory = monthlyInventory; inventory > 0; --inventory) {
            if(dpStore.pickedElementIndex[inventory] >= 0) {
                Campaign campaign = campaigns.get(dpStore.pickedElementIndex[inventory]);
                campaign.incrementCount();
                totalImpressions += campaign.getImpressions();
                inventory -= (campaign.getImpressions() + 1);
            }
        }
        campaignAllocationResponse.setTotalImpressions(totalImpressions);
        campaignAllocationResponse.setCampaigns(campaigns);
    }

    public void run(CampaignAllocationRequest campaignAllocationRequest) {
        ArrayList<Campaign> campaigns = campaignAllocationRequest.getCampaigns();
        int totalCampaigns = campaigns.size();
        int monthlyInventory = campaignAllocationRequest.getMonthlyInventory();
        CampaignAllocationAlgorithmDPStore dpStore = new CampaignAllocationAlgorithmDPStore(monthlyInventory + 1);

        if(totalCampaigns <= 0 || monthlyInventory <= 0) {
            return;
        }
        dpStore.dpVector[0] = 0;
        dpStore.pickedElementIndex[0] = -1;
        for (int inventory = 1; inventory <= monthlyInventory; ++inventory) {
            dpStore.dpVector[inventory] = dpStore.dpVector[inventory - 1];
            dpStore.pickedElementIndex[inventory] = dpStore.pickedElementIndex[inventory - 1];
            for (int campaignIndex = 0; campaignIndex < totalCampaigns; ++campaignIndex) {
                Campaign campaign = campaigns.get(campaignIndex);
                int campaignRevenue = campaign.getRevenue();
                int campaignImpressions = campaign.getImpressions();
                if (campaignImpressions <= inventory) {
                    int pickedRevenue = campaignRevenue + dpStore.dpVector[inventory - campaignImpressions];
                    if (pickedRevenue > dpStore.dpVector[inventory]) {
                        dpStore.dpVector[inventory] = pickedRevenue;
                        dpStore.pickedElementIndex[inventory] = campaignIndex;
                    }
                }
            }
        }
        prepareResponse(dpStore, campaignAllocationRequest);
    }

    public CampaignAllocationResponse getCampaignAllocationResponse() {
        return campaignAllocationResponse;
    }
}
