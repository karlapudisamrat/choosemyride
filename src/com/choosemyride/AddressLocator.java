package com.choosemyride;

import java.io.IOException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.util.EntityUtils;
import com.choosemyride.Utils.HttpClientHelper;
import com.choosemyride.data.model.google.GeocodeResponse;
import com.choosemyride.data.model.google.Location;
import com.choosemyride.exceptions.MultipleLocationsFoundException;
import com.choosemyride.exceptions.NoLocationFoundException;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

/**
 * https://maps.googleapis.com/maps/api/geocode/json?address=1927+bridgepointe+pkwy+san+mateo+CA&key={API_KEY}
 */
public class AddressLocator {

    public static Location getLocationFor(String address)
            throws NoLocationFoundException, MultipleLocationsFoundException {

        String url = buildMapsUrl(address);
        HttpGet httpGet = new HttpGet(url);
        httpGet.setHeader("Content-type", "application/json");

        try {
            HttpResponse resp = HttpClientHelper.getHttpClient().execute(httpGet);
            //TODO Error handling for status codes
            int statusCode = resp.getStatusLine().getStatusCode();
            String respJson = EntityUtils.toString(resp.getEntity());

            Gson gson = new GsonBuilder().create();
            GeocodeResponse geoResp = gson.fromJson(respJson, GeocodeResponse.class);

            if(geoResp.getResults() == null || geoResp.getResults().isEmpty()) {
                throw new NoLocationFoundException(address);
            } else if(geoResp.getResults().size() > 1) {
                throw new MultipleLocationsFoundException(address);
            }

            return new Location(geoResp.getResults().get(0).getGeometry().getLocation().getLat(),
                    geoResp.getResults().get(0).getGeometry().getLocation().getLng());
        } catch (IOException e) {
            throw new RuntimeException("Error doing a GET on Uber API", e);
        }
    }

    private static String buildMapsUrl(String address) {
        try {
            return new URIBuilder()
                    .setScheme("https")
                    .setHost("maps.googleapis.com")
                    .setPath("/maps/api/geocode/json")
                    .setParameter("address", address)
                    .setParameter("key", System.getProperty("GOOGLE_API_KEY"))
                    .build().toString();
        } catch (URISyntaxException e) {
            throw new RuntimeException("Exception while building uber estimate url", e);
        }
    }
}
