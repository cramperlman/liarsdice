package com.cram.liarsdice;

import java.util.ArrayList;
import java.util.List;

/**
 * A Player can hold N dice in their hand at any time
 */
public class Player {

    protected static final int NUMBER_OF_DICE = 5; // per the rules, we get 5 dice per player

    private List<Dice> dice = new ArrayList<Dice>();

    /**
     * Instantiates a Player with N dice
     */
    public Player() {
        for (int i = 0; i < NUMBER_OF_DICE; i++) {
            dice.add(new Dice());
        }
    }

    /**
     * For testing purposes only
     *
     * @param diceValues
     */
    public Player(int[] diceValues) {
        for (int i = 0; i < diceValues.length; i++) {
            Dice dice = new Dice(diceValues[i]);
            this.dice.add(dice);
        }
    }

    /**
     * A Player can place numDiceToPlay of value on the table
     *
     * @param numDiceToPlay
     * @param value
     * @return
     */
    public List<Dice> playDice(int numDiceToPlay, int value) {
        List<Dice> diceToPlay = new ArrayList<Dice>();

        for (Dice thisDice : dice) {
            // Theoretically, a person could have more dice of a specific value than they intend to play
            //
            // the instructions are vague, in this sense, of whether or not to re-roll those in that scenario so we
            // will also re-roll those
            if (thisDice.getValue() == value && diceToPlay.size() < numDiceToPlay) {
                diceToPlay.add(thisDice);
            } else {
                thisDice.roll();
            }
        }

        if (diceToPlay.size() < numDiceToPlay) {
            // If somehow we didn't have enough dice because the Player made a stupid play...
            throw new IllegalArgumentException("Unable to play " + numDiceToPlay + " with value " + value + ".");
        }

        return diceToPlay;
    }

    /**
     * Get the Player's dice
     *
     * @return
     */
    public List<Dice> getDice() {
        return dice;
    }
}