package com.oo.campaignallocator;

import io.dropwizard.Application;
import io.dropwizard.setup.Bootstrap;
import io.dropwizard.setup.Environment;
import com.oo.campaignallocator.resources.CampaignAllocatorResource;

public class CampaignAllocatorApplication extends Application<CampaignAllocatorConfiguration> {

    public static void main(final String[] args) throws Exception {
        new CampaignAllocatorApplication().run(args);
    }

    @Override
    public String getName() {
        return "CampaignAllocator";
    }

    @Override
    public void initialize(final Bootstrap<CampaignAllocatorConfiguration> bootstrap) {
        // TODO: application initialization
    }

    @Override
    public void run(final CampaignAllocatorConfiguration configuration,
                    final Environment environment) {
        environment.jersey().register(new CampaignAllocatorResource());
    }

}
