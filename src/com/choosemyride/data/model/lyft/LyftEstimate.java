package com.choosemyride.data.model.lyft;

import java.util.List;

import com.google.gson.annotations.SerializedName;

public class LyftEstimate {

    @SerializedName("cost_estimates")
    private List<CostEstimates> costEstimates;

    public List<CostEstimates> getCostEstimates() {
        return costEstimates;
    }

    public void setCostEstimates(List<CostEstimates> costEstimates) {
        this.costEstimates = costEstimates;
    }
}