package com.cram.liarsdice;

import org.apache.commons.math3.util.ArithmeticUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * In the game Liar's Dice, each player begins the game with 5 dice, hidden from the other players. Every player rolls
 * the dice and hides the result. The first player puts X(ex. 0 to 5) number of dice in the middle and declares how many
 * dice of a certain number exist amongst all the dice in play. For example, if he puts out 3 4s and says "There are at
 * least 10 4s", then he believes there are 10 4's across everyone's hand including the 3 dice in the middle.
 * <p>
 * After a player places dice on the board and makes a bid(note: the player doesn't have to place any dice on the board,
 * they can just make a bid), he/she must reroll the remaining dice in their hand.
 * <p>
 * The next player must either challenge the previous player's claim, or make a new claim that is at least one die
 * higher (it can be an different number is the player chooses). For example, "There are at least 11 4s" or "There are
 * at least 11 3s".
 */
public class Game {

    private int numberOfPlayers;

    private int totalNumberOfDice;

    private List<Player> players = new ArrayList<Player>();
    private List<Dice> diceOnTable = new ArrayList<Dice>();

    /**
     * Instantiates a game of Liars Dice for the specified number of players
     *
     * @param numberOfPlayers
     */
    public Game(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;

        for (int i = 0; i < numberOfPlayers; i++) {
            players.add(new Player());
        }

        totalNumberOfDice = numberOfPlayers * Player.NUMBER_OF_DICE;
    }

    /**
     * Plays a move for a given player, specifying the number of dice placed on the table with a given value
     *
     * @param player
     * @param dice
     * @param value
     */
    public void move(int player, int dice, int value) {
        if (player < 0 || player >= numberOfPlayers) {
            throw new IllegalArgumentException("Unable to move for player " + player + ". Player number must be between 0 and " + (numberOfPlayers - 1) + ".");
        }

        if (dice < 0 || dice > Player.NUMBER_OF_DICE) {
            throw new IllegalArgumentException("Unable to move for player " + player + ". Must play 0 to " + Player.NUMBER_OF_DICE + " dice.");
        }

        if (value > 6 || value < 1) {
            throw new IllegalArgumentException("Unable to move for player " + player + ". Dice values must be between 1 and 6.");
        }

        Player currentPlayer = players.get(player);
        diceOnTable.addAll(currentPlayer.playDice(dice, value));
    }

    /**
     * Returns the probability of the requested state based on the formula of
     * n! / k! (n - k)! * (1/6)^k * (5/6)^(n-k)
     *
     * @param numOfDice
     * @param value
     * @return
     */
    public double claim(int numOfDice, int value) {
        double probability = 0;

        int n = totalNumberOfDice - diceOnTable.size();

        for (int k = numOfDice; k <= n; k++) {
            // There are other ways of getting a factorial in Java, but the Apache utils still work just fine
            // I've yet to find any reason why these were actually deprecated. Math is math and has been for thousands
            // of years!
            probability = probability + ArithmeticUtils.factorial(n) / ArithmeticUtils.factorial(k) * ArithmeticUtils.factorial(n - k)
                    * (Math.pow(1.0 / 6.0, k)) * (Math.pow(5.0 / 6.0, n - k));
        }

        return probability * 100;
    }

    /**
     * Check to see if there are at least numberOfDice with value in existence and return true if so
     *
     * @param numberOfDice
     * @param value
     * @return
     */
    public boolean challenge(int numberOfDice, int value) {
        int numDiceWithValue = 0;
        for (Player player : players) {
            for (Dice dice : player.getDice()) {
                if (dice.getValue() == value) {
                    numDiceWithValue++;
                }

                if (numDiceWithValue == numberOfDice) {
                    return true;
                }
            }
        }

        return false;
    }


    public Player getPlayer(int player) {
        if (player > players.size() || player < 0) {
            throw new IllegalArgumentException("Unable to get player " + player + ". Must be between 0 and " + (numberOfPlayers - 1) + ".");
        }

        return players.get(player);
    }

    @Override
    public String toString() {
        return "Game{" +
                "numberOfPlayers=" + numberOfPlayers +
                ", totalNumberOfDice=" + totalNumberOfDice +
                ", players=" + players +
                ", diceOnTable=" + diceOnTable +
                '}';
    }

    /**
     * For testing purposes only
     */
    public Game() {
        numberOfPlayers = 4;

        Player player1 = new Player(new int[]{3, 3, 1, 1, 1});
        Player player2 = new Player(new int[]{3, 2, 1, 1, 1});
        Player player3 = new Player(new int[]{1, 1, 1, 1, 1});
        Player player4 = new Player(new int[]{1, 1, 1, 1, 1});

        players.add(player1);
        players.add(player2);
        players.add(player3);
        players.add(player4);

        totalNumberOfDice = numberOfPlayers * Player.NUMBER_OF_DICE;
    }

}
