package com.lme.mars.rover;


import java.util.List;

import static com.lme.mars.rover.RobotInstruction.R;

/**
 * RobotOrientation - (N, S, E, W for north, south, east, and west).
 *
 * Calculates new RobotOrientation for a turn and new RobotLocation for a move.
 */
public enum RobotOrientation {

    N(0, 1),
    E(1, 0),
    S(0, -1),
    W(-1, 0);

    private static final List<RobotOrientation> ORIENTATION_LIST = List.of(N, E, S, W);
    private final int xMove;
    private final int yMove;

    RobotOrientation(int xMove, int yMove) {
        this.xMove = xMove;
        this.yMove = yMove;
    }

    public RobotOrientation turn(RobotInstruction robotInstruction) {
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
