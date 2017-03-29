package com.oo.campaignallocator;

import com.oo.campaignallocator.core.Campaign;
import com.oo.campaignallocator.api.CampaignAllocationResponse;
import com.oo.campaignallocator.api.CampaignAllocationRequest;
import com.oo.campaignallocator.resources.CampaignAllocatorResource;
import com.google.common.base.Optional;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class CampaignAllocatorResourceTest {
    private CampaignAllocatorResource resource;
    private CampaignAllocationRequest request;

    @Before
    public void setup() {
        // Before each test, we re-instantiate our resource so it will reset
        // the counter. It is good practice when dealing with a class that
        // contains mutable data to reset it so tests can be ran independently
        // of each other.
        resource = new CampaignAllocatorResource();
        request = new CampaignAllocationRequest();
        request.setMonthlyInventory(2000000000);
        ArrayList<Campaign> campaigns = new ArrayList<Campaign>();
        campaigns.add(new Campaign("customer1", 1000000, 5000));
        campaigns.add(new Campaign("customer2", 2000000, 9000));
        campaigns.add(new Campaign("customer3", 3000000, 20000));
        request.setCampaigns(campaigns);
    }

    @Test
    public void computedTotalRevenueIsOptimal() {
        CampaignAllocationResponse result = resource.allocateCampaigns(request);
        assertThat(result.getTotalRevenue() == 13330000);
    }

    @Test
    public void computedTotalImpressionsIsNotGreaterThanMonthlyInventory() {
        CampaignAllocationResponse result = resource.allocateCampaigns(request);
        assertThat(result.getTotalImpressions() <= request.getMonthlyInventory());
    }

    @Test
    public void computedTotalImpressionsIsEqualToSumOfCampaignTotalImpressions() {
        CampaignAllocationResponse result = resource.allocateCampaigns(request);
        List<Campaign> campaigns = result.getCampaigns();
        int totalImpressions = 0;
        for (Campaign campaign:campaigns) {
            totalImpressions += campaign.getTotalImpressions();
        }
        assertThat(result.getTotalImpressions() == totalImpressions);
    }

    @Test
    public void computedTotalRevenueIsEqualToSumOfCampaignTotalRevenue() {
        CampaignAllocationResponse result = resource.allocateCampaigns(request);
        List<Campaign> campaigns = result.getCampaigns();
        int totalRevenue = 0;
        for (Campaign campaign:campaigns) {
            totalRevenue += campaign.getTotalRevenue();
        }
        assertThat(result.getTotalRevenue() == totalRevenue);
    }
}
