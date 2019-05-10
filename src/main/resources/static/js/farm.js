const frameDiv = document.getElementById("farm");
const log = console.log;
const app = new PIXI.Application();
const canvas = app.view;
canvas.width = frameDiv.offsetWidth;
canvas.height = frameDiv.offsetHeight;
canvas.classList.add('forCanvas');
frameDiv.appendChild(canvas);
var activeWidth;
var widthChangeHistory = [];
setStarterWidthInArray();
getResizeProperties();
resizeFarm();

const stage = app.stage;

//preload stuff
Promise.all([createFields(), createAnimals(), createCrops(), createVisitors(), createRainAnimation(), createSnowAnimation()])
    .then(() => {
        initBackground();
        createTractorAndMarketSprite();
        connect();
        setup();
    });

function setup() {

    keyboardMovements();

    let slowDownWeatherEffect = 0;

    window.setInterval(getMemoryInfo, 300000);//get memory info every 5 mins

    getCurrentWeather();
    window.setInterval(getCurrentWeather, 600000);//get weather data every 10 mins

    app.ticker.add((delta) => {

        if (!checkForCollision(getFarmer(), getBackground())) {
            getFarmer().x += getFarmer().vx;
            getFarmer().y += getFarmer().vy;
        }
        slowDownWeatherEffect++;
        if (slowDownWeatherEffect % 6 == 0) {
            produceWeather();
            if (slowDownWeatherEffect == 97) {
                slowDownWeatherEffect = 0;
            }
        }
        saveTractorPosition();

        renderMessage();
    });
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
        getFarmer().texture = getLeftTractorTexture();
        getFarmer().vx = -5;
        getFarmer().vy = 0;
        playTractorSound();
    };
    //Left arrow key `release` method
    left.release = () => {
        if (!right.isDown && getFarmer().vy === 0) {
            getFarmer().vx = 0;
            decreaseTractorVolume();
        }
    };

    //Up
    up.press = () => {
        getFarmer().vy = -5;
        getFarmer().vx = 0;
        playTractorSound();
    };
    up.release = () => {
        if (!down.isDown && getFarmer().vx === 0) {
            getFarmer().vy = 0;
            decreaseTractorVolume();
        }
    };

    //Right
    right.press = () => {
        getFarmer().texture = getRightTractorTexture();
        getFarmer().vx = 5;
        getFarmer().vy = 0;
        playTractorSound();
    };
    right.release = () => {
        if (!left.isDown && getFarmer().vy === 0) {
            getFarmer().vx = 0;
            decreaseTractorVolume();
        }
    };

    //Down
    down.press = () => {
        getFarmer().vy = 5;
        getFarmer().vx = 0;
        playTractorSound();
    };
    down.release = () => {
        if (!up.isDown && getFarmer().vx === 0) {
            getFarmer().vy = 0;
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

function getActiveWidth() {
    return activeWidth;
}

function resizeFarm() {
    canvas.width = frameDiv.offsetWidth;
    canvas.height = frameDiv.offsetHeight;
    getBackground().width = frameDiv.offsetWidth;
    getBackground().height = frameDiv.offsetHeight;

}

function getResizeProperties() {
    if (widthChangeHistory.length > 2) {
        let howManyToRemove = widthChangeHistory.length - 2;
        widthChangeHistory.splice(0, howManyToRemove);
    }

    if (frameDiv.offsetWidth >= 700) {
        widthChangeHistory.push(700);
        activeWidth = 700;
        return {
            width: 700,
            horizontalRatio: 700 / 700,
            field: {width: 300, height: 200},
            market: {scale: 1},
            tractor: {scale: 0.3},
            animals: {scale: {cow: 0.4, sheep: 0.3, chicken: 0.2}},
            visitors: {scale: 0.4},
            crops: {scale: 0.3}
        }
    } else if (frameDiv.offsetWidth >= 600 && frameDiv.offsetWidth < 700) {
        widthChangeHistory.push(600);
        activeWidth = 600;
        return {
            width: 600,
            horizontalRatio: 600 / 700,
            field: {width: 257, height: 171},
            market: {scale: 0.85},
            tractor: {scale: 0.26},
            animals: {scale: {cow: 0.34, sheep: 0.255, chicken: 0.17}},
            visitors: {scale: 0.34},
            crops: {scale: 0.255}
        }
    } else if (frameDiv.offsetWidth >= 500 && frameDiv.offsetWidth < 600) {
        widthChangeHistory.push(500);
        activeWidth = 500;
        return {
            width: 500,
            horizontalRatio: 500 / 700,
            field: {width: 213, height: 142},
            market: {scale: 0.71},
            tractor: {scale: 0.21},
            animals: {scale: {cow: 0.28, sheep: 0.21, chicken: 0.14}},
            visitors: {scale: 0.28},
            crops: {scale: 0.21}
        }
    } else { //(frameDiv.offsetWidth < 500)
        widthChangeHistory.push(400);
        activeWidth = 400;
        return {
            width: 400,
            horizontalRatio: 400 / 700,
            field: {width: 171, height: 114},
            market: {scale: 0.57},
            tractor: {scale: 0.17},
            animals: {scale: {cow: 0.23, sheep: 0.17, chicken: 0.11}},
            visitors: {scale: 0.23},
            crops: {scale: 0.17}
        }
    }
}

function hasWidthsChange() {
    console.log("checking if widths have changed");
    console.log(widthChangeHistory.length);
    console.log(widthChangeHistory);
    let latest = widthChangeHistory[widthChangeHistory.length - 1];
    let oldest = widthChangeHistory[widthChangeHistory.length - 2];
    console.log(latest);
    console.log(oldest);

    if (latest != oldest) {
        return true
    }
    return false;
}

function setStarterWidthInArray() {
    if (frameDiv.offsetWidth >= 700) {
        widthChangeHistory.push(700);
    } else if (frameDiv.offsetWidth >= 600 && frameDiv.offsetWidth < 700) {
        widthChangeHistory.push(600);
    } else if (frameDiv.offsetWidth >= 500 && frameDiv.offsetWidth < 600) {
        widthChangeHistory.push(500);
    } else { //(frameDiv.offsetWidth < 500)
        widthChangeHistory.push(400);
    }
}

window.addEventListener('resize', resizeFarm);
