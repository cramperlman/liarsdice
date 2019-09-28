package com.cram.liarsdice;

import org.junit.Test;

import java.util.List;

import static junit.framework.TestCase.*;

/**
 * Some sample tests. These could all be made better using jMock, Mockito, etc. Instead, as a proof of concept and due to
 * time constraints, this is just using some test-specific helper methods.
 */
public class GameTest {
//    @Test
//    public void testGame_HappyPath() {
//        // These could be enhanced with a lot more happy paths
//        Game game = new Game();
//
//        game.move(0, 2, 3);
//        game.move(1, 1, 3);
//
//        assertTrue(game.claim(16, 3) < 0.000000001);
//        assertTrue(game.challenge(2, 3));
//        assertTrue(game.challenge(3, 3));
//    }

    @Test
    public void test_Init() {
        Game game = new Game();

        assertEquals(4, game.getPlayers().size());

        int[] dice = game.getPlayer(0).getDiceValues();
        assertTrue(compareDice(new int[]{2, 2, 4, 5, 6}, dice));

        dice = game.getPlayer(1).getDiceValues();
        assertTrue(compareDice(new int[]{1, 2, 3, 4, 5}, dice));

        dice = game.getPlayer(2).getDiceValues();
        assertTrue(compareDice(new int[]{3, 3, 3, 5, 5}, dice));

        dice = game.getPlayer(3).getDiceValues();
        assertTrue(compareDice(new int[]{1, 3, 4, 6, 6}, dice));

        assertEquals(0, game.getDiceOnTable().size());

        try {
            game.move(0, 3, 2);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to play 3 with value 2.", e.getMessage());
        }

        try {
            game.move(1, 1, 10);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to move for player 1. Dice values must be between 1 and 6.", e.getMessage());
        }

        try {
            game.move(5, 1, 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to move for player 5. Player number must be between 0 and 3.", e.getMessage());
        }

        game.move(0, 2, 2);
        assertEquals(2, game.getDiceOnTable().size());

        assertEquals(2, game.getDiceOnTable().get(0).getValue());
        assertEquals(2, game.getDiceOnTable().get(1).getValue());

        assertTrue(compareDice(new int[]{4, 5, 6}, game.getPlayer(0).getDiceValues()));

        double probability = game.claim(4, 2);
        assertEquals(probability, 82.7219230910404);

        game.move(1, 1, 4);
        assertEquals(3, game.getDiceOnTable().size());

        assertEquals(2, game.getDiceOnTable().get(0).getValue());
        assertEquals(2, game.getDiceOnTable().get(1).getValue());
        assertEquals(4, game.getDiceOnTable().get(2).getValue());

        assertTrue(compareDice(new int[]{1, 2, 3, 5}, game.getPlayer(1).getDiceValues()));

        probability = game.claim(5 ,1);
        assertEquals(probability, 13.964191642203073);

        try {
            game.claim(2, 1);
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to make a claim of 2 because prior claim was 5", e.getMessage());
        }

        game.move(2, 3, 3);
        probability = game.claim(6, 3);
        assertTrue(probability > 42.05);

        assertFalse(game.challenge(6, 3));
        assertTrue(game.challenge(5, 3));
    }

    private boolean compareDice(int[] list1, int[] list2) {
        if (list1.length != list2.length) {
            return false;
        }

        for (int i = 0; i < list1.length; i++) {
           if (list1[i] != list2[i]) {
               return false;
           }
        }

        return true;
    }

    @Test
    public void testGame_SadPath() {
        // These could be enhanced with a lot more sad pagths
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
