package com.choosemyride.data.model;

import java.util.TreeMap;

public class ResponseEstimateSummary {
    private TreeMap<String, Integer> uberSummary;
    private TreeMap<String, Integer> lyftSummary;

    public TreeMap<String, Integer> getUberSummary() {
        return uberSummary;
    }

    public void setUberSummary(TreeMap<String, Integer> uberSummary) {
        this.uberSummary = uberSummary;
    }

    public TreeMap<String, Integer> getLyftSummary() {
        return lyftSummary;
    }

    public void setLyftSummary(TreeMap<String, Integer> lyftSummary) {
        this.lyftSummary = lyftSummary;
    }
}