package com.choosemyride.data.model.google;

import com.google.gson.annotations.SerializedName;

public class LatLng {
    @SerializedName("place_id")
    String placeId;

    @SerializedName("geometry")
    Geometry geometry;

    public Geometry getGeometry() {
        return geometry;
    }

    public void setGeometry(Geometry geometry) {
        this.geometry = geometry;
    }

    public String getPlaceId() {
        return placeId;
    }

    public void setPlaceId(String placeId) {
        this.placeId = placeId;
    }
}