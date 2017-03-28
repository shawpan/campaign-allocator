package com.oo.campaignallocator.core;

import com.oo.campaignallocator.api.CampaignAllocationRequest;
import com.oo.campaignallocator.api.CampaignAllocationResponse;

/**
 * CampaignAllocationAlgorithmInterface is the contact of any algorithm that
 * can be used for campaign allocation in this application
 * @author Akiz
 */
public interface CampaignAllocationAlgorithmInterface {
    /**
     * This method provides the signature of the method to run the algorithm
     * in any class that can be used as campaign allocation algorithm
     * @param campaignAllocationRequest is the input object
     */
    public void run(CampaignAllocationRequest campaignAllocationRequest);

    /**
     * This method provides the signature of the method to get the final output object
     * in any class that can be used as campaign allocation algorithm
     * @return CampaignAllocationResponse
     */
    public CampaignAllocationResponse getCampaignAllocationResponse();
}
