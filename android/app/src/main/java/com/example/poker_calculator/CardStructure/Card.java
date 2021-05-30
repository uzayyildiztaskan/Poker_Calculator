package com.example.poker_calculator.CardStructure;

public class Card {

    private CardTypes type;

    private int number;

    public Card(CardTypes cardType, int number) {

        type = cardType;
        this.number = number;
    }

    public CardTypes getType() {
        return type;
    }

    public int getNumber() {
        return number;
    }
}
