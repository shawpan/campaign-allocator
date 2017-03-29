package com.oo.campaignallocator.resources;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import com.oo.campaignallocator.api.CampaignAllocationResponse;
import com.oo.campaignallocator.api.CampaignAllocationRequest;
import com.oo.campaignallocator.core.CampaignAllocationAlgorithmInterface;
import com.oo.campaignallocator.core.CampaignAllocationAlgorithmDP;

/**
 * CampaignAllocatorResource represents the public api for campaign allocation
 * it consumes and produces json output
 * @author Akiz
 */

@Path("/campaign-allocation")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class CampaignAllocatorResource {
    /**
     * Holds the campaign allocation algorthim object
     */
    private CampaignAllocationAlgorithmInterface campaignAllocationAlgorithm;

    /**
     * Creates a resource object for the http protocol
     */
    public CampaignAllocatorResource() {
        this.campaignAllocationAlgorithm = new CampaignAllocationAlgorithmDP();
    }

    /**
     * This is the POST api method for "/campaign-allocation" uri
     * @param campaignAllocationRequest the input json object
     * @return CampaignAllocationResponse the output json object
     */
    @POST
    public CampaignAllocationResponse allocateCampaigns(CampaignAllocationRequest campaignAllocationRequest) {
        campaignAllocationAlgorithm.init(campaignAllocationRequest);
        campaignAllocationAlgorithm.run();

        return campaignAllocationAlgorithm.getCampaignAllocationResponse();
    }

}
