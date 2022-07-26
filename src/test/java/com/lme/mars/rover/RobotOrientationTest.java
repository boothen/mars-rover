package com.lme.mars.rover;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RobotOrientationTest {

    @ParameterizedTest
    @MethodSource("shouldTurnParameters")
    void shouldTurn(RobotOrientation currentOrientation, RobotInstruction robotInstruction, RobotOrientation newOrientation) {
        assertThat(currentOrientation.turn(robotInstruction)).isEqualTo(newOrientation);
    }

    @ParameterizedTest
    @MethodSource("shouldMoveParameters")
    void shouldMove(RobotOrientation currentOrientation, RobotLocation newLocation) {
        assertThat(currentOrientation.move(new RobotLocation(2, 2))).isEqualTo(newLocation);
    }

    private static Stream<Arguments> shouldTurnParameters() {
        return Stream.of(
            Arguments.of(RobotOrientation.N, RobotInstruction.R, RobotOrientation.E),
            Arguments.of(RobotOrientation.E, RobotInstruction.R, RobotOrientation.S),
            Arguments.of(RobotOrientation.S, RobotInstruction.R, RobotOrientation.W),
            Arguments.of(RobotOrientation.W, RobotInstruction.R, RobotOrientation.N),
            Arguments.of(RobotOrientation.N, RobotInstruction.L, RobotOrientation.W),
            Arguments.of(RobotOrientation.W, RobotInstruction.L, RobotOrientation.S),
            Arguments.of(RobotOrientation.S, RobotInstruction.L, RobotOrientation.E),
            Arguments.of(RobotOrientation.E, RobotInstruction.L, RobotOrientation.N)
        );
    }

    private static Stream<Arguments> shouldMoveParameters() {
        return Stream.of(
            Arguments.of(RobotOrientation.N, new RobotLocation(2, 3)),
            Arguments.of(RobotOrientation.E, new RobotLocation(3, 2)),
            Arguments.of(RobotOrientation.S, new RobotLocation(2, 1)),
            Arguments.of(RobotOrientation.W, new RobotLocation(1, 2))
        );
    }
}