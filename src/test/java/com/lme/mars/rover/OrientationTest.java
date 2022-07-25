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

    @ParameterizedTest
    @MethodSource("shouldMoveParameters")
    void shouldMove(Orientation currentOrientation, RobotLocation newLocation) {
        assertThat(currentOrientation.move(new RobotLocation(2, 2))).isEqualTo(newLocation);
    }

    private static Stream<Arguments> shouldTurnParameters() {
        return Stream.of(
            Arguments.of(Orientation.N, RobotInstruction.R, Orientation.E),
            Arguments.of(Orientation.E, RobotInstruction.R, Orientation.S),
            Arguments.of(Orientation.S, RobotInstruction.R, Orientation.W),
            Arguments.of(Orientation.W, RobotInstruction.R, Orientation.N),
            Arguments.of(Orientation.N, RobotInstruction.L, Orientation.W),
            Arguments.of(Orientation.W, RobotInstruction.L, Orientation.S),
            Arguments.of(Orientation.S, RobotInstruction.L, Orientation.E),
            Arguments.of(Orientation.E, RobotInstruction.L, Orientation.N)
        );
    }

    private static Stream<Arguments> shouldMoveParameters() {
        return Stream.of(
            Arguments.of(Orientation.N, new RobotLocation(2, 3)),
            Arguments.of(Orientation.E, new RobotLocation(3, 2)),
            Arguments.of(Orientation.S, new RobotLocation(2, 1)),
            Arguments.of(Orientation.W, new RobotLocation(1, 2))
        );
    }
}