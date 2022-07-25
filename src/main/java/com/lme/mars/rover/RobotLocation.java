package com.lme.mars.rover;

import java.util.Objects;

public class RobotLocation {

    private final int x;
    private final int y;

    public RobotLocation(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public RobotLocation moveLocation(int xMove, int yMove) {
        return new RobotLocation(x + xMove, y + yMove);
    }

    @Override
    public String toString() {
        return x + " " + y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        RobotLocation that = (RobotLocation) o;
        return x == that.x && y == that.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
