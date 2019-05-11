function checkForCollision(entity1, entity2) {
    return checkForCollisionOnLeftSide(entity1, entity2) ||
        checkForCollisionOnRightSide(entity1, entity2) ||
        checkForCollisionOnTopSide(entity1, entity2) ||
        checkForCollisionOnBottomSide(entity1, entity2);
}

function checkForCollisionOnLeftSide(entity1, entity2) {
    if (entity1.x - entity1.width/2 + entity1.vx <= entity2.x) {
        getTractorMovement().moveHorizontal = false;
        getFarmer().vx = 0;
        return true;
    }
    return false;
}

function checkForCollisionOnRightSide(entity1, entity2) {
    if (entity1.x + entity1.width/2 + entity1.vx >= entity2.width) {
        getTractorMovement().moveHorizontal = false;
        getFarmer().vx = 0;
        return true;
    }
    return false;
}

function checkForCollisionOnTopSide(entity1, entity2) {
    if (entity1.y - entity1.height/2 + entity1.vy <= entity2.y) {
        getTractorMovement().moveVertical = false;
        getFarmer().vy = 0;
        return true;
    }
    return false;
}

function checkForCollisionOnBottomSide(entity1, entity2) {
    if (entity1.y + entity1.height/2 + entity1.vy >= entity2.height) {
        getTractorMovement().moveVertical = false;
        getFarmer().vy = 0;
        return true;
    }
    return false;
}