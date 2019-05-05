const rainAnimations = [];
const snowAnimations = [];

function getRainAnimations() {
    return rainAnimations;
}

function getSnowAnimations() {
    return snowAnimations;
}


function createRainAnimation() {
    for (let i = 0; i < 20; i++) { //we basically create 20 frames of rain animations
        let rainDropContainer = new PIXI.Container();
        let noOfRainDrops = Math.floor((Math.random() * 50) + 20);//between 20-100
        for (let i = 0; i < noOfRainDrops; i++) {
            let graphics = new PIXI.Graphics();
            graphics.lineStyle(2, 0xD3D3D3, 1);
            let x = Math.floor((Math.random() * getCanvas().width - 10) + 0);
            let y = Math.floor((Math.random() * getCanvas().height - 10) + 0);
            graphics.moveTo(x, y);
            graphics.lineTo(x+5, y+5);
            rainDropContainer.addChild(graphics);
        }
        getRainAnimations().push(rainDropContainer);
    }
}

function createSnowAnimation() {
    for (let i = 0; i < 20; i++) { //we basically create 20 frames of rain animations
        let snowDropContainer = new PIXI.Container();
        let noOfSnowDrops = Math.floor((Math.random() * 50) + 20);//between 20-100
        for (let i = 0; i < noOfSnowDrops; i++) {
            let graphics = new PIXI.Graphics();
            let opacityOfSnow = Math.random();
            graphics.lineStyle(2, 0xFFFFFF, opacityOfSnow);
            let x = Math.floor((Math.random() * getCanvas().width - 10) + 0);
            let y = Math.floor((Math.random() * getCanvas().height - 10) + 0);
            graphics.beginFill(0xFFFFFF, opacityOfSnow);
            graphics.drawStar(x, y, Math.floor((Math.random() * 5) + 4), Math.floor((Math.random() * 5) + 3));
            snowDropContainer.addChild(graphics);
        }
        getSnowAnimations().push(snowDropContainer);
    }
}