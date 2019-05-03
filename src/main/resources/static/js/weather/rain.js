function produceRain() {
    try {
        getStage().removeChild(getWeatherContainer());
        getWeatherContainer().removeChildren(0, getWeatherContainer().children.length);
    } catch (err) {
        console.log("Rain container has no children opps");
    }
    let rainDrops = [];
    let noOfRainDrops = Math.floor((Math.random() * 50) + 20);//between 20-100
    for (let i = 0; i < noOfRainDrops; i++) {
        let graphics = new PIXI.Graphics();
        graphics.lineStyle(2, 0xD3D3D3, 1);
        let x = Math.floor((Math.random() * getCanvas().width - 10) + 0);
        let y = Math.floor((Math.random() * getCanvas().height - 10) + 0);
        graphics.moveTo(x, y);
        graphics.lineTo(x+5, y+5);
        getWeatherContainer().addChild(graphics);
    }

    getStage().addChild(getWeatherContainer());
}