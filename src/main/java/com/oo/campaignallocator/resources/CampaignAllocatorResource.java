package com.oo.campaignallocator.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.oo.campaignallocator.api.CampaignAllocationResponse;
import com.oo.campaignallocator.api.CampaignAllocationRequest;
import com.oo.campaignallocator.core.CampaignAllocationAlgorithmInterface;
import com.oo.campaignallocator.core.CampaignAllocationAlgorithmDP;

@Path("/campaign-allocation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CampaignAllocatorResource {
    private CampaignAllocationAlgorithmInterface campaignAllocationAlgorithm;

    public CampaignAllocatorResource() {
        this.campaignAllocationAlgorithm = new CampaignAllocationAlgorithmDP();
    }

    @POST
    public CampaignAllocationResponse allocateCampaigns(CampaignAllocationRequest campaignAllocationRequest) {
        campaignAllocationAlgorithm.run(campaignAllocationRequest);

        return campaignAllocationAlgorithm.getCampaignAllocationResponse();
    }

}
