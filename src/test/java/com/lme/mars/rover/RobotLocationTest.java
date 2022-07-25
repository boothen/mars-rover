package com.lme.mars.rover;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;

class RobotLocationTest {

    @Test
    void shouldMove() {
        RobotLocation currentLocation = new RobotLocation(1, 1);
        RobotLocation robotLocation = currentLocation.moveLocation(1, 1);
        assertThat(robotLocation).isEqualTo(new RobotLocation(2, 2));
    }

    @Test
    void toStringFormat() {
        assertThat(new RobotLocation(1, 2).toString()).isEqualTo("1 2");
    }

    @ParameterizedTest
    @MethodSource("verifyOffGridParameters")
    void verifyOffGrid(RobotLocation currentLocation, int xMax, int yMax, boolean offGrid) {
        assertThat(currentLocation.offGrid(xMax, yMax)).isEqualTo(offGrid);
    }

    private static Stream<Arguments> verifyOffGridParameters() {
        return Stream.of(
            Arguments.of(new RobotLocation(0, 0), 10, 10, false),
            Arguments.of(new RobotLocation(-1, 0), 10, 10, true),
            Arguments.of(new RobotLocation(0, -1), 10, 10, true),
            Arguments.of(new RobotLocation(10, 0), 10, 10, false),
            Arguments.of(new RobotLocation(0, 10), 10, 10, false),
            Arguments.of(new RobotLocation(10, 10), 10, 10, false),
            Arguments.of(new RobotLocation(11, 0), 10, 10, true),
            Arguments.of(new RobotLocation(0, 11), 10, 10, true),
            Arguments.of(new RobotLocation(11, 11), 10, 10, true)
        );
    }
}