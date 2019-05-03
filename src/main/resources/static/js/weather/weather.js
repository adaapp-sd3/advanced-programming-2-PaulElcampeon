var weatherContainer = new PIXI.Container();
var weather = "clear";

function getWeather() {
    return weather;
}

function setWeather(newWeather) {
    weather = newWeather.toLowerCase();
}

function getWeatherContainer() {
    return weatherContainer;
}

function produceWeather() {

    if (getWeather() == "clear") {
        clearWeather();
    } else if (getWeather() == "rain") {
        produceRain();
    } else if (getWeather() == "snow") {
        produceSnow();
    }
}


function clearWeather() {
    if (getWeatherContainer().children != 0) {
        try {
            getStage().removeChild(getWeatherContainer());
            getWeatherContainer().removeChildren(0, getWeatherContainer().children.length);
        } catch (err) {
            console.log("Rain container has no children opps");
        }
    }
}