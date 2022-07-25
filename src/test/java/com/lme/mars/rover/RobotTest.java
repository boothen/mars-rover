package com.lme.mars.rover;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RobotTest {

    @Test
    void shouldTurnRight() {
        Robot robot = new Robot(new Mars(10, 10), new RobotLocation(0, 0 ), Orientation.N);
        robot.executeInstruction(RobotInstruction.R);
        assertThat(robot.toString()).isEqualTo("0 0 E");
    }

    @Test
    void shouldTurnLeft() {
        Robot robot = new Robot(new Mars(10, 10), new RobotLocation(0, 0), Orientation.N);
        robot.executeInstruction(RobotInstruction.L);
        assertThat(robot.toString()).isEqualTo("0 0 W");
    }

    @Test
    void shouldMoveForward() {
        Robot robot = new Robot(new Mars(10, 10), new RobotLocation(0, 0), Orientation.N);
        robot.executeInstruction(RobotInstruction.F);
        assertThat(robot.toString()).isEqualTo("0 1 N");
    }

    @Test
    void shouldReturnLostWhenOffGrid() {
        Robot robot = new Robot(new Mars(10, 10), new RobotLocation(10, 10), Orientation.N);
        robot.executeInstruction(RobotInstruction.F);
        assertThat(robot.toString()).isEqualTo("10 10 N LOST");
    }

    @Test
    void shouldCheckScentAndIgnoreMove() {
        Mars mars = new Mars(10, 10);
        Robot robot1 = new Robot(mars, new RobotLocation(10, 10), Orientation.N);
        robot1.executeInstruction(RobotInstruction.F);
        assertThat(robot1.toString()).isEqualTo("10 10 N LOST");

        Robot robot2 = new Robot(mars, new RobotLocation(10, 10), Orientation.N);
        robot2.executeInstruction(RobotInstruction.F);
        assertThat(robot2.toString()).isEqualTo("10 10 N");
    }

    @Test
    void shouldIgnoreMovesOnceLost() {
        Mars mars = new Mars(10, 10);
        Robot robot1 = new Robot(mars, new RobotLocation(10, 10), Orientation.N);
        robot1.executeInstruction(RobotInstruction.F);
        assertThat(robot1.toString()).isEqualTo("10 10 N LOST");
        robot1.executeInstruction(RobotInstruction.L);
        assertThat(robot1.toString()).isEqualTo("10 10 N LOST");
    }
}