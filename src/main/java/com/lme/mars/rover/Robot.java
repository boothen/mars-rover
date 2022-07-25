package com.lme.mars.rover;

public class Robot {

    public static final String LOST = " LOST";
    private final Mars mars;
    private RobotLocation robotLocation;
    private Orientation orientation;
    private boolean lost;

    public Robot(Mars mars, RobotLocation initialLocation, Orientation initialOrientation) {
        this.mars = mars;
        robotLocation = initialLocation;
        orientation = initialOrientation;
    }

    public void executeInstruction(RobotInstruction instruction) {
        if (lost) {
            return;
        }
        switch (instruction) {
            case R, L -> orientation = orientation.turn(instruction);
            case F -> executeMove();
        }
    }

    private void executeMove() {
        if (mars.hasScent(robotLocation, orientation)) {
            return;
        }

        try {
            robotLocation = mars.move(robotLocation, orientation);
        } catch (RobotOffGridException e) {
            mars.leaveScent(robotLocation, orientation);
            lost = true;
        }
    }

    @Override
    public String toString() {
        return robotLocation.toString() + " " + orientation.toString() + (lost ? LOST : "");
    }
}
