package com.lme.mars.rover;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RobotProgramTest {

    private static final String INPUT = """
        5 3
        1 1 E
        RFRFRFRF
        3 2 N
        FRRFLLFFRRFLL
        0 3 W
        LLFFFLFLFL""";

    private static final String EXPECTED_OUTPUT = """
        1 1 E
        3 3 N LOST
        2 3 S""";

    @Test
    void shouldRunProgram() {
        RobotProgram robotProgram = new RobotProgram();
        String actualOutput = robotProgram.executeProgram(INPUT);

        assertThat(actualOutput).isEqualTo(EXPECTED_OUTPUT);
    }
}