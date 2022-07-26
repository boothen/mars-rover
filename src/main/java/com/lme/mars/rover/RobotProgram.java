package com.lme.mars.rover;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Main entry point to execute the Robot input program and receive the output.
 */
public class RobotProgram {

    public String executeProgram(String input) {
        List<String> instructions = input.lines()
                                         .filter(str -> !str.isBlank())
                                         .collect(Collectors.toCollection(LinkedList::new));

        Mars mars = new Mars(instructions.remove(0));

        List<Robot> robots = new LinkedList<>();
        while (!instructions.isEmpty()) {
            Robot robot = new Robot(mars, instructions.remove(0));
            robots.add(robot);
            robot.executeInstructionSet(instructions.remove(0));
        }

        return robots.stream()
                     .map(Robot::toString)
                     .collect(Collectors.joining(System.lineSeparator()));
    }

}
