package com.oo.campaignallocator.core;

/**
 * Campaign object that represents a campaign
 */
public class Campaign {
    /**
     * Holds the customer name of the campaign
     */
    private String customerName;

    /**
     * Holds total number of the campaign that contributes to maximum revenue
     * genertaion after running campaign allocation algorithm
     */
    private int count;

    /**
     * Holds the unit number of impressions that should be served per campaign
     */
    private int impressions;

    /**
     * Holds the price/revenue per campaign
     */
    private int revenue;

    /**
     * Creates a campaign object
     */
    public Campaign() {
        this.count = 0;
        this.impressions = 0;
        this.revenue = 0;
    }

    /**
     * Increments the number of campaign
     */
    public void incrementCount() {
        count++;
    }

    /**
     * Creates a campaign representation with customer name
     * @param customerName name of customer
     */
    public Campaign(String customerName) {
        this.customerName = customerName;
    }

    /**
     * Creates a campaign representation with customer name, impressions, revenue
     * @param customerName name of customer
     * @param impressions unit number of impressions per campaign
     * @param revenue revenue of the impressions
     */
    public Campaign(String customerName, int impressions, int revenue) {
        this.customerName = customerName;
        this.impressions = impressions;
        this.revenue = revenue;
    }

    /**
     * Get customer name
     * @return customerName name of customer
     */
    public String getCustomerName() {
        return customerName;
    }

    /**
     * Get the number of campaign
     * @return count number of campaign
     */
    public int getCount() {
        return count;
    }

    /**
     * Set the number of campaign
     * @param count number of campaign
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Get unit number of impressions per campaign
     * @return impressions
     */
    public int getImpressions() {
        return impressions;
    }

    /**
     * Get unit number of revenue/price per campaign
     * @return revenue
     */
    public int getRevenue() {
        return revenue;
    }

    /**
     * Get total number of impressions muliplying campaign frequency with
     * impressions per campaign
     * @return totalImpressions
     */
    public long getTotalImpressions() {
        return impressions * count;
    }

    /**
     * Get total number of revenue muliplying campaign frequency with
     * revenue/price per campaign
     * @return totalRevenue
     */
    public long getTotalRevenue() {
        return revenue * count;
    }
}
