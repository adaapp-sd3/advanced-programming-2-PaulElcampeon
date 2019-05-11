const frameDiv = document.getElementById("farm");
const log = console.log;
const app = new PIXI.Application();
const canvas = app.view;
canvas.width = frameDiv.offsetWidth;
canvas.height = frameDiv.offsetHeight;
canvas.classList.add('forCanvas');
frameDiv.appendChild(canvas);
var activeWidth;
resizeFarm();
getResizeProperties();
var tractorMovement = {x:0, y:0, moveHorizontal:false, moveVertical:false, move: false};

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
        renderMessage();

        if (getTractorMovement().move) {
            moveTractor();
        }
    });

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

function getFrameDiv() {
    return frameDiv;
}

function getTractorMovement() {
    return tractorMovement;
}

function resizeFarm() {
    canvas.width = frameDiv.offsetWidth;
    canvas.height = frameDiv.offsetHeight;
    getBackground().width = frameDiv.offsetWidth;
    getBackground().height = frameDiv.offsetHeight;
    updateTractor();
    updateMarket();
}

function getResizeProperties() {
    if (frameDiv.offsetWidth >= 700) {
        activeWidth = 700;
        return {
            width: 700,
            horizontalRatio: 700 / 700,
            field: {width: 300, height: 200},
            market: {scale: 1},
            tractor: {scale: 0.3},
            animals: {scale: {cow: 0.4, sheep: 0.3, chicken: 0.2}},
            visitors: {scale: 0.4},
            crops: {scale: 0.3},
            messages: {scale: 1}
        }
    } else if (frameDiv.offsetWidth >= 600 && frameDiv.offsetWidth < 700) {
        activeWidth = 600;
        return {
            width: 600,
            horizontalRatio: 600 / 700,
            field: {width: 257, height: 171},
            market: {scale: 0.85},
            tractor: {scale: 0.26},
            animals: {scale: {cow: 0.34, sheep: 0.255, chicken: 0.17}},
            visitors: {scale: 0.34},
            crops: {scale: 0.255},
            messages: {scale: 0.85}
        }
    } else if (frameDiv.offsetWidth >= 500 && frameDiv.offsetWidth < 600) {
        activeWidth = 500;
        return {
            width: 500,
            horizontalRatio: 500 / 700,
            field: {width: 213, height: 142},
            market: {scale: 0.71},
            tractor: {scale: 0.21},
            animals: {scale: {cow: 0.28, sheep: 0.21, chicken: 0.14}},
            visitors: {scale: 0.28},
            crops: {scale: 0.21},
            messages: {scale: 0.71}
        }
    } else if (frameDiv.offsetWidth >= 400 && frameDiv.offsetWidth < 500){ //(frameDiv.offsetWidth < 500)
        activeWidth = 400;
        return {
            width: 400,
            horizontalRatio: 400 / 700,
            field: {width: 171, height: 114},
            market: {scale: 0.57},
            tractor: {scale: 0.17},
            animals: {scale: {cow: 0.23, sheep: 0.17, chicken: 0.11}},
            visitors: {scale: 0.23},
            crops: {scale: 0.17},
            messages: {scale: 0.57}
        }
    } else {
         activeWidth = 300;
         return {
            width: 300,
            horizontalRatio: 300 / 700,
            field: {width: 128, height: 85},
            market: {scale: 0.42},
            tractor: {scale: 0.12},
            animals: {scale: {cow: 0.16, sheep: 0.12, chicken: 0.08}},
            visitors: {scale: 0.16},
            crops: {scale: 0.12},
            messages: {scale: 0.42}
         }
    }
}

window.addEventListener('resize', resizeFarm);

stage.interactive = true;

//for devices with touch screen
stage.on('tap', (event) => {
    getTractorMovement().x = Math.round(event.data.global.x);
    getTractorMovement().y = Math.round(event.data.global.y);
    getTractorMovement().moveHorizontal = true;
    getTractorMovement().moveVertical = true;
    getTractorMovement().move = true;
})

stage.on('click', (event) => {
    getTractorMovement().x = Math.round(event.data.global.x);
    getTractorMovement().y = Math.round(event.data.global.y);
    getTractorMovement().moveHorizontal = true;
    getTractorMovement().moveVertical = true;
    getTractorMovement().move = true;
})

