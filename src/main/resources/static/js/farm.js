const frameDiv = document.getElementById("farm");
const log = console.log;
const app = new PIXI.Application();
const canvas = app.view;
canvas.width = 700;
canvas.height =  window.innerHeight;
canvas.classList.add('forCanvas');
frameDiv.appendChild(canvas);

const stage = app.stage;
const loader = PIXI.loader;
const resources = loader.resources;

PIXI.loader.load(setup);

var farmer, market, leftTractorTexture, rightTractorTexture, marketTexture;

function setup() {
    initBackground();
    connect();
    setUpSprites();
    keyboardMovements();
    let slowDownWeatherEffect = 0
    getCurrentWeather();
    app.ticker.add((delta) => {

        if (!checkForCollision(farmer, stage)) {
            farmer.x += farmer.vx;
            farmer.y += farmer.vy;
        }
        slowDownWeatherEffect++;
        if (slowDownWeatherEffect%6 == 0) {
            produceWeather();
            if (slowDownWeatherEffect == 97) {
                slowDownWeatherEffect = 0;
            }
        }
        renderMessage();
    });
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

function setUpSprites() {
    leftTractorTexture = new PIXI.Texture.from("../images/farmerLeft.png");
    rightTractorTexture = new PIXI.Texture.from("../images/farmerRight.png");
    marketTexture = new PIXI.Texture.from("../images/building/market.png");
    farmer = new PIXI.Sprite(leftTractorTexture);
    market = new PIXI.Sprite(marketTexture);
    market.name = "market";
    farmer.name = "farmer";
    market.x = 550;
    market.y = 650;
    farmer.x = 100;
    farmer.y = 100;
    farmer.vx = 0;
    farmer.vy = 0;
    farmer.scale.set(0.3);
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
    };

    //Left arrow key `release` method
    left.release = () => {
        if (!right.isDown && farmer.vy === 0) {
            farmer.vx = 0;
        }
    };

    //Up
    up.press = () => {
        farmer.vy = -5;
        farmer.vx = 0;
    };
    up.release = () => {
        if (!down.isDown && farmer.vx === 0) {
            farmer.vy = 0;
        }
    };

    //Right
    right.press = () => {
        farmer.texture = rightTractorTexture;
        farmer.vx = 5;
        farmer.vy = 0;
    };
    right.release = () => {
        if (!left.isDown && farmer.vy === 0) {
            farmer.vx = 0;
        }
    };

    //Down
    down.press = () => {
        farmer.vy = 5;
        farmer.vx = 0;
    };
    down.release = () => {
        if (!up.isDown && farmer.vx === 0) {
            farmer.vy = 0;
        }
    };
}