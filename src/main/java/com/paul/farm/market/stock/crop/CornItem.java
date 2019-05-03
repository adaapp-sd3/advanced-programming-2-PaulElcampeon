package com.paul.farm.market.stock.crop;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class CornItem extends MarketItem {

    public CornItem() {
        super(3, 2);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        farm.setTotalCorn(farm.getTotalCorn() + 1);
        farm.setBudget(farm.getBudget() - getBuyPrice());
        return true;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalCorn() > 0) {
            farm.setBudget(farm.getBudget() + getSellPrice());
            farm.setTotalCorn(farm.getTotalCorn() - 1);
            return true;
        }
        return false;
    }

    public static CornItem getInstance() {
        return new CornItem();
    }
}
