package com.example.poker_calculator.Player;

import com.example.poker_calculator.CardStructure.Card;

public class Player {

    private Card[] cardsOnHand;

    public Player() {

        cardsOnHand = new Card[2];
    }

    public Player(Card[] cards) {

        cardsOnHand = new Card[2];
        cardsOnHand[0] = new Card(cards[0].getType(), cards[0].getNumber());
        cardsOnHand[1] = new Card(cards[1].getType(), cards[1].getNumber());
    }

    public void addCard(Card card, int index) {

        cardsOnHand[index] = new Card(card.getType(), card.getNumber());
    }

    public Card[] getCards() {

        return cardsOnHand;
    }

    public boolean isHandEmpty() {

        return cardsOnHand[0] == null && cardsOnHand[1] == null;
    }
}
