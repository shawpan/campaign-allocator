package com.oo.campaignallocator.core;

import com.oo.campaignallocator.api.CampaignAllocationRequest;
import com.oo.campaignallocator.api.CampaignAllocationResponse;
import java.lang.Math;
import java.util.ArrayList;

/**
 * CampaignAllocationAlgorithmDP implements a campaign allocation algorithm
 * using dynamic programming / 0-1 Knapsack approach. It follows the contact
 * CampaignAllocationAlgorithmInterface so that it fits into the main
 * application alongside other allocation algorithm.
 * @author Akiz
 */
public class CampaignAllocationAlgorithmDP implements CampaignAllocationAlgorithmInterface {
    /**
     * Holds the response object that is prepared suing the algorithm
     */
    private CampaignAllocationResponse campaignAllocationResponse;

    /**
     * Creates a campaign algorithm DP object
     */
    public CampaignAllocationAlgorithmDP() {
        this.campaignAllocationResponse = new CampaignAllocationResponse();
    }

    /**
     * Prepare response object that will be published through api
     * main part of the response includes totalImpressions, totalRevenue and
     * list of campaigns with their optimal count that produces the best revenue
     * under monthly inventory constraint
     * @param dpStore holds the required memory for the DP algorithm
     * @param campaignAllocationRequest is the request/input object with campaign and inventory data
     */
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

    /**
     * This is main method of the algorithm that finds the optimal list of campaigns
     * resulting maximum revenue with given monthly inventory
     * @param campaignAllocationRequest is the request/input object with campaign and inventory data
     */
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

    /**
     * Gets the final response object prepared by run method
     * @return campaignAllocationResponse final response
     */
    public CampaignAllocationResponse getCampaignAllocationResponse() {
        return campaignAllocationResponse;
    }
}
