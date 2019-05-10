const dashboardDiv = document.getElementById("dashboard");
const dashBoard = new Dashboard();
const farm = new Farm();
const touristContainer = new PIXI.Container();
const farmContainer = new PIXI.Container();
var touristList = [];
const background = new PIXI.Graphics();


function renderDashboard(data) {
    if (dashBoard.rendered) {
        dashBoard.update(data);
        getStage().addChild(getMarket());
        getStage().addChild(getFarmer());
    } else {
        dashboardDiv.innerHTML = "";
        dashBoard.render(data);
        getStage().addChild(getMarket());
        getStage().addChild(getFarmer());
    }
}

function renderFarm(data) {
    farm.render(data);
}

function renderTourists() {
    try {
        getStage().removeChild(getTouristContainer());
        getTouristContainer().removeChildren(0, getTouristContainer().children.length);
    } catch (err) {
        console.log("Tried to remove tourist from container but there were none")
    }
    let visitor = 0;
    getTouristList().forEach(tourist => {
        getVisitors()[visitor].x = tourist.xpos * getResizeProperties().horizontalRatio;
        getVisitors()[visitor].y = tourist.ypos * getResizeProperties().horizontalRatio;
        getVisitors()[visitor].scale.set(getResizeProperties().visitors.scale);
        getTouristContainer().addChild(getVisitors()[visitor]);
        visitor++;
    });
    getStage().addChild(getTouristContainer());
}

function initBackground() {
    background.name = "background";
    background.beginFill(0x78d361);
    background.drawRect(0, 0, getCanvas().width, getCanvas().height);
    background.endFill();
    getStage().addChild(background);
}

function updateTractor() {
        getFarmer().scale.set(getResizeProperties().tractor.scale);
}

function saveTractorPosition(x, y) {
    getOriginalTractorPos().x = x;
    getOriginalTractorPos().y = y;
}

function updateMarket() {
    getMarket().x = getResizeProperties().horizontalRatio * getOriginalMarketPos().x;
    getMarket().y = getResizeProperties().horizontalRatio * getOriginalMarketPos().y;
    getMarket().scale.set(getResizeProperties().market.scale);
}

function getDashboardDiv() {
    return dashboardDiv;
}

function getDashboard() {
    return dashBoard;
}

function getFarm() {
    return farm;
}

function getBackground() {
    return background;
}

function getFarmContainer() {
    return farmContainer;
}

function getTouristContainer() {
    return touristContainer;
}

function getTouristList() {
    return touristList;
}

function setTouristList(data) {
    touristList = data;
}
