var dashboardDiv = document.getElementById("dashboard");


const dashBoard = new Dashboard();
const farm = new Farm();

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

function getDashboardDiv() {
    return dashboardDiv;
}

function getDashboard() {
    return dashBoard;
}

function getFarm() {
    return farm;
}

function initBackground() {
    let background = new PIXI.Graphics();
    background.name = "background";
    background.beginFill(0x78d361);
    background.drawRect(0, 0, getCanvas().width, getCanvas().height);
    background.endFill();
    getStage().addChild(background);
}
