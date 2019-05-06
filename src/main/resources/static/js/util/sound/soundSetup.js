const tractorSound = new Howl({
  src: ['../../sound/vehicle/tractor.mp3'],
  loop: true,
  volume: 0.2,
   sprite: {
      tractor: [4000, 8000]
    }
});

const marketSound = new Howl({
  src: ['../../sound/market/market.mp3'],
  loop: true,
  volume: 0.2
});

const cowSound = new Howl({
  src: ['../../sound/animals/cow.mp3'],
  loop: true,
  volume: 0.2
});

const sheepSound = new Howl({
  src: ['../../sound/animals/sheep.mp3'],
  loop: true,
  volume: 0.2
});

const chickenSound = new Howl({
  src: ['../../sound/animals/chicken.mp3'],
  loop: true,
  volume: 0.2
});

const clearWeatherSound = new Howl({
  src: ['../../sound/weather/clear.mp3'],
  loop: true,
  volume: 0.2
});

const rainWeatherSound = new Howl({
  src: ['../../sound/weather/rain.mp3'],
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

function playClearWeatherSound() {
    clearWeatherSound.play();
}

function decreaseClearVolume() {
    clearWeatherSound.fade(0.2, 0, 1000);
    clearWeatherSound.stop();
}

function playRainWeatherSound() {
    rainWeatherSound.play();
}

function decreaseRainVolume() {
    rainWeatherSound.fade(0.2, 0, 1000);
    rainWeatherSound.stop();
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