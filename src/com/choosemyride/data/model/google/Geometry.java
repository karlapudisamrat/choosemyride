package com.choosemyride.data.model.google;


import com.google.gson.annotations.SerializedName;

public class Geometry {
    @SerializedName("location")
    Location location;

    public Location getLocation() {
        return location;
    }

    public void setLocation(Location location) {
        this.location = location;
    }
}