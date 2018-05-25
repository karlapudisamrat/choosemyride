package com.choosemyride.data.model.google;

import java.util.List;

public class GeocodeResponse {
    private List<LatLng> results;
    private String status;

    public List<LatLng> getResults() {
        return results;
    }

    public void setResults(List<LatLng> results) {
        this.results = results;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
