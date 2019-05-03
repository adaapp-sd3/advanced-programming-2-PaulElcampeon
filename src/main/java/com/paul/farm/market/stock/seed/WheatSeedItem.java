package com.paul.farm.market.stock.seed;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class WheatSeedItem extends MarketItem {

    public WheatSeedItem() {
        super(2, 0);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        farm.setWheatSeed(farm.getWheatSeed() + 1);
        farm.setBudget(farm.getBudget() - getBuyPrice());
        return true;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        return false;
    }

    public static WheatSeedItem getInstance() {
        return new WheatSeedItem();
    }
}
