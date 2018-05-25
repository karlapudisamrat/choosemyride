package com.choosemyride;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;
import com.choosemyride.Utils.HttpClientHelper;
import com.choosemyride.data.model.ResponseEstimate;
import com.choosemyride.data.model.lyft.AuthRequest;
import com.choosemyride.data.model.lyft.AuthResponse;
import com.choosemyride.data.model.lyft.CostEstimates;
import com.choosemyride.data.model.lyft.LyftEstimate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * https://api.lyft.com/v1/cost?start_lat=37.5629817&start_lng=-122.286287&end_lat=37.4020858&end_lng=-122.1468056
 */
public class LyftEstimator {

    public static ResponseEstimate getCheapestPrice(double strtLat, double strtLng,
                                                    double endLat, double endLng) {
        LyftEstimate estimates = getEstimatesFor(strtLat, strtLng, endLat, endLng);
        CostEstimates estimate = estimates.getCostEstimates().stream()
                .min((a, b) -> a.getEstimatedCostCentsMin() - b.getEstimatedCostCentsMin()).get();
        return new ResponseEstimate(estimate.getDisplayName(), (int) (estimate.getEstimatedCostCentsMin()/100));
    }

    public static TreeMap<String, Integer> getSummary(double startLat, double startLng,
                                                      double endLat, double endLng) {
        LyftEstimate estimates = getEstimatesFor(startLat, startLng, endLat, endLng);

        TreeMap<String, Integer> estimateSummary = new TreeMap<>();

        for(CostEstimates estimate: estimates.getCostEstimates()) {
            estimateSummary.put(estimate.getDisplayName(), estimate.getEstimatedCostCentsMin()/100);
        }

        return estimateSummary;
    }

    public static LyftEstimate getEstimatesFor(double strtLat, double strtLng,
                                       double endLat, double endLng) {

        String url = buildEstimateUrl(strtLat, strtLng, endLat, endLng);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", String.format("Bearer %s", getAuthToken()));
        httpGet.setHeader("Content-type", "application/json");

        try {
            HttpResponse resp = HttpClientHelper.getHttpClient().execute(httpGet);
            //TODO Error handling for status codes
            int statusCode = resp.getStatusLine().getStatusCode();
            String respJson = EntityUtils.toString(resp.getEntity());

            Gson gson = new GsonBuilder().create();
            return gson.fromJson(respJson, LyftEstimate.class);
        } catch (IOException e) {
            throw new RuntimeException("Error doing a GET on lyft API", e);
        }
    }

    private static String buildEstimateUrl(double strtLat, double strtLng,
                                           double endLat, double endLng) {
        try {
            return new URIBuilder()
                    .setScheme("https")
                    .setHost("api.lyft.com")
                    .setPath("/v1/cost")
                    .setParameter("start_lat", String.valueOf(strtLat))
                    .setParameter("start_lng", String.valueOf(strtLng))
                    .setParameter("end_lat", String.valueOf(endLat))
                    .setParameter("end_lng", String.valueOf(endLng))
                    .build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Exception while building lyft estimate url", e);
        }
    }

    private static String getAuthToken() {
        HttpPost httpPost = new HttpPost("https://api.lyft.com/oauth/token");
        httpPost.setHeader("Authorization", "Basic " + System.getProperty("LYFT_API_KEY"));
        httpPost.setHeader("Content-type", "application/json");

        Gson gson = new GsonBuilder().create();
        AuthRequest req = new AuthRequest("client_credentials", "public");
        String body = gson.toJson(req);

        try {
            httpPost.setEntity(new StringEntity(body));
            HttpResponse resp = HttpClientHelper.getHttpClient().execute(httpPost);

            //TODO Error handling for status codes
            int statusCode = resp.getStatusLine().getStatusCode();
            String respJson = EntityUtils.toString(resp.getEntity());
            AuthResponse authResp = gson.fromJson(respJson, AuthResponse.class);
            return authResp.getAccessToken();
        } catch (IOException e) {
            throw new RuntimeException("Error getting access token for lyft", e);
        }
    }
}
