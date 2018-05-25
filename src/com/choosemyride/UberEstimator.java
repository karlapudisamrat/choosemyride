package com.choosemyride;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.TreeMap;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import com.choosemyride.Utils.HttpClientHelper;
import com.choosemyride.data.model.ResponseEstimate;
import com.choosemyride.data.model.uber.PriceEstimate;
import com.choosemyride.data.model.uber.UberEstimate;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * Example request
 * https://api.uber.com/v1.2/estimates/price?start_latitude=37.5629817&start_longitude=-122.286287
 * &end_latitude=37.4020858&end_longitude=-122.1468056&seat_count=2
 */
public class UberEstimator {

    public static ResponseEstimate getCheapestPrice(double startLat, double startLng,
                                                    double endLat, double endLng, int seatCount) {
        UberEstimate estimates = getEstimatesFor(startLat, startLng, endLat, endLng, seatCount);
        PriceEstimate estimate = estimates.getPrices().stream()
                .min((a, b) -> a.getLowEstimate() - b.getLowEstimate()).get();
        return new ResponseEstimate(estimate.getDisplayName(), estimate.getLowEstimate());
    }

    public static TreeMap<String, Integer> getSummary(double strtLat, double strtLng,
                                                      double endLat, double endLng, int seatCount) {
        UberEstimate estimates = getEstimatesFor(strtLat, strtLng, endLat, endLng, seatCount);

        TreeMap<String, Integer> estimateSummary = new TreeMap<>();
        for(PriceEstimate estimate: estimates.getPrices()) {
            estimateSummary.put(estimate.getDisplayName(), estimate.getLowEstimate());
        }

        return estimateSummary;
    }

    public static UberEstimate getEstimatesFor(double strtLat, double strtLng,
                                               double endLat, double endLng, int seatCount) {

        String url = buildEstimateUrl(strtLat, strtLng, endLat, endLng, seatCount);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Authorization", "Token " + System.getProperty("UBER_API_KEY"));
        httpGet.setHeader("Accept-Language", "en_US");
        httpGet.setHeader("Content-type", "application/json");

        try {
            HttpResponse resp = HttpClientHelper.getHttpClient().execute(httpGet);
            //TODO Error handling for status codes
            int statusCode = resp.getStatusLine().getStatusCode();
            String respJson = EntityUtils.toString(resp.getEntity());

            Gson gson = new GsonBuilder().create();

            return gson.fromJson(respJson, UberEstimate.class);
        } catch (IOException e) {
            throw new RuntimeException("Error doing a GET on Uber API", e);
        }
    }

    private static String buildEstimateUrl(double startLat, double startLng,
                                        double endLat, double endLng, int seatCount) {
        try {
            return new URIBuilder()
                    .setScheme("https")
                    .setHost("api.uber.com")
                    .setPath("/v1.2/estimates/price")
                    .setParameter("start_latitude", String.valueOf(startLat))
                    .setParameter("start_longitude", String.valueOf(startLng))
                    .setParameter("end_latitude", String.valueOf(endLat))
                    .setParameter("end_longitude", String.valueOf(endLng))
                    .setParameter("seat_count", String.valueOf(seatCount))
                    .build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Exception while building uber estimate url", e);
        }
    }
}
