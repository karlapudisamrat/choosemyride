package com.choosemyride.Utils;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

public class HttpClientHelper {
    private static HttpClient httpClient;

    public static HttpClient getHttpClient() {
        if(httpClient == null) {
            httpClient = HttpClientBuilder.create().build();
        }

        return httpClient;
    }
}
