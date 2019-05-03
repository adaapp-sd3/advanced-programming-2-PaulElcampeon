package com.paul.farm.market.stock.animalCondiments;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class WoolItem extends MarketItem {

    public WoolItem() {
        super(0, 20);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalWool() > 0) {
            farm.setBudget(farm.getBudget() + getSellPrice());
            farm.setTotalWool(farm.getTotalWool() - 1);
            return true;
        }
        return false;
    }

    public static WoolItem getInstance() {
        return new WoolItem();
    }
}
