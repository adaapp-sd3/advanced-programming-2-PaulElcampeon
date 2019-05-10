const marketListener = (market, farmer) => {
    if (farmer.x + farmer.width >= market.x && farmer.x <= market.x + market.width && farmer.y <= market.y + market.height && farmer.y >=  market.y - 30) {
        if (!getMarketSound().playing()) {
            playMarketSound();
        }
        return true;
    }
    if (getMarketSound().playing()) {
        decreaseMarketVolume();
    }
    return false
};