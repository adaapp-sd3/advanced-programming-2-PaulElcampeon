const frameDiv = document.getElementById("farm");
const log = console.log;
const app = new PIXI.Application();
const canvas = app.view;
canvas.width = frameDiv.offsetWidth;
canvas.height = window.innerHeight;
canvas.classList.add('forCanvas');
frameDiv.appendChild(canvas);
const MAX_WIDTH = 700;
const MAX_HEIGHT = 700;
var widthRatio = canvas.width < 700 ? canvas.width / MAX_WIDTH : 700 / 700;
var previousWidth = canvas.width;


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

        if (!checkForCollision(farmer, stage)) {
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

function checkForCollision(entity1, entity2) {
    return checkForCollisionOnLeftSide(entity1, entity2) ||
        checkForCollisionOnRightSide(entity1, entity2) ||
        checkForCollisionOnTopSide(entity1, entity2) ||
        checkForCollisionOnBottomSide(entity1, entity2);

}

function checkForCollisionOnLeftSide(entity1, entity2) {
    if (entity1.x + entity1.vx <= entity2.x) {
        return true
    }
    ;
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

function getWidthRation() {
    return widthRatio;
}

function getMaxWidth() {
    return MAX_WIDTH;
}

function resizeFarm() {


}


window.onresize = function (event) {
    canvas.width = frameDiv.offsetWidth;
    widthRatio = canvas.width < 700 ? canvas.width / previousWidth : 700 / 700;
    stage.children.forEach(child => {
        if (child.name != "weather") {
            child.x *= widthRatio;
            child.width *= widthRatio;
            child.children.forEach(childOfChild => {
                childOfChild.x *= widthRatio;
                childOfChild.width *= widthRatio;
            });
        }
    });
    previousWidth = canvas.width;
};