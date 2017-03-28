package com.oo.campaignallocator.core;

import com.oo.campaignallocator.api.CampaignAllocationRequest;
import com.oo.campaignallocator.api.CampaignAllocationResponse;

public interface CampaignAllocationAlgorithmInterface {

    public void run(CampaignAllocationRequest campaignAllocationRequest);

    public CampaignAllocationResponse getCampaignAllocationResponse();
}
