package com.example.poker_calculator;

public class TableCache {

    public Card[] deckOnTable;

    public TableCache() {

        deckOnTable = new Card[5];
    }

    public void addFlopCards(Card[] card) {

        deckOnTable[0] = new Card(card[0].getType(), card[0].getNumber());
        deckOnTable[1] = new Card(card[1].getType(), card[1].getNumber());
        deckOnTable[2] = new Card(card[2].getType(), card[2].getNumber());
    }

    public void addTheTurn(Card card) {

        deckOnTable[3] = new Card(card.getType(), card.getNumber());
    }

    public void addTheRiver(Card card) {

        deckOnTable[4] = new Card(card.getType(), card.getNumber());
    }

    public void changeCard(Card card, int index) {

        deckOnTable[index] = new Card(card.getType(), card.getNumber());
    }

    public Card getCard(int index) {

        return deckOnTable[index];
    }

}
