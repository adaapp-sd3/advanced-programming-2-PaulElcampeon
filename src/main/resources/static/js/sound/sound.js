var tractorSound = new Howl({
  src: ['../../sound/tractor.mp3'],
  loop: true,
  volume: 0.2,
   sprite: {
      tractor: [4000, 8000]
    }
});

var marketSound = new Howl({
  src: ['../../sound/market.mp3'],
  loop: true,
  volume: 0.2
});

var cowSound = new Howl({
  src: ['../../sound/cow.mp3'],
  loop: true,
  volume: 0.2
});

var sheepSound = new Howl({
  src: ['../../sound/sheep.mp3'],
  loop: true,
  volume: 0.2
});

var chickenSound = new Howl({
  src: ['../../sound/chicken.mp3'],
  loop: true,
  volume: 0.2
});

var clearSound = new Howl({
  src: ['../../sound/clear.mp3'],
  loop: true,
  volume: 0.2
});

var rainSound = new Howl({
  src: ['../../sound/rain.mp3'],
  loop: true,
  volume: 0.2
});

function playTractorSound() {
    tractorSound.fade(0, 0.2, 1000);
    tractorSound.play('tractor');
}

function decreaseTractorVolume() {
    tractorSound.fade(0.2, 0, 1000);
}

function playCowSound() {
    cowSound.play();
}

function decreaseCowVolume() {
    cowSound.fade(0.2, 0, 1000);
    cowSound.stop();
}

function playSheepSound() {
    sheepSound.play();
}

function decreaseSheepVolume() {
    sheepSound.fade(0.2, 0, 1000);
    sheepSound.stop();
}

function playChickenSound() {
    chickenSound.play();
}

function decreaseChickenVolume() {
    chickenSound.fade(0.2, 0, 1000);
    chickenSound.stop();
}

function playMarketSound() {
    marketSound.play();
}

function decreaseMarketVolume() {
    marketSound.fade(0.2, 0, 1000);
    marketSound.stop();
}

function playClearSound() {
    clearSound.play();
}

function decreaseClearVolume() {
    clearSound.fade(0.2, 0, 1000);
    clearSound.stop();
}

function playRainSound() {
    rainSound.play();
}

function decreaseRainVolume() {
    rainSound.fade(0.2, 0, 1000);
    rainSound.stop();
}

function playAnimalSounds(data) {
    if (data.totalChickens > 0) {
        if(!chickenSound.playing()) {
            playChickenSound();
        }
    } else {
        if (chickenSound.playing()) {
            decreaseChickenVolume();
        }
    }

    if (data.totalCows > 0) {
        if (!cowSound.playing()) {
            playCowSound();
        }
    } else {
        if (cowSound.playing()) {
            decreaseCowVolume();
        }
    }

    if (data.totalSheep > 0) {
        if (!sheepSound.playing()) {
            playSheepSound();
        }
    } else {
        if (sheepSound.playing()) {
            decreaseSheepVolume();
        }
    }
}

function getMarketSound() {
    return marketSound;
}