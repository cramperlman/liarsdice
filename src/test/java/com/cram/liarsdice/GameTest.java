package com.cram.liarsdice;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GameTest {
    @Test
    public void testGame_HappyPath() {
        assert(true);

        Game game = new Game();

        game.move(0, 2, 3);
        game.move(1,1,3);

        System.out.println(game.claim(16,3));
    }
}
