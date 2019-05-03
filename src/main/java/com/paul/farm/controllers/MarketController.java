package com.paul.farm.controllers;

import com.paul.farm.dtos.MarketRequest;
import com.paul.farm.market.Market;
import com.paul.farm.models.Farm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MarketController {

    @Autowired
    private Market market;

    @RequestMapping(value = "/market/buy", method = RequestMethod.POST)
    public void buyFromMarket(@RequestBody MarketRequest marketRequest) {
        market.buyItem(marketRequest);
    }

    @RequestMapping(value = "/market/sell", method = RequestMethod.POST)
    public void sellToMarket(@RequestBody MarketRequest marketRequest) {
        market.sellItem(marketRequest);
    }

}
