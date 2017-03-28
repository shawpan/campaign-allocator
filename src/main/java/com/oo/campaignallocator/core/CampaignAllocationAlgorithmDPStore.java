package com.oo.campaignallocator.core;

import com.oo.campaignallocator.api.CampaignAllocationRequest;
import com.oo.campaignallocator.api.CampaignAllocationResponse;
import java.lang.Math;
import java.util.ArrayList;

/**
 * CampaignAllocationAlgorithmDPStore stores the vectors to compute optimal
 * solution using 0-1 Knapsack DP algorithm
 * @author Akiz
 */
public class CampaignAllocationAlgorithmDPStore {
    /**
     * Holds the revenue calculation data required for the DP algorithm
     */
    public int dpVector[];

    /**
     * Holds index of picked campaign as value for the inventory as key
     */
    public int pickedElementIndex[];

    /**
     * Creates the memory/store object for the DP algorithm
     * @param size is the length of vectors 
     */
    public CampaignAllocationAlgorithmDPStore(int size) {
        dpVector = new int[size];
        pickedElementIndex = new int[size];
    }
}
