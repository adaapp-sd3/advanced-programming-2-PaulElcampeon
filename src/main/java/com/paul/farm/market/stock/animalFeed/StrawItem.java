package com.paul.farm.market.stock.animalFeed;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class StrawItem extends MarketItem {

    public StrawItem() {
        super(5, 3);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        farm.setTotalStraw(farm.getTotalStraw() + 1);
        farm.setBudget(farm.getBudget() - getBuyPrice());
        return true;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalStraw() > 0) {
            farm.setBudget(farm.getBudget() + getSellPrice());
            farm.setTotalStraw(farm.getTotalStraw() - 1);
            return true;
        }
        return false;
    }

    public static StrawItem getInstance() {
        return new StrawItem();
    }
}
