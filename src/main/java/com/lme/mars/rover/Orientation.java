package com.lme.mars.rover;


import java.util.List;

import static com.lme.mars.rover.RobotInstruction.R;

public enum Orientation {

    N(0, 1),
    E(1, 0),
    S(0, -1),
    W(-1, 0);

    private static final List<Orientation> ORIENTATION_LIST = List.of(N, E, S, W);
    private final int xMove;
    private final int yMove;

    Orientation(int xMove, int yMove) {
        this.xMove = xMove;
        this.yMove = yMove;
    }

    public Orientation turn(RobotInstruction robotInstruction) {
        int orientationPosition = ordinal();
        orientationPosition += robotInstruction == R ? 1 : -1;
        if (orientationPosition < 0) {
            return W;
        }
        if (orientationPosition > 3) {
            return N;
        }
        return ORIENTATION_LIST.get(orientationPosition);
    }

    public RobotLocation move(RobotLocation currentLocation) {
        return currentLocation.moveLocation(xMove, yMove);
    }

}
