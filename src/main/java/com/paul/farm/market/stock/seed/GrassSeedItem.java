package com.paul.farm.market.stock.seed;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class GrassSeedItem extends MarketItem {

    public GrassSeedItem() {
        super(3, 0);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        farm.setGrassSeed(farm.getGrassSeed() + 1);
        farm.setBudget(farm.getBudget() - getBuyPrice());
        return true;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        return false;
    }

    public static GrassSeedItem getInstance() {
        return new GrassSeedItem();
    }
}
