package com.wensby.terminablo.userinterface.terminal;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

public class TerminalCoordinatesTest {

    @Test
    public void construction() {
        TerminalCoordinates coordinates = TerminalCoordinates.of(1, 2);
        assertThat(coordinates.getRow(), is(1));
        assertThat(coordinates.getColumn(), is(2));
    }

    @Test(expected = IllegalArgumentException.class)
    public void construction_illegal_whenNegativeArguments() {
        TerminalCoordinates.of(-1, 0);
    }
}