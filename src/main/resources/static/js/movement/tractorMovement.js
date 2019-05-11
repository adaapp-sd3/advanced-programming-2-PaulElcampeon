function moveTractor() {
    if (getTractorMovement().moveHorizontal) {
        if (getTractorMovement().x < getFarmer().x) {
            if (getFarmer().x >= getTractorMovement().x) {
                getFarmer().texture = getLeftTractorTexture();
                getFarmer().vx = -5;
            } else {
                getTractorMovement().moveHorizontal = false;
                getFarmer().vx = 0;
            }
        } else {
            if (getFarmer().x + 5 <= getTractorMovement().x) {
                getFarmer().texture = getRightTractorTexture();
                getFarmer().vx = 5;
            } else {
                getTractorMovement().moveHorizontal = false;
                getFarmer().vx = 0;
            }
        }
    }

    if (!getTractorMovement().moveHorizontal) {
        if (getTractorMovement().moveVertical) {
            if (getTractorMovement().y < getFarmer().y) {
                if (getFarmer().y >= getTractorMovement().y) {
                    getFarmer().vy = -5;
                } else {
                    getTractorMovement().moveVertical = false;
                    getFarmer().vy = 0;
                }
            } else {
                if (getFarmer().y + getFarmer().height/2 <= getTractorMovement().y) {
                    getFarmer().vy = 5;
                } else {
                    getTractorMovement().moveVertical = false;
                    getFarmer().vy = 0;
                }
            }
        }
    }

    if (getTractorMovement().moveHorizontal == false && getTractorMovement().moveVertical == false) {
        getTractorMovement().move = false;
    }
}