function keyboard(value) {
  let key = {};
  key.value = value;
  key.isDown = false;
  key.isUp = true;
  key.press = undefined;
  key.release = undefined;
  //The `downHandler`
  key.downHandler = event => {
    if (event.key === key.value) {
      if (key.isUp && key.press) key.press();
      key.isDown = true;
      key.isUp = false;
      event.preventDefault();
    }
  };

  //The `upHandler`
  key.upHandler = event => {
    if (event.key === key.value) {
      if (key.isDown && key.release) key.release();
      key.isDown = false;
      key.isUp = true;
      event.preventDefault();
    }
  };

  //Attach event listeners
  const downListener = key.downHandler.bind(key);
  const upListener = key.upHandler.bind(key);

  window.addEventListener(
    "keydown", downListener, false
  );
  window.addEventListener(
    "keyup", upListener, false
  );

  // Detach event listeners
  key.unsubscribe = () => {
    window.removeEventListener("keydown", downListener);
    window.removeEventListener("keyup", upListener);
  };

  return key;
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
            saveTractorPosition(getFarmer().x, getFarmer().y);
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
            saveTractorPosition(getFarmer().x, getFarmer().y);
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
            saveTractorPosition(getFarmer().x, getFarmer().y);
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
            saveTractorPosition(getFarmer().x, getFarmer().y);
            decreaseTractorVolume();
        }
    };
}