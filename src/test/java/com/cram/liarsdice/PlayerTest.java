package com.cram.liarsdice;

import org.junit.Test;

import static junit.framework.TestCase.*;

/**
 * Some sample tests. These could all be made better using jMock, Mockito, etc. Instead, as a proof of concept and due to
 * time constraints, this is just using some test-specific helper methods.
 */
public class PlayerTest {
    @Test
    public void testPlayer() {
        Player player = new Player();

        assertEquals(5, player.getDice().size());

        Game game = new Game();
        player = game.getPlayer(0);
        assertEquals(2, player.playDice(2, 3).size());
    }

    @Test
    public void testPlayer_sadPath() {
        Game game = new Game();

        Player player = game.getPlayer(1);
        try {
            assertEquals(2, player.playDice(2, 3).size());
            fail();
        } catch (IllegalArgumentException e) {
            assertEquals("Unable to play 2 with value 3.", e.getMessage());
        }
    }
}
