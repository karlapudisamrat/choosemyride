package com.choosemyride.data.model.uber;

import com.google.gson.annotations.SerializedName;

public class PriceEstimate {
    @SerializedName("product_id")
    String productId;

    @SerializedName("currency_code")
    String currencyCode;

    @SerializedName("display_name")
    String displayName;

    @SerializedName("localized_display_name")
    String localizedDisplayName;

    String estimate;

    int minimum;

    @SerializedName("low_estimate")
    int lowEstimate;

    @SerializedName("high_estimate")
    int highEstimate;

    @SerializedName("surge_multiplier")
    int surgeMultiplier;

    int duration;

    float distance;

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCurrencyCode() {
        return currencyCode;
    }

    public void setCurrencyCode(String currencyCode) {
        this.currencyCode = currencyCode;
    }

    public String getDisplayName() {
        return displayName;
    }

    public void setDisplayName(String displayName) {
        this.displayName = displayName;
    }

    public String getLocalizedDisplayName() {
        return localizedDisplayName;
    }

    public void setLocalizedDisplayName(String localizedDisplayName) {
        this.localizedDisplayName = localizedDisplayName;
    }

    public String getEstimate() {
        return estimate;
    }

    public void setEstimate(String estimate) {
        this.estimate = estimate;
    }

    public int getMinimum() {
        return minimum;
    }

    public void setMinimum(int minimum) {
        this.minimum = minimum;
    }

    public int getLowEstimate() {
        return lowEstimate;
    }

    public void setLowEstimate(int lowEstimate) {
        this.lowEstimate = lowEstimate;
    }

    public int getHighEstimate() {
        return highEstimate;
    }

    public void setHighEstimate(int highEstimate) {
        this.highEstimate = highEstimate;
    }

    public int getSurgeMultiplier() {
        return surgeMultiplier;
    }

    public void setSurgeMultiplier(int surgeMultiplier) {
        this.surgeMultiplier = surgeMultiplier;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public float getDistance() {
        return distance;
    }

    public void setDistance(float distance) {
        this.distance = distance;
    }
}
