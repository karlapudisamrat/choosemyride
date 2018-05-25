package com.choosemyride.data.model;

public class ResponseEstimate {
    public String serviceName;
    public Integer estimate;

    public ResponseEstimate(String serviceName, Integer estimate) {
        this.serviceName = serviceName;
        this.estimate = estimate;
    }
}