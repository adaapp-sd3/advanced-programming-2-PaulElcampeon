let rainFrame = 0;
function produceRain() {
    try {
        getStage().removeChild(getWeatherContainer());
        getWeatherContainer().removeChildren(0, getWeatherContainer().children.length);
    } catch (err) {
        console.log("Rain container has no children opps");
    }

    getWeatherContainer().addChild(getRainAnimations()[rainFrame]);
    getStage().addChild(getWeatherContainer());
    rainFrame++;
    if (rainFrame == 19) {
        rainFrame = 0;
    }
}