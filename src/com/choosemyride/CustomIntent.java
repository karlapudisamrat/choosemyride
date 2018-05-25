package com.choosemyride;

public enum CustomIntent {

    CHEAPEST("GetCheapestIntent"),
    CHEAPEST_QUICK("GetCheapestQuickIntent"),
    SUMMARY("GetEstimatesSummaryIntent"),
    SUMMARY_QUICK("GetEstimatesSummaryQuickIntent");

    private String name;

    CustomIntent(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }

    public static CustomIntent getIntent(String name) {
        for(CustomIntent intent: values()) {
            if(intent.getName().equalsIgnoreCase(name)) {
                return intent;
            }
        }

        return null;
    }
}
