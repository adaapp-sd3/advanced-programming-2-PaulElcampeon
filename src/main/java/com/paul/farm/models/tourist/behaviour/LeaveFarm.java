package com.paul.farm.models.tourist.behaviour;

import com.paul.farm.models.tourist.Tourist;
import lombok.Data;

@Data
public class LeaveFarm {

    public boolean leaveIfHaveTo(Tourist tourist) {
        if (tourist.getTimeArrived() != 0 && !tourist.getFarmName().equals("")) {
            if (System.currentTimeMillis() - tourist.getTimeArrived() >= tourist.getTimeSpentVisiting()) {
                return true;
            }
            return false;
        }
        return false;
    }

}
