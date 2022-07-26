package com.lme.mars.rover;

/**
 * RobotLocation on Mars
 *
 * @param x
 * @param y
 */
public record RobotLocation(int x, int y) {

    public RobotLocation moveLocation(int xMove, int yMove) {
        return new RobotLocation(x + xMove, y + yMove);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    public boolean offGrid(int xMax, int yMax) {
        return x < 0 || x > xMax || y < 0 || y > yMax;
    }
}
