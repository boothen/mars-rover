package com.lme.mars.rover;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class RobotLocationTest {

    @Test
    void moveTest() {
        RobotLocation currentLocation = new RobotLocation(1, 1);
        RobotLocation robotLocation = currentLocation.moveLocation(1, 1);
        assertThat(robotLocation.getX()).isEqualTo(2);
        assertThat(robotLocation.getY()).isEqualTo(2);
    }
}