package com.cram.liarsdice;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Some sample tests. These could all be made better using jMock, Mockito, etc. Instead, as a proof of concept and due to
 * time constraints, this is just using some test-specific helper methods.
 */
public class DiceTest {
    @Test
    public void testDice() {
        Dice dice = new Dice();

        assertTrue(dice.getValue() <= 6);
        assertTrue(dice.getValue() >= 1);

        assertTrue(dice.roll() <= 6);
        assertTrue(dice.getValue() >= 1);
    }
}
