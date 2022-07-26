package com.lme.mars.rover;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RobotTest {

    @Test
    void shouldTurnRight() {
        Robot robot = new Robot(new Mars(10, 10), new RobotLocation(0, 0 ), RobotOrientation.N);
        robot.executeInstruction(RobotInstruction.R);
        assertThat(robot.toString()).isEqualTo("0 0 E");
    }

    @Test
    void shouldTurnLeft() {
        Robot robot = new Robot(new Mars(10, 10), new RobotLocation(0, 0), RobotOrientation.N);
        robot.executeInstruction(RobotInstruction.L);
        assertThat(robot.toString()).isEqualTo("0 0 W");
    }

    @Test
    void shouldMoveForward() {
        Robot robot = new Robot(new Mars(10, 10), new RobotLocation(0, 0), RobotOrientation.N);
        robot.executeInstruction(RobotInstruction.F);
        assertThat(robot.toString()).isEqualTo("0 1 N");
    }

    @Test
    void shouldReturnLostWhenOffGrid() {
        Robot robot = new Robot(new Mars(10, 10), new RobotLocation(10, 10), RobotOrientation.N);
        robot.executeInstruction(RobotInstruction.F);
        assertThat(robot.toString()).isEqualTo("10 10 N LOST");
    }

    @Test
    void shouldCheckScentAndIgnoreMove() {
        Mars mars = new Mars(10, 10);
        Robot robot1 = new Robot(mars, new RobotLocation(10, 10), RobotOrientation.N);
        robot1.executeInstruction(RobotInstruction.F);
        assertThat(robot1.toString()).isEqualTo("10 10 N LOST");

        Robot robot2 = new Robot(mars, new RobotLocation(10, 10), RobotOrientation.N);
        robot2.executeInstruction(RobotInstruction.F);
        assertThat(robot2.toString()).isEqualTo("10 10 N");
    }

    @Test
    void shouldIgnoreMovesOnceLost() {
        Mars mars = new Mars(10, 10);
        Robot robot1 = new Robot(mars, new RobotLocation(10, 10), RobotOrientation.N);
        robot1.executeInstruction(RobotInstruction.F);
        assertThat(robot1.toString()).isEqualTo("10 10 N LOST");
        robot1.executeInstruction(RobotInstruction.L);
        assertThat(robot1.toString()).isEqualTo("10 10 N LOST");
    }

    @Test
    void shouldConstructRobotFromString() {
        Robot robot = new Robot(new Mars(10, 10), "9 9 S");
        assertThat(robot.toString()).isEqualTo("9 9 S");
    }

    @Test
    void shouldExecuteInstructionSet() {
        Robot robot = new Robot(new Mars(10, 10), "0 0 N");
        robot.executeInstructionSet("FFFRFFFRFLLLF");
        assertThat(robot.toString()).isEqualTo("2 2 W");
    }
}