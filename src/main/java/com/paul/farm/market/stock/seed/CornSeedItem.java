package com.paul.farm.market.stock.seed;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class CornSeedItem extends MarketItem {

    public CornSeedItem() {
        super(5, 0);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        farm.setCornSeed(farm.getCornSeed() + 1);
        farm.setBudget(farm.getBudget() - getBuyPrice());
        return true;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        return false;
    }

    public static CornSeedItem getInstance() {
        return new CornSeedItem();
    }
}
