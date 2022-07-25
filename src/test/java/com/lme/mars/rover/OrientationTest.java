package com.lme.mars.rover;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class OrientationTest {

    @ParameterizedTest
    @MethodSource("shouldTurnParameters")
    void shouldTurn(Orientation currentOrientation, RobotInstruction robotInstruction, Orientation newOrientation) {
        assertThat(currentOrientation.turn(robotInstruction)).isEqualTo(newOrientation);
    }

    private static Stream<Arguments> shouldTurnParameters() {
        return Stream.of(
          Arguments.of(Orientation.NORTH, RobotInstruction.RIGHT, Orientation.EAST),
          Arguments.of(Orientation.EAST, RobotInstruction.RIGHT, Orientation.SOUTH),
          Arguments.of(Orientation.SOUTH, RobotInstruction.RIGHT, Orientation.WEST),
          Arguments.of(Orientation.WEST, RobotInstruction.RIGHT, Orientation.NORTH),
          Arguments.of(Orientation.NORTH, RobotInstruction.LEFT, Orientation.WEST),
          Arguments.of(Orientation.WEST, RobotInstruction.LEFT, Orientation.SOUTH),
          Arguments.of(Orientation.SOUTH, RobotInstruction.LEFT, Orientation.EAST),
          Arguments.of(Orientation.EAST, RobotInstruction.LEFT, Orientation.NORTH)
        );
    }
}