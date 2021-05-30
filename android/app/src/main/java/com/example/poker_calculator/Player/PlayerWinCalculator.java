package com.example.poker_calculator.Player;

import com.example.poker_calculator.CardStructure.Card;
import com.example.poker_calculator.CardStructure.HandRankings;
import com.example.poker_calculator.MainGame.TableCache;

import java.util.ArrayList;
import java.util.Arrays;

public class PlayerWinCalculator {

    private Player mainPlayer;

    private Player[] otherPlayers;

    private TableCache cardsOnTable;

    public PlayerWinCalculator(TableCache cardsOnTable, Player mainPlayer, Player[] otherPlayers) {

        this.mainPlayer = mainPlayer;
        this.otherPlayers = otherPlayers;
        this.cardsOnTable = cardsOnTable;
    }

    public boolean canPlayerWin() {

        if(mainPlayer.isHandEmpty())    return false;

        for (Player otherPlayer : otherPlayers) {

            if (isBetter(otherPlayer) == false)
                return false;
        }
        return true;
    }

    public boolean isBetter(Player otherPlayer) {

        HandRankings playerRank = HandRankings.HIGH_HAND;
        HandRankings otherPlayerRank = HandRankings.HIGH_HAND;

        for(int i = 0; i < 3; i++) {
            for(int j = i+1 ; j < 4; j++) {
                for(int k = j+1; k < 5; k++) {

                    Card[] currentTestedCombinationForMainPlayer = new Card[5];

                    currentTestedCombinationForMainPlayer[0] = new Card(mainPlayer.getCards()[0].getType(), mainPlayer.getCards()[0].getNumber());
                    currentTestedCombinationForMainPlayer[1] = new Card(mainPlayer.getCards()[1].getType(), mainPlayer.getCards()[1].getNumber());
                    currentTestedCombinationForMainPlayer[2] = new Card(cardsOnTable.getCard(i).getType(), cardsOnTable.getCard(i).getNumber());
                    currentTestedCombinationForMainPlayer[3] = new Card(cardsOnTable.getCard(j).getType(), cardsOnTable.getCard(j).getNumber());
                    currentTestedCombinationForMainPlayer[4] = new Card(cardsOnTable.getCard(k).getType(), cardsOnTable.getCard(k).getNumber());

                    Card[] currentTestedCombinationForOtherPlayer = new Card[5];

                    currentTestedCombinationForOtherPlayer[0] = new Card(otherPlayer.getCards()[0].getType(), otherPlayer.getCards()[0].getNumber());
                    currentTestedCombinationForOtherPlayer[1] = new Card(otherPlayer.getCards()[1].getType(), otherPlayer.getCards()[1].getNumber());
                    currentTestedCombinationForOtherPlayer[2] = currentTestedCombinationForMainPlayer[2];
                    currentTestedCombinationForOtherPlayer[3] = currentTestedCombinationForMainPlayer[3];
                    currentTestedCombinationForOtherPlayer[4] = currentTestedCombinationForMainPlayer[4];


                    HandRankings currentTestedRankForMainPlayer = findRank(currentTestedCombinationForMainPlayer);
                    if(currentTestedRankForMainPlayer.isBetterThan(playerRank)) playerRank = currentTestedRankForMainPlayer;

                    HandRankings currentTestedRankForOtherPlayer = findRank(currentTestedCombinationForOtherPlayer);
                    if(currentTestedRankForOtherPlayer.isBetterThan(otherPlayerRank))   otherPlayerRank = currentTestedRankForOtherPlayer;
                }
            }
        }

        if(playerRank.isBetterThan(otherPlayerRank))
            return true;
        else if(otherPlayerRank.isBetterThan(playerRank))
            return false;

        int playersHighestCard = mainPlayer.getCards()[0].getNumber();
        int playersSecondHighestCard = mainPlayer.getCards()[1].getNumber();

        int otherPlayersHighestCard = otherPlayer.getCards()[0].getNumber();
        int otherPlayersSecondHighestCard = otherPlayer.getCards()[1].getNumber();

        if(playersHighestCard < playersSecondHighestCard) {
            playersHighestCard = mainPlayer.getCards()[1].getNumber();
            playersSecondHighestCard = mainPlayer.getCards()[0].getNumber();
        }

        if(otherPlayersHighestCard < otherPlayersSecondHighestCard) {
            otherPlayersHighestCard = otherPlayer.getCards()[1].getNumber();
            otherPlayersSecondHighestCard = otherPlayer.getCards()[0].getNumber();
        }

        if(playersHighestCard > otherPlayersHighestCard)    return true;
        else if(playersHighestCard < otherPlayersHighestCard)   return false;
        else {
            if(playersSecondHighestCard > otherPlayersSecondHighestCard)    return true;
            else if(playersSecondHighestCard < otherPlayersSecondHighestCard)   return false;
        }

        return false;
    }

    public void sort(Card[] combination) {

        ArrayList<Card> unsortedCombination = new ArrayList<>();

        for(int i = 0; i < combination.length; i++) {

            unsortedCombination.add(combination[i]);
        }

        ArrayList<Card> sortedCombination = new ArrayList<Card>();

        while(!unsortedCombination.isEmpty()) {

            int index = 0;
            int max = unsortedCombination.get(0).getNumber();

            for(Card card : unsortedCombination) {
                if(max < card.getNumber()) {
                    max = card.getNumber();
                    index = unsortedCombination.indexOf(card);
                }
            }

            sortedCombination.add(unsortedCombination.remove(index));
        }

        int index = 0;
        while(!sortedCombination.isEmpty()) {

            Card removedCard = sortedCombination.remove(0);
            combination[index++] = new Card(removedCard.getType(), removedCard.getNumber());
        }
    }

    public HandRankings findRank(Card[] combination) {

        sort(combination);

        if(combination[0].getNumber() == 14 && combination[1].getNumber() == 13 && combination[2].getNumber() == 12 && combination[3].getNumber() == 11 && combination[4].getNumber() == 10) {
            if(combination[0].getType() == combination[1].getType() && combination[1].getType() == combination[2].getType() && combination[2].getType() == combination[3].getType() && combination[3].getType() == combination[4].getType())
                return HandRankings.ROYAL_FLUSH;
        }

        if(combination[0].getNumber() == combination[1].getNumber()+1 && combination[1].getNumber() == combination[2].getNumber()+1 && combination[2].getNumber() == combination[3].getNumber()+1 && combination[3].getNumber() == combination[4].getNumber()+1) {
            if (combination[0].getType() == combination[1].getType() && combination[1].getType() == combination[2].getType() && combination[2].getType() == combination[3].getType() && combination[3].getType() == combination[4].getType())
                return HandRankings.STRAIGHT_FLUSH;
        }

        if((combination[0].getNumber() == combination[1].getNumber() && combination[1].getNumber() == combination[2].getNumber() && combination[2].getNumber() == combination[3].getNumber()) || (combination[1].getNumber() == combination[2].getNumber() && combination[2].getNumber() == combination[3].getNumber() && combination[3].getNumber() == combination[4].getNumber())) {
            return HandRankings.FOUR_OF_A_KIND;
        }

        if((combination[0].getNumber() == combination[1].getNumber() && combination[1].getNumber() == combination[2].getNumber() && combination[3].getNumber() == combination[4].getNumber()) || (combination[2].getNumber() == combination[3].getNumber() && combination[3].getNumber() == combination[4].getNumber() && combination[0].getNumber() == combination[1].getNumber())) {
            return HandRankings.FULL_HOUSE;
        }

        if(combination[0].getType() == combination[1].getType() && combination[1].getType() == combination[2].getType() && combination[2].getType() == combination[3].getType() && combination[3].getType() == combination[4].getType()) {
            return HandRankings.FLUSH;
        }

        if(combination[0].getNumber() == combination[1].getNumber()+1 && combination[1].getNumber() == combination[2].getNumber()+1 && combination[2].getNumber() == combination[3].getNumber()+1 && combination[3].getNumber() == combination[4].getNumber()+1) {
            return HandRankings.STRAIGHT;
        }

        if((combination[0].getNumber() == combination[1].getNumber() && combination[1].getNumber() == combination[2].getNumber()) || (combination[1].getNumber() == combination[2].getNumber() && combination[2].getNumber() == combination[3].getNumber()) || (combination[2].getNumber() == combination[3].getNumber() && combination[3].getNumber() == combination[4].getNumber())) {
            return HandRankings.THREE_OF_A_KIND;
        }

        if((combination[0].getNumber() == combination[1].getNumber() && combination[2].getNumber() == combination[3].getNumber()) || (combination[0].getNumber() == combination[1].getNumber() && combination[3].getNumber() == combination[4].getNumber()) || (combination[1].getNumber() == combination[2].getNumber() && combination[3].getNumber() == combination[4].getNumber())) {
            return HandRankings.TWO_PAIRS;
        }

        if((combination[0].getNumber() == combination[1].getNumber()) || (combination[1].getNumber() == combination[2].getNumber()) || (combination[2].getNumber() == combination[3].getNumber()) || (combination[3].getNumber() == combination[4].getNumber())) {
            return HandRankings.ONE_PAIR;
        }

        return HandRankings.HIGH_HAND;
    }
}

