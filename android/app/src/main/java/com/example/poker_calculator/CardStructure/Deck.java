package com.example.poker_calculator.CardStructure;

public class Deck {

    private Card[] deck;

    public Deck() {

        deck = new Card[52];

        for(int i = 0; i < 52; i++) {

            if(i == 0)  deck[i] = new Card(CardTypes.CLUB, 14);

            else if(i == 13)    deck[i] = new Card(CardTypes.DIAMOND, 14);

            else if(i == 26)    deck[i] = new Card(CardTypes.HEART, 14);

            else if(i == 39)    deck[i] = new Card(CardTypes.SPADE, 14);

            else if(i < 13)  deck[i] = new Card(CardTypes.CLUB, i+1);

            else if(i < 26) deck[i] = new Card(CardTypes.DIAMOND, i-12);

            else if(i < 39) deck[i] = new Card(CardTypes.HEART, i-25);

            else    deck[i] = new Card(CardTypes.SPADE, i-38);
        }
    }

    public Card[] getDeck() {
        return deck;
    }

    public int getCardIndex(Card card) {

        int index = -1;

        for(int i = 0; i < 52; i++){

            if(deck[i].getType() == card.getType() && deck[i].getNumber() == card.getNumber())
                index = i;
                break;
        }

        return index;
    }
}
