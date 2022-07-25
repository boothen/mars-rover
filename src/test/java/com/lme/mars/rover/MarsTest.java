package com.lme.mars.rover;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MarsTest {

    private final Mars mars = new Mars(10, 10);

    @Test
    void shouldReturnNewLocation() throws RobotOffGridException {
        RobotLocation newLocation = mars.move(new RobotLocation(1, 1), Orientation.E);
        assertThat(newLocation).isEqualTo(new RobotLocation(2, 1));
    }

    @Test
    void shouldThrowExceptionWhenMoveOffGrid() {
        assertThatExceptionOfType(RobotOffGridException.class).isThrownBy(() -> mars.move(new RobotLocation(10, 10), Orientation.E));
    }

    @Test
    void shouldLeaveScentAndVerifyScentLeft() {
        assertThat(mars.hasScent(new RobotLocation(10, 10), Orientation.E)).isFalse();
        mars.leaveScent(new RobotLocation(10, 10), Orientation.E);
        assertThat(mars.hasScent(new RobotLocation(10, 10), Orientation.E)).isTrue();
    }

    @Test
    void shouldVerifyScentHasOrientation() {
        mars.leaveScent(new RobotLocation(0, 0), Orientation.W);
        assertThat(mars.hasScent(new RobotLocation(0, 0), Orientation.S)).isFalse();
    }

    @ParameterizedTest
    @MethodSource("invalidGridSizeParameters")
    void shouldThrowExceptionWhenGridIsInvalidSize(int x, int y) {
        assertThatIllegalArgumentException().isThrownBy(() -> new Mars(x, y));
    }

    private static Stream<Arguments> invalidGridSizeParameters() {
        return Stream.of(
            Arguments.of(-1, 0),
            Arguments.of(0, -1),
            Arguments.of(51, 0),
            Arguments.of(0, 51)
        );
    }
}