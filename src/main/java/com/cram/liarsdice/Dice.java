package com.cram.liarsdice;

import java.util.Random;

/**
 * A Dice (or single Die, depending on whom you ask!) has a single value which is the number facing upright on the table
 */
public class Dice {

    private static final int NUM_OF_SIDES = 6;

    private int value;

    /**
     * When we instantiate a new dice, we can roll it and give it a value
     */
    public Dice() {
        value = roll();
    }

    /**
     * Roll the dice and set the value from 1-6.
     *
     * @return
     */
    public int roll() {
        Random r = new Random();
        value = r.nextInt(NUM_OF_SIDES) + 1;
        return value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Dice{" +
                "value=" + value +
                '}';
    }

    /**
     * For testing purposes only
     *
     * @param value
     */
    public Dice(int value) {
        this.value = value;
    }
}
