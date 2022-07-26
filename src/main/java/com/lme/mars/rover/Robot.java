package com.lme.mars.rover;

import java.util.Map;
import java.util.Scanner;

/**
 * Robot that receives instructions and execute commands from those instructions.
 *
 * Keeps track of its current location and orientation and whether it has become lost.
 *
 * Once a Robot has become lost all subsequent instructions are not processed.
 *
 * N.B Adding a new RobotInstruction will require a new entry adding to INSTRUCTION_COMMAND_MAP
 * and either a new command or an update to an existing one.
 */
public class Robot {

    private static final String LOST = " LOST";

    private static final Map<RobotInstruction, Command> INSTRUCTION_COMMAND_MAP = Map.of(RobotInstruction.R, new TurnCommand(),
                                                                                         RobotInstruction.L, new TurnCommand(),
                                                                                         RobotInstruction.F, new MoveCommand());
    private final Mars mars;
    private RobotLocation currentLocation;
    private RobotOrientation currentOrientation;
    private boolean lost;

    public Robot(Mars mars, RobotLocation initialLocation, RobotOrientation initialOrientation) {
        this.mars = mars;
        currentLocation = initialLocation;
        currentOrientation = initialOrientation;
    }

    public Robot(Mars mars, String initialLocationAndOrientation) {
        this(mars,
             new RobotLocation(Integer.parseInt(initialLocationAndOrientation.split(" ")[0]),
                               Integer.parseInt(initialLocationAndOrientation.split(" ")[1])),
             RobotOrientation.valueOf(initialLocationAndOrientation.split(" ")[2]));
    }

    public void executeInstruction(RobotInstruction instruction) {
        if (lost) {
            return;
        }
        INSTRUCTION_COMMAND_MAP.get(instruction).executeCommand(this, instruction);
    }

    public void executeInstructionSet(String instructionSet) {
        try (Scanner scanner = new Scanner(instructionSet)) {
            scanner.useDelimiter("");
            while (scanner.hasNext()) {
                executeInstruction(RobotInstruction.valueOf(scanner.next()));
            }
        }
    }

    @Override
    public String toString() {
        return currentLocation.toString() + " " + currentOrientation.toString() + (lost ? LOST : "");
    }

    private interface Command {
        void executeCommand(Robot robot, RobotInstruction instruction);
    }

    private static class MoveCommand implements Command {

        @Override
        public void executeCommand(Robot robot, RobotInstruction instruction) {
            if (robot.mars.hasScent(robot.currentLocation, robot.currentOrientation)) {
                return;
            }

            try {
                robot.currentLocation = robot.mars.move(robot.currentLocation, robot.currentOrientation);
            } catch (RobotOffGridException e) {
                robot.mars.leaveScent(robot.currentLocation, robot.currentOrientation);
                robot.lost = true;
            }
        }
    }

    private static class TurnCommand implements Command {

        @Override
        public void executeCommand(Robot robot, RobotInstruction instruction) {
            robot.currentOrientation = robot.currentOrientation.turn(instruction);
        }
    }
}
