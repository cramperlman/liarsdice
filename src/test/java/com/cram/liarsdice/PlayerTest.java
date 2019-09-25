package com.cram.liarsdice;

import org.junit.Test;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertTrue;

public class PlayerTest {
    @Test
    public void testPlayer() {
        Player player = new Player();

        assertEquals(5, player.getDice().size());
    }
}
