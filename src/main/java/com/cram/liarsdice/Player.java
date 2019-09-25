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
            // If somehow we didn't have enough dice to meet the Player's stated intentions,
            // because the Player made a stupid play...
            throw new IllegalArgumentException("Unable to play " + numDiceToPlay + " with value " + value + ".");
        }

        // Presumably, we can't replay dice that have already been played
        dice.removeAll(diceToPlay);

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

    @Override
    public String toString() {
        return "Player{" +
                "dice=" + dice +
                '}';
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
}
