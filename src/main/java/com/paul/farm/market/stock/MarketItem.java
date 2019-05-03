package com.paul.farm.market.stock;

import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public abstract class MarketItem {

    private int buyPrice;
    private int sellPrice;

    public MarketItem() {

    }

    public MarketItem(int buyPrice, int sellPrice) {
        this.buyPrice = buyPrice;
        this.sellPrice = sellPrice;
    }

    public abstract boolean addToFarm(Farm farm);

    public abstract boolean removeFromFarm(Farm farm);
}
