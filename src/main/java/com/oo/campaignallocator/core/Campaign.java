package com.oo.campaignallocator.core;


public class Campaign {
    private String customerName;
    private int count;
    private int impressions;
    private int revenue;

    public Campaign() {
        this.count = 0;
        this.impressions = 0;
        this.revenue = 0;
    }

    public void incrementCount() {
        count++;
    }

    public Campaign(String customerName) {
        this.customerName = customerName;
    }

    public String getCustomerName() {
        return customerName;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getImpressions() {
        return impressions;
    }

    public int getRevenue() {
        return revenue;
    }

    public long getTotalImpressions() {
        return impressions * count;
    }

    public long getTotalRevenue() {
      return revenue * count;
    }
}
