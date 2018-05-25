package com.choosemyride.data.model.uber;

import java.util.List;

public class UberEstimate {

    private List<PriceEstimate> prices;

    public List<PriceEstimate> getPrices() {
        return prices;
    }

    public void setPrices(List<PriceEstimate> prices) {
        this.prices = prices;
    }
}