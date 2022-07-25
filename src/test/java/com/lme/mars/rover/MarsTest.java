package com.lme.mars.rover;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

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
}