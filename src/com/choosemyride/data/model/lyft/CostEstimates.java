package com.choosemyride.data.model.lyft;

import com.google.gson.annotations.SerializedName;

public class CostEstimates {

    @SerializedName("ride_type")
    String rideType;

    @SerializedName("display_name")
    String displayName;

    @SerializedName("estimated_cost_cents_max")
    int estimatedCostCentsMax;

    @SerializedName("estimated_cost_cents_min")
    int estimatedCostCentsMin;

    @SerializedName("estimated_distance_miles")
    double estimatedDistanceMiles;

    @SerializedName("estimated_distance_seconds")
    double estimatedDistanceSeconds;

    @SerializedName("primetime_confirmation_token")
    String primetimeConfirmationToken;

    @SerializedName("cost_token")
    String costToken;

    @SerializedName("is_valid_estimate")
    boolean isValidEstimate;

    public String getRideType() {
        return rideType;
    }

    public void setRideType(String rideType) {
        this.rideType = rideType;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public int getEstimatedCostCentsMax() {
        return estimatedCostCentsMax;
    }

    public void setEstimatedCostCentsMax(int estimatedCostCentsMax) {
        this.estimatedCostCentsMax = estimatedCostCentsMax;
    }

    public int getEstimatedCostCentsMin() {
        return estimatedCostCentsMin;
    }

    public void setEstimatedCostCentsMin(int estimatedCostCentsMin) {
        this.estimatedCostCentsMin = estimatedCostCentsMin;
    }

    public double getEstimatedDistanceMiles() {
        return estimatedDistanceMiles;
    }

    public void setEstimatedDistanceMiles(double estimatedDistanceMiles) {
        this.estimatedDistanceMiles = estimatedDistanceMiles;
    }

    public double getEstimatedDistanceSeconds() {
        return estimatedDistanceSeconds;
    }

    public void setEstimatedDistanceSeconds(double estimatedDistanceSeconds) {
        this.estimatedDistanceSeconds = estimatedDistanceSeconds;
    }

    public String getPrimetimeConfirmationToken() {
        return primetimeConfirmationToken;
    }

    public void setPrimetimeConfirmationToken(String primetimeConfirmationToken) {
        this.primetimeConfirmationToken = primetimeConfirmationToken;
    }

    public String getCostToken() {
        return costToken;
    }

    public void setCostToken(String costToken) {
        this.costToken = costToken;
    }

    public boolean isValidEstimate() {
        return isValidEstimate;
    }

    public void setValidEstimate(boolean validEstimate) {
        isValidEstimate = validEstimate;
    }
}
