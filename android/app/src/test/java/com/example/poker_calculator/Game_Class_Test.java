package com.example.poker_calculator;

import com.example.poker_calculator.CardStructure.Card;
import com.example.poker_calculator.CardStructure.CardTypes;
import com.example.poker_calculator.MainGame.Game;

import org.junit.Test;

import static org.junit.Assert.*;

public class Game_Class_Test {

    @Test
    public void test_FindPlayerCardIndex() {

        Game testGame = new Game();
        Card[] aceAndOtherCheckForHearts = {new Card(CardTypes.HEART, 14), new Card(CardTypes.HEART, 5)};
        Card[] aceAndOtherCheckForSpades = {new Card(CardTypes.SPADE, 14), new Card(CardTypes.SPADE, 7)};
        Card[] aceAndOtherCheckForDiamonds = {new Card(CardTypes.DIAMOND, 14), new Card(CardTypes.DIAMOND, 13)};
        Card[] aceAndOtherCheckForClubs = {new Card(CardTypes.CLUB, 14), new Card(CardTypes.CLUB, 8)};

        testGame.addInitialPlayerCards(aceAndOtherCheckForHearts);

        int[] testIndexes = testGame.findPlayerCardIndex();

        assertEquals(26, testIndexes[0]);
        assertEquals(30, testIndexes[1]);

        testGame = new Game();

        testGame.addInitialPlayerCards(aceAndOtherCheckForSpades);

        testIndexes = testGame.findPlayerCardIndex();

        assertEquals(39, testIndexes[0]);
        assertEquals(45, testIndexes[1]);

        testGame = new Game();

        testGame.addInitialPlayerCards(aceAndOtherCheckForDiamonds);

        testIndexes = testGame.findPlayerCardIndex();

        assertEquals(13, testIndexes[0]);
        assertEquals(25, testIndexes[1]);

        testGame = new Game();

        testGame.addInitialPlayerCards(aceAndOtherCheckForClubs);

        testIndexes = testGame.findPlayerCardIndex();

        assertEquals(0, testIndexes[0]);
        assertEquals(7, testIndexes[1]);
    }

    @Test
    public void test_FindTableCacheCardIndexWithEmptyCache() {

        Game testGame = new Game();
        int[] testIndexes = testGame.findTableCacheCardIndex();

        assertEquals(-1, testIndexes[0]);
        assertEquals(-1, testIndexes[1]);
        assertEquals(-1, testIndexes[2]);
        assertEquals(-1, testIndexes[3]);
        assertEquals(-1, testIndexes[4]);

    }

    @Test
    public void test_FindTableCacheCardIndexWithTheFlop() {

        Game testGame = new Game();
        Card[] cards = {new Card(CardTypes.HEART, 14), new Card(CardTypes.SPADE, 5), new Card(CardTypes.CLUB, 4)};
        testGame.addTheFlop(cards);

        int[] testIndexes = testGame.findTableCacheCardIndex();

        assertEquals(26, testIndexes[0]);
        assertEquals(43, testIndexes[1]);
        assertEquals(3, testIndexes[2]);
        assertEquals(-1, testIndexes[3]);
        assertEquals(-1, testIndexes[4]);
    }

    @Test
    public void test_FindTableCacheCardIndexWithTheTurn() {

        Game testGame = new Game();
        Card[] cards = {new Card(CardTypes.HEART, 12), new Card(CardTypes.SPADE, 3), new Card(CardTypes.CLUB, 1)};
        testGame.addTheFlop(cards);
        testGame.addTheTurn(new Card(CardTypes.SPADE, 11));

        int[] testIndexes = testGame.findTableCacheCardIndex();

        assertEquals(37, testIndexes[0]);
        assertEquals(41, testIndexes[1]);
        assertEquals(0, testIndexes[2]);
        assertEquals(49, testIndexes[3]);
        assertEquals(-1, testIndexes[4]);
    }

    @Test
    public void test_FindTableCacheCardIndexWithTheRiver() {

        Game testGame = new Game();
        Card[] cards = {new Card(CardTypes.HEART, 12), new Card(CardTypes.SPADE, 3), new Card(CardTypes.CLUB, 1)};
        testGame.addTheFlop(cards);
        testGame.addTheTurn(new Card(CardTypes.SPADE, 11));
        testGame.addTheRiver(new Card(CardTypes.DIAMOND,1));

        int[] testIndexes = testGame.findTableCacheCardIndex();

        assertEquals(37, testIndexes[0]);
        assertEquals(41, testIndexes[1]);
        assertEquals(0, testIndexes[2]);
        assertEquals(49, testIndexes[3]);
        assertEquals(13, testIndexes[4]);
    }

    @Test
    public void test_isIndexFree() {

        Game testGame = new Game();
        int[] playerCardsIndex = {13, 42};
        int[] tableCacheCardIndex = {15, 23, -1, -1, -1};
        int[][] otherPlayersIndex = {{17, 19}, {1,3}, {-1, -1}};

        assertFalse(testGame.isIndexFree(13,playerCardsIndex,tableCacheCardIndex,otherPlayersIndex));
        assertFalse(testGame.isIndexFree(42,playerCardsIndex,tableCacheCardIndex,otherPlayersIndex));
        assertFalse(testGame.isIndexFree(15,playerCardsIndex,tableCacheCardIndex,otherPlayersIndex));
        assertFalse(testGame.isIndexFree(23,playerCardsIndex,tableCacheCardIndex,otherPlayersIndex));
        assertFalse(testGame.isIndexFree(17,playerCardsIndex,tableCacheCardIndex,otherPlayersIndex));
        assertFalse(testGame.isIndexFree(19,playerCardsIndex,tableCacheCardIndex,otherPlayersIndex));
        assertFalse(testGame.isIndexFree(1,playerCardsIndex,tableCacheCardIndex,otherPlayersIndex));
        assertFalse(testGame.isIndexFree(3,playerCardsIndex,tableCacheCardIndex,otherPlayersIndex));
        assertTrue(testGame.isIndexFree(18,playerCardsIndex,tableCacheCardIndex,otherPlayersIndex));
    }

    @Test
    public void test_findMaxAvailableIndex() {

        Game testGame = new Game();
        testGame.setPlayerAmount(2);

        int[] playerCardIndex = {51, 50};
        int[] tableCacheCardIndex = {49, 47};

        int[] playerCardIndex1 = {12, 24};
        int[] tableCacheCardIndex1 = {49, 47};

        assertEquals(46, testGame.findMaxAvailableIndex(playerCardIndex, tableCacheCardIndex));
        assertEquals(50, testGame.findMaxAvailableIndex(playerCardIndex1, tableCacheCardIndex1));

    }

    @Test
    public void test_calculate() {

        Game testGame = new Game();
        testGame.setPlayerAmount(2);

        Card[] playerCards = {new Card(CardTypes.HEART, 3), new Card(CardTypes.CLUB, 7)};
        testGame.addInitialPlayerCards(playerCards);

        Card flopCards[] = {new Card(CardTypes.HEART, 13), new Card(CardTypes.DIAMOND, 8), new Card(CardTypes.SPADE, 5)};
        testGame.addTheFlop(flopCards);

        //testGame.addTheTurn(new Card(CardTypes.DIAMOND, 3));

        double res = testGame.calculate();
        System.out.println(res);
        assertNotEquals(100, res);
    }
}
