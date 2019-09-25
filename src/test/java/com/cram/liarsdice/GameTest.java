package com.cram.liarsdice;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Some sample tests. These could all be made better using jMock, Mockito, etc. Instead, as a proof of concept and due to
 * time constraints, this is just using some test-specific helper methods.
 */
public class GameTest {
    @Test
    public void testGame_HappyPath() {
        // These could be enhanced with a lot more happy paths
        Game game = new Game();

        game.move(0, 2, 3);
        game.move(1, 1, 3);

        assertTrue(game.claim(16, 3) < 0.000000001);
        assertTrue(game.challenge(2, 3));
        assertTrue(game.challenge(3, 3));
    }

    @Test
    public void testGame_SadPath() {
        // These could be enhanced with a lot more sad paths
        Game game = new Game();

        try {
            game.move(-1, 2, 3);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to move for player -1. Player number must be between 0 and 3.", e.getMessage());
        }

        try {
            game.move(0, 8, 3);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to move for player 0. Must play 0 to 5 dice.", e.getMessage());
        }

        try {
            game.move(0, -2, 3);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to move for player 0. Must play 0 to 5 dice.", e.getMessage());
        }

        try {
            game.move(0, 3, 13);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to move for player 0. Dice values must be between 1 and 6.", e.getMessage());
        }

        try {
            game.move(0, 3, -7);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to move for player 0. Dice values must be between 1 and 6.", e.getMessage());
        }

        try {
            game.getPlayer(-2);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to get player -2. Must be between 0 and 3.", e.getMessage());
        }


    }
}
