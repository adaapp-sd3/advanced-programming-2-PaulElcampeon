package com.paul.farm.tourism;

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
