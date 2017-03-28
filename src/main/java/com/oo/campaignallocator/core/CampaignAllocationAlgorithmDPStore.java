package com.oo.campaignallocator.core;

import com.oo.campaignallocator.api.CampaignAllocationRequest;
import com.oo.campaignallocator.api.CampaignAllocationResponse;
import java.lang.Math;
import java.util.ArrayList;

public class CampaignAllocationAlgorithmDPStore {
    public int dpVector[];
    public int pickedElementIndex[];

    public CampaignAllocationAlgorithmDPStore(int size) {
        dpVector = new int[size];
        pickedElementIndex = new int[size];
    }
}
