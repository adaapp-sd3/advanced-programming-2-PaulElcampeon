function produceSnow() {
    try {
        getStage().removeChild(getWeatherContainer());
        getWeatherContainer().removeChildren(0, getWeatherContainer().children.length);
    } catch (err) {
        console.log("Rain container has no children opps");
    }
    let snowDrops = [];
    let noOfSnowDrops = Math.floor((Math.random() * 50) + 20);//between 20-100
    for (let i = 0; i < noOfSnowDrops; i++) {
        let graphics = new PIXI.Graphics();
        let opacityOfSnow = Math.random();
        graphics.lineStyle(2, 0xFFFFFF, opacityOfSnow);
        let x = Math.floor((Math.random() * getCanvas().width - 10) + 0);
        let y = Math.floor((Math.random() * getCanvas().height - 10) + 0);
        graphics.beginFill(0xFFFFFF, opacityOfSnow);
        graphics.drawStar(x, y, Math.floor((Math.random() * 5) + 4), Math.floor((Math.random() * 5) + 3));
        getWeatherContainer().addChild(graphics);
    }

    getStage().addChild(getWeatherContainer());
}