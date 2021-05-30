package com.example.poker_calculator.CardStructure;

public enum HandRankings {

    ROYAL_FLUSH(9),
    STRAIGHT_FLUSH(8),
    FOUR_OF_A_KIND(7),
    FULL_HOUSE(6),
    FLUSH(5),
    STRAIGHT(4),
    THREE_OF_A_KIND(3),
    TWO_PAIRS(2),
    ONE_PAIR(1),
    HIGH_HAND(0);

    private int power;

    HandRankings(int power) {
        this.power = power;
    }

    public boolean isBetterThan(HandRankings otherRank) {

        return this.power > otherRank.power;
    }
}
