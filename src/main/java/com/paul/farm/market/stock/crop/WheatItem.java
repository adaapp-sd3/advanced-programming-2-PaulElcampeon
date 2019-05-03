package com.paul.farm.market.stock.crop;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class WheatItem extends MarketItem {

    public WheatItem() {
        super(0, 3);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if(farm.getTotalWheat() > 0 ) {
            farm.setBudget(farm.getBudget() + getSellPrice());
            farm.setTotalWheat(farm.getTotalWheat() - 1);
            return true;
        }
        return false;
    }

    public static WheatItem getInstance() {
        return new WheatItem();
    }
}
