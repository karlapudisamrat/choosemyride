package com.choosemyride.data.model.lyft;

import com.google.gson.annotations.SerializedName;

public class AuthResponse {

    @SerializedName("token_type")
    String token_type;

    @SerializedName("access_token")
    String accessToken;

    @SerializedName("expires_in")
    int expiresIn;

    String scope;

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
