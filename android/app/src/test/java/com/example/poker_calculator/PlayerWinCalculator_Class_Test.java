package com.example.poker_calculator;

import org.junit.Test;

import static org.junit.Assert.*;

public class PlayerWinCalculator_Class_Test {

    @Test
    public void test_isBetter() {

        TableCache cardsOnTable = new TableCache();
        Card flopCards[] = {new Card(CardTypes.HEART, 12), new Card(CardTypes.HEART, 11), new Card(CardTypes.DIAMOND, 6)};
        cardsOnTable.addFlopCards(flopCards);
        cardsOnTable.addTheTurn(new Card(CardTypes.SPADE, 8));
        cardsOnTable.addTheRiver(new Card(CardTypes.HEART, 10));

        Card[] cards = {new Card(CardTypes.HEART, 14), new Card(CardTypes.HEART, 13)};
        Player mainPlayer = new Player(cards);

        Card[] otherPlayerCards = {new Card(CardTypes.SPADE, 5), new Card(CardTypes.CLUB, 8)};
        Player otherPlayer = new Player(otherPlayerCards);
        Player[] otherPlayers = {otherPlayer};

        PlayerWinCalculator pwin = new PlayerWinCalculator(cardsOnTable, mainPlayer, otherPlayers);

        Card[] combination = {new Card(CardTypes.HEART, 10), new Card(CardTypes.HEART, 11), new Card(CardTypes.HEART, 7), new Card(CardTypes.HEART, 9), new Card(CardTypes.HEART, 8)};

        assertEquals(pwin.findRank(combination), HandRankings.STRAIGHT_FLUSH);
    }
}
