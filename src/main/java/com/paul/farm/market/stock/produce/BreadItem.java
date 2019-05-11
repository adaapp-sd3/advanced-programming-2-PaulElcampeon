package com.paul.farm.market.stock.produce;

import com.paul.farm.market.stock.MarketItem;
import com.paul.farm.models.Farm;
import lombok.Data;

@Data
public class BreadItem extends MarketItem {

    public BreadItem() {
        super(0, 5);
    }

    @Override
    public boolean addToFarm(Farm farm) {
        return false;
    }

    @Override
    public boolean removeFromFarm(Farm farm) {
        if (farm.getTotalBread() > 0) {
            farm.setBudget(farm.getBudget() + getSellPrice());
            farm.setTotalBread(farm.getTotalBread() - 1);
            return true;
        }
        return false;
    }

    public static BreadItem getInstance() {
        return new BreadItem();
    }

}
