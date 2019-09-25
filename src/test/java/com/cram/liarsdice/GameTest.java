package com.cram.liarsdice;

import org.junit.Test;

import static junit.framework.TestCase.assertTrue;

public class GameTest {
    @Test
    public void testGame() {
        assert(true);

        Game game = new Game();

        /*
        game.move(player: 1, dice: 2, value: 3)
game.move(player: 2, dice: 1, value: 3)
game.claim(dice: 19, value: 3) An outrageous claim! -> 0.000000000516%
         */

        game.move(0, 2, 3);
        game.move(1,1,3);

        System.out.println(game.claim(16,3));
    }

}
