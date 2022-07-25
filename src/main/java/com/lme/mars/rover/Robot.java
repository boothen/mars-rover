package com.lme.mars.rover;

import java.util.Scanner;

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

    public Robot(Mars mars, String initialLocationAndOrientation) {
        this(mars,
             new RobotLocation(Integer.parseInt(initialLocationAndOrientation.split(" ")[0]),
                               Integer.parseInt(initialLocationAndOrientation.split(" ")[1])),
             Orientation.valueOf(initialLocationAndOrientation.split(" ")[2]));
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

    public void executeInstructionSet(String instructionSet) {
        try (Scanner scanner = new Scanner(instructionSet)) {
            scanner.useDelimiter("");
            while (scanner.hasNext()) {
                executeInstruction(RobotInstruction.valueOf(scanner.next()));
            }
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
