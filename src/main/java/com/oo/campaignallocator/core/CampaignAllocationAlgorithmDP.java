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
     * Holds the request object that is used as input by the algorithm
     */
    private CampaignAllocationRequest campaignAllocationRequest;

    /**
     * Holds the scaling factor of large inventory amount
     */
    private int inventoryScaleFactor;

    /**
     * Maximum inventory amount supported without scaling
     */
    public static final int MAX_INVENTORY_AMOUNT = 99999999;

    /**
     * Creates a campaign algorithm DP object
     */
    public CampaignAllocationAlgorithmDP() {
    }

    /**
     * Initialize dp algorithm object with input and output object
     * @param campaignAllocationRequest input object
     */
    public void init(CampaignAllocationRequest campaignAllocationRequest) {
        this.campaignAllocationRequest = campaignAllocationRequest;
        this.campaignAllocationResponse = new CampaignAllocationResponse();
        this.inventoryScaleFactor = 1;
        if (campaignAllocationRequest.getMonthlyInventory() > CampaignAllocationAlgorithmDP.MAX_INVENTORY_AMOUNT) {
            this.inventoryScaleFactor = 100;
        }
    }

    /**
     * Prepare response object that will be published through api
     * main part of the response includes totalImpressions, totalRevenue and
     * list of campaigns with their optimal count that produces the best revenue
     * under monthly inventory constraint
     * @param dpStore holds the required memory for the DP algorithm
     * @param campaignAllocationRequest is the request/input object with campaign and inventory data
     */
    private void prepareResponse(CampaignAllocationAlgorithmDPStore dpStore) {
        campaignAllocationResponse = new CampaignAllocationResponse();
        ArrayList<Campaign> campaigns = campaignAllocationRequest.getCampaigns();
        int totalCampaigns = campaigns.size();
        long totalImpressions = 0;
        int monthlyInventory = campaignAllocationRequest.getMonthlyInventory() / inventoryScaleFactor;
        campaignAllocationResponse.setTotalRevenue(dpStore.dpVector[monthlyInventory]);
        for (int inventory = monthlyInventory; inventory > 0;) {
            if(dpStore.pickedElementIndex[inventory] >= 0) {
                Campaign campaign = campaigns.get(dpStore.pickedElementIndex[inventory]);
                campaign.incrementCount();
                totalImpressions += campaign.getImpressions();
                inventory -= (campaign.getImpressions() / inventoryScaleFactor);
            } else {
              inventory--;
            }
        }
        campaignAllocationResponse.setTotalImpressions(totalImpressions);
        campaignAllocationResponse.setCampaigns(campaigns);
    }

    /**
     * This is main method of the algorithm that finds the optimal list of campaigns
     * resulting maximum revenue with given monthly inventory
     */
    public void run() {
        ArrayList<Campaign> campaigns = campaignAllocationRequest.getCampaigns();
        int totalCampaigns = campaigns.size();
        int monthlyInventory = campaignAllocationRequest.getMonthlyInventory() / inventoryScaleFactor;
        CampaignAllocationAlgorithmDPStore dpStore = new CampaignAllocationAlgorithmDPStore(monthlyInventory + 1);

        if(totalCampaigns <= 0 || monthlyInventory <= 0) {
            return;
        }
        dpStore.dpVector[0] = 0;
        dpStore.pickedElementIndex[0] = -1;
        for (int inventory = 1; inventory <= monthlyInventory; ++inventory) {
            dpStore.dpVector[inventory] = dpStore.dpVector[inventory - 1];
            dpStore.pickedElementIndex[inventory] = -1;
            for (int campaignIndex = 0; campaignIndex < totalCampaigns; ++campaignIndex) {
                Campaign campaign = campaigns.get(campaignIndex);
                int campaignRevenue = campaign.getRevenue();
                int campaignImpressions = campaign.getImpressions() / inventoryScaleFactor;
                if (campaignImpressions <= inventory) {
                    int pickedRevenue = campaignRevenue + dpStore.dpVector[inventory - campaignImpressions];
                    if (pickedRevenue > dpStore.dpVector[inventory]) {
                        dpStore.dpVector[inventory] = pickedRevenue;
                        dpStore.pickedElementIndex[inventory] = campaignIndex;
                    }
                }
            }
        }
        prepareResponse(dpStore);
    }

    /**
     * Gets the final response object prepared by run method
     * @return campaignAllocationResponse final response
     */
    public CampaignAllocationResponse getCampaignAllocationResponse() {
        return campaignAllocationResponse;
    }
}
