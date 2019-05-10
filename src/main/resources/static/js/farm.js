const frameDiv = document.getElementById("farm");
const log = console.log;
const app = new PIXI.Application();
const canvas = app.view;
canvas.width = frameDiv.offsetWidth;
canvas.height = frameDiv.offsetHeight;
//canvas.height = window.innerHeight;
canvas.classList.add('forCanvas');
frameDiv.appendChild(canvas);
const MAX_WIDTH = 700;
const MAX_HEIGHT = 700;
var widthRatio = canvas.width < 700 ? canvas.width / MAX_WIDTH : 700 / 700;
var heightRatio = canvas.height < 700 ? canvas.height / MAX_HEIGHT : 700 / 700;
var previousWidth = canvas.width;
var previousHeight = canvas.height;
var activeRatio;

const stage = app.stage;

//preload stuff
Promise.all([createFields(), createAnimals(), createCrops(), createVisitors(), createRainAnimation(), createSnowAnimation()])
    .then(() => {
        initBackground();
        createTractorAndMarketSprite();
        connect();
        setup();
    });

var farmer, market, leftTractorTexture, rightTractorTexture, marketTexture;

function setup() {

    keyboardMovements();

    let slowDownWeatherEffect = 0;

    window.setInterval(getMemoryInfo, 300000);//get memory info every 5 mins

    getCurrentWeather();
    window.setInterval(getCurrentWeather, 600000);//get weather data every 10 mins

    app.ticker.add((delta) => {

        if (!checkForCollision(farmer, getBackground())) {
            farmer.x += farmer.vx;
            farmer.y += farmer.vy;
        }
        slowDownWeatherEffect++;
        if (slowDownWeatherEffect % 6 == 0) {
            produceWeather();
            if (slowDownWeatherEffect == 97) {
                slowDownWeatherEffect = 0;
            }
        }
        renderMessage();
    });

    console.log(stage);
}

function checkForCollision(entity1, entity2) {
    return checkForCollisionOnLeftSide(entity1, entity2) ||
        checkForCollisionOnRightSide(entity1, entity2) ||
        checkForCollisionOnTopSide(entity1, entity2) ||
        checkForCollisionOnBottomSide(entity1, entity2);

}

function checkForCollisionOnLeftSide(entity1, entity2) {
    if (entity1.x + entity1.vx <= entity2.x) {
        return true;
    };
    return false;
}

function checkForCollisionOnRightSide(entity1, entity2) {
    if (entity1.x + entity1.width + entity1.vx >= entity2.width) {
        return true;
    }
    return false;
}

function checkForCollisionOnTopSide(entity1, entity2) {
    if (entity1.y + entity1.vy <= entity2.y) {
        return true;
    }
    return false;
}

function checkForCollisionOnBottomSide(entity1, entity2) {
    if (entity1.y + entity1.height + entity1.vy >= entity2.height) {
        return true;
    }
    return false;
}

function keyboardMovements() {
    //Capture the keyboard arrow keys
    let left = keyboard("ArrowLeft");
    let up = keyboard("ArrowUp");
    let right = keyboard("ArrowRight");
    let down = keyboard("ArrowDown");

    //Left arrow key `press` method
    left.press = () => {
        farmer.texture = leftTractorTexture;
        farmer.vx = -5;
        farmer.vy = 0;
        playTractorSound();
    };

    //Left arrow key `release` method
    left.release = () => {
        if (!right.isDown && farmer.vy === 0) {
            farmer.vx = 0;
            decreaseTractorVolume();
        }
    };

    //Up
    up.press = () => {
        farmer.vy = -5;
        farmer.vx = 0;
        playTractorSound();
    };
    up.release = () => {
        if (!down.isDown && farmer.vx === 0) {
            farmer.vy = 0;
            decreaseTractorVolume();
        }
    };

    //Right
    right.press = () => {
        farmer.texture = rightTractorTexture;
        farmer.vx = 5;
        farmer.vy = 0;
        playTractorSound();
    };
    right.release = () => {
        if (!left.isDown && farmer.vy === 0) {
            farmer.vx = 0;
            decreaseTractorVolume();
        }
    };

    //Down
    down.press = () => {
        farmer.vy = 5;
        farmer.vx = 0;
        playTractorSound();
    };
    down.release = () => {
        if (!up.isDown && farmer.vx === 0) {
            farmer.vy = 0;
            decreaseTractorVolume();
        }
    };
}

function getMemoryInfo() {
    console.log(window.performance.memory);
}

function getStage() {
    return stage;
}

function getCanvas() {
    return canvas;
}

function getFarmer() {
    return farmer;
}

function getMarket() {
    return market;
}

function getWidthRatio() {
    return widthRatio;
}

function getHeightRatio() {
    return heightRatio;
}

function getMaxWidth() {
    return MAX_WIDTH;
}

function resizeFarm() {
    getBackground().width = frameDiv.offsetWidth;
    getBackground().height = frameDiv.offsetHeight;

    if (frameDiv.offsetWidth >= 700) {
        widthRatio = 1;
    }

    if (frameDiv.offsetWidth >= 600 && frameDiv.offsetWidth < 700) {

    }



    canvas.width = frameDiv.offsetWidth;
    widthRatio = canvas.width < 700 ? canvas.width / previousWidth : 700 / 700;
    canvas.height = frameDiv.offsetHeight;
    heightRatio = canvas.height < 700 ? canvas.height / previousHeight : 700 / 700;

    stage.children.forEach(child => {
        if (child.name != "weather" || child.name != "background") {
            child.x *= widthRatio;
            child.y *= heightRatio;
            child.width *= widthRatio;
            child.height *= heightRatio;
            child.children.forEach(childOfChild => {
                childOfChild.x *= widthRatio;
                childOfChild.width *= widthRatio;
                childOfChild.y *= heightRatio;
                childOfChild.height *= heightRatio;
            });
        }
    });
    previousWidth = canvas.width;
    previousHeight = canvas.height;

}

window.addEventListener('resize', resizeFarm);

resizeFarm();

function getActiveRatio() {
    if (frameDiv.offsetWidth >= 700) {
        return 700;
    }

    if (frameDiv.offsetWidth >= 600 && frameDiv.offsetWidth < 700) {
        return 600;
    }

    if (frameDiv.offsetWidth >= 500 && frameDiv.offsetWidth < 600) {
        return 500;
    }

    if (frameDiv.offsetWidth < 500) {
        return 400;
    }
}

function getWidthRatio() {
    if (frameDiv.offsetWidth >= 700) {
        return {
            width: 700/getActiveRatio()
        }
    }

    if (frameDiv.offsetWidth >= 600 && frameDiv.offsetWidth < 700) {
        return {
            width: 600/getActiveRatio()
        }
    }

    if (frameDiv.offsetWidth >= 500 && frameDiv.offsetWidth < 600) {
        return {
            width: 500/getActiveRatio()
        }
    }

    if (frameDiv.offsetWidth < 500) {
        return {
            width: 400/getActiveRatio()
        }
    }
}