const messageContainer = new PIXI.Container();
const messageText = new PIXI.Text();
var messageTimeLength = 0;

var style = new PIXI.TextStyle({
        fontFamily: 'Arial',
        fontSize: 24,
        align: 'center',
        fontStyle: 'normal',
        fontWeight: 'bold',
        fill: ['#ffffff', '#00ff99'], // gradient
        stroke: '#4a1850',
        strokeThickness: 5,
        dropShadow: true,
        dropShadowColor: '#000000',
        dropShadowBlur: 4,
        dropShadowAngle: Math.PI / 6,
        dropShadowDistance: 6,
        wordWrap: true,
        wordWrapWidth: 690
    });

function getMessageContainer() {
    return messageContainer;
}

function getMessageText() {
    return messageText;
}

function setMessage(newMessage) {
    try {
        getMessageContainer().removeChildren(0, getMessageContainer().children);
    } catch (err) {
        console.log("opps message container has no messages");
    }
    messageTimeLength = Date.now() + 5000;//message length will be 5 seconds
    createMessage(newMessage);
    getStage().addChild(getMessageContainer());//add the message once
}

function renderMessage() {
    if (Date.now() > getMessageTimeLength()) { //when time is up we remove the message from canvas;
        try {
            getStage().removeChild(getMessageContainer());
        } catch(err) {
            console.log("stage does not have the message container")
        }
    } else {
        getStage().removeChild(getMessageContainer());
        getStage().addChild(getMessageContainer());//add the message once
    }
}

function getMessageTimeLength() {
    return messageTimeLength;
}

function createMessage(message) {
    getMessageText().text = message;
    getMessageText().style = getTextStyle();
    getMessageText().scale.set(getResizeProperties().messages.scale);
    getMessageContainer().x = (getFrameDiv().offsetWidth - getMessageText().width)/2;
    getMessageContainer().y = (getFrameDiv().offsetHeight - getMessageText().height)/2;
    getMessageContainer().addChild(getMessageText());
}

function getTextStyle() {
    return style;
}