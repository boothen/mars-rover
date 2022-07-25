package com.lme.mars.rover;

import java.util.HashSet;
import java.util.Set;

public class Mars {

    private record RobotLocationWithOrientation(RobotLocation robotLocation, Orientation orientation) {

    }

    private final int x;
    private final int y;
    private final Set<RobotLocationWithOrientation> robotScentTracker = new HashSet<>();

    public Mars(int x, int y) {
        if (x < 0 || x > 50 || y < 0 || y > 50) {
            throw new IllegalArgumentException(String.format("Invalid x or y value, x:%d, y:%d", x, y));
        }
        this.x = x;
        this.y = y;
    }

    public Mars(String coordinates) {
        this(Integer.parseInt(coordinates.split(" ")[0]),
             Integer.parseInt(coordinates.split(" ")[1]));
    }

    public boolean hasScent(RobotLocation robotLocation, Orientation orientation) {
        return robotScentTracker.contains(new RobotLocationWithOrientation(robotLocation, orientation));
    }

    public RobotLocation move(RobotLocation robotLocation, Orientation orientation) throws RobotOffGridException {
        RobotLocation newLocation = orientation.move(robotLocation);
        if (newLocation.offGrid(x, y)) {
            throw new RobotOffGridException();
        }

        return newLocation;
    }

    public void leaveScent(RobotLocation robotLocation, Orientation orientation) {
        robotScentTracker.add(new RobotLocationWithOrientation(robotLocation, orientation));
    }
}
