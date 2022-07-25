package com.lme.mars.rover;


import java.util.List;

import static com.lme.mars.rover.RobotInstruction.RIGHT;

public enum Orientation {

    NORTH(0, 1),
    EAST(1, 0),
    SOUTH(0, -1),
    WEST(-1, 0);

    private static final List<Orientation> ORIENTATION_LIST = List.of(NORTH, EAST, SOUTH, WEST);
    private final int xMove;
    private final int yMove;

    Orientation(int xMove, int yMove) {
        this.xMove = xMove;
        this.yMove = yMove;
    }

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

    public RobotLocation move(RobotLocation currentLocation) {
        return currentLocation.moveLocation(this.xMove, this.yMove);
    }

}
