package com.choosemyride;

import com.choosemyride.data.model.ResponseEstimate;
import com.choosemyride.data.model.ResponseEstimateSummary;
import com.choosemyride.data.model.google.Location;
import com.choosemyride.exceptions.MultipleLocationsFoundException;
import com.choosemyride.exceptions.NoLocationFoundException;

public class Orchestrator {

    public static ResponseEstimate getCheapest(String src, String dest)
            throws NoLocationFoundException, MultipleLocationsFoundException {
        Location sLoc = AddressLocator.getLocationFor(src);
        Location dLoc = AddressLocator.getLocationFor(dest);

        ResponseEstimate uberEstimate = UberEstimator.getCheapestPrice(sLoc.getLat(), sLoc.getLng(),
                dLoc.getLat(), dLoc.getLng(), 2);

        ResponseEstimate lyftEstimate = LyftEstimator.getCheapestPrice(sLoc.getLat(), sLoc.getLng(),
                dLoc.getLat(), dLoc.getLng());

        if(uberEstimate.estimate < lyftEstimate.estimate) {
            return new ResponseEstimate("Uber's " + uberEstimate.serviceName, uberEstimate.estimate);
        } else {
            return new ResponseEstimate("Lyft's " + lyftEstimate.serviceName, lyftEstimate.estimate);
        }
    }

    public static ResponseEstimateSummary getSummary(String src, String dest)
            throws NoLocationFoundException, MultipleLocationsFoundException {
        Location sLoc = AddressLocator.getLocationFor(src);
        Location dLoc = AddressLocator.getLocationFor(dest);

        ResponseEstimateSummary summary = new ResponseEstimateSummary();
        summary.setUberSummary(UberEstimator.getSummary(sLoc.getLat(), sLoc.getLng(),
                dLoc.getLat(), dLoc.getLng(), 2));
        summary.setLyftSummary(LyftEstimator.getSummary(sLoc.getLat(), sLoc.getLng(),
                dLoc.getLat(), dLoc.getLng()));

        return summary;
    }
}