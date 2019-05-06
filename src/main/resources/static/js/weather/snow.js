let snowFrame = 0;
function produceSnow() {
    try {
        getStage().removeChild(getWeatherContainer());
        getWeatherContainer().removeChildren(0, getWeatherContainer().children.length);
    } catch (err) {
        console.log("Snow container has no children");
    }
    getWeatherContainer().addChild(getSnowAnimations()[snowFrame]);
    getStage().addChild(getWeatherContainer());
    snowFrame++;
    if (snowFrame == 19) {
        snowFrame = 0;
    }
}