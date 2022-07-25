package com.lme.mars.rover;


import java.util.List;

import static com.lme.mars.rover.RobotInstruction.RIGHT;

public enum Orientation {

    NORTH,
    EAST,
    SOUTH,
    WEST;

    private static final List<Orientation> ORIENTATION_LIST = List.of(NORTH, EAST, SOUTH, WEST);

    public Orientation turn(RobotInstruction robotInstruction) {
        int orientationPosition = ordinal();
        orientationPosition += robotInstruction == RIGHT ? 1 : -1;
        if (orientationPosition < 0) {
            return WEST;
        }
        if (orientationPosition > 3) {
            return NORTH;
        }
        return ORIENTATION_LIST.get(orientationPosition);
    }

}
