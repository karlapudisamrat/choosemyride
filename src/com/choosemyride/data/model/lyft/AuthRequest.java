package com.choosemyride.data.model.lyft;

import com.google.gson.annotations.SerializedName;

public class AuthRequest {
    @SerializedName("grant_type")
    String grantType;

    String scope;

    public AuthRequest(String grantType, String scope) {
        this.grantType = grantType;
        this.scope = scope;
    }
}
