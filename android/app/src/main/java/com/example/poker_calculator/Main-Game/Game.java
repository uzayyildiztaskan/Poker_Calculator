package com.example.poker_calculator;

public class Game {

    private final Deck deck;

    private final TableCache tableCache;

    private int playerAmount;

    private final Player mainPlayer;

    private int tableCacheCardAmount;

    public Game() {

        deck = new Deck();
        tableCache = new TableCache();
        mainPlayer = new Player();
        playerAmount = 1;
        tableCacheCardAmount = 0;
    }

    public void setPlayerAmount(int playerAmount) {

        this.playerAmount = playerAmount;
    }

    public void addInitialPlayerCards(Card[] card) {

        mainPlayer.addCard(card[0], 0);
        mainPlayer.addCard(card[1], 1);
    }

    public void addTheFlop(Card[] cards) {

        tableCache.addFlopCards(cards);
        tableCacheCardAmount += 3;
    }

    public void addTheTurn(Card card) {

        tableCache.addTheTurn(card);
        tableCacheCardAmount++;
    }

    public void addTheRiver(Card card) {

        tableCache.addTheRiver(card);
        tableCacheCardAmount++;
    }

    public void changePlayerCard(Card card, int index) {

        mainPlayer.addCard(card, index);
    }

    public int[] findPlayerCardIndex() {

        Card[] playerCards = mainPlayer.getCards();

        int[] playerCardConstant = new int[2];

        switch (playerCards[0].getType()) {

            case CLUB:
                playerCardConstant[0] = 0;
                break;
            case DIAMOND:
                playerCardConstant[0] = 1;
                break;
            case HEART:
                playerCardConstant[0] = 2;
                break;
            case SPADE:
                playerCardConstant[0] = 3;
                break;
        }

        switch (playerCards[1].getType()) {

            case CLUB:
                playerCardConstant[1] = 0;
                break;
            case DIAMOND:
                playerCardConstant[1] = 1;
                break;
            case HEART:
                playerCardConstant[1] = 2;
                break;
            case SPADE:
                playerCardConstant[1] = 3;
                break;
        }

        int[] playerCardsIndex = new int[2];

        int sumNumber1 = playerCards[0].getNumber() % 14;
        int sumNumber2 = playerCards[1].getNumber() % 14;

        if(sumNumber1 != 0)
            sumNumber1 = sumNumber1 - 1;
        if(sumNumber2 != 0)
            sumNumber2 = sumNumber2 - 1;

        playerCardsIndex[0] = sumNumber1 + playerCardConstant[0] * 13;
        playerCardsIndex[1] = sumNumber2 + playerCardConstant[1] * 13;

        return playerCardsIndex;
    }

    public int[] findTableCacheCardIndex() {

        int[] cardConstants = new int[5];

        int[] tableCacheCardIndex = new int[5];

        if(tableCache.deckOnTable[0] == null) {

            tableCacheCardIndex[0] = -1;
            tableCacheCardIndex[1] = -1;
            tableCacheCardIndex[2] = -1;
            tableCacheCardIndex[3] = -1;
            tableCacheCardIndex[4] = -1;
        }

        else {

            switch (tableCache.deckOnTable[0].getType()) {

                case CLUB:
                    cardConstants[0] = 0;
                    break;
                case DIAMOND:
                    cardConstants[0] = 1;
                    break;
                case HEART:
                    cardConstants[0] = 2;
                    break;
                case SPADE:
                    cardConstants[0] = 3;
                    break;
            }

            switch (tableCache.deckOnTable[1].getType()) {

                case CLUB:
                    cardConstants[1] = 0;
                    break;
                case DIAMOND:
                    cardConstants[1] = 1;
                    break;
                case HEART:
                    cardConstants[1] = 2;
                    break;
                case SPADE:
                    cardConstants[1] = 3;
                    break;
            }

            switch (tableCache.deckOnTable[2].getType()) {

                case CLUB:
                    cardConstants[2] = 0;
                    break;
                case DIAMOND:
                    cardConstants[2] = 1;
                    break;
                case HEART:
                    cardConstants[2] = 2;
                    break;
                case SPADE:
                    cardConstants[2] = 3;
                    break;
            }

            if(tableCache.deckOnTable[3] == null) {

                tableCacheCardIndex[3] = -1;
                tableCacheCardIndex[4] = -1;
            }

            else {

                switch (tableCache.deckOnTable[3].getType()) {

                    case CLUB:
                        cardConstants[3] = 0;
                        break;
                    case DIAMOND:
                        cardConstants[3] = 1;
                        break;
                    case HEART:
                        cardConstants[3] = 2;
                        break;
                    case SPADE:
                        cardConstants[3] = 3;
                        break;
                }

                if(tableCache.deckOnTable[4] == null) {

                    tableCacheCardIndex[4] = -1;
                }

                else {

                    switch (tableCache.deckOnTable[4].getType()) {

                        case CLUB:
                            cardConstants[4] = 0;
                            break;
                        case DIAMOND:
                            cardConstants[4] = 1;
                            break;
                        case HEART:
                            cardConstants[4] = 2;
                            break;
                        case SPADE:
                            cardConstants[4] = 3;
                            break;
                    }
                }
            }

            if(tableCacheCardIndex[0] != -1) {

                int tableIndexNumber1 = tableCache.deckOnTable[0].getNumber() % 14;
                if(tableIndexNumber1 != 0)  tableIndexNumber1--;
                tableCacheCardIndex[0] = tableIndexNumber1 + cardConstants[0] * 13;
            }

            if(tableCacheCardIndex[1] != -1) {

                int tableIndexNumber2 = tableCache.deckOnTable[1].getNumber() % 14;
                if(tableIndexNumber2 != 0)  tableIndexNumber2--;
                tableCacheCardIndex[1] = tableIndexNumber2 + cardConstants[1] * 13;
            }

            if(tableCacheCardIndex[2] != -1) {

                int tableIndexNumber3 = tableCache.deckOnTable[2].getNumber() % 14;
                if(tableIndexNumber3 != 0)  tableIndexNumber3--;
                tableCacheCardIndex[2] = tableIndexNumber3 + cardConstants[2] * 13;
            }

            if(tableCacheCardIndex[3] != -1) {

                int tableIndexNumber4 = tableCache.deckOnTable[3].getNumber() % 14;
                if(tableIndexNumber4 != 0)  tableIndexNumber4--;
                tableCacheCardIndex[3] = tableIndexNumber4 + cardConstants[3] * 13;
            }

            if(tableCacheCardIndex[4] != -1) {

                int tableIndexNumber5 = tableCache.deckOnTable[4].getNumber() % 14;
                if(tableIndexNumber5 != 0)  tableIndexNumber5--;
                tableCacheCardIndex[4] = tableIndexNumber5 + cardConstants[4] * 13;
            }

        }

        return tableCacheCardIndex;
    }

    public boolean isIndexFree(int index, int[] playerCardsIndex, int[] tableCacheCardIndex, int[][] otherPlayersIndex) {

        for (int cardsIndex : playerCardsIndex) {
            if (cardsIndex == index)
                return false;
        }

        for (int cardsIndex : playerCardsIndex) {
            if (cardsIndex == index)
                return false;
        }

        for (int cacheCardIndex : tableCacheCardIndex) {
            if (cacheCardIndex == index)
                return false;
        }

        for (int[] playersIndex : otherPlayersIndex) {
            for (int j = 0; j < 2; j++) {
                if (playersIndex[j] == index)
                    return false;
            }
        }

        return true;
    }

    public int findMaxAvailableIndex(int[] playerCardsIndex, int[] tableCacheCardIndex) {

        int index = 51;

        int playerCards = (playerAmount - 1) * 2;

        while(playerCards != 0) {

            for (int cardsIndex : playerCardsIndex) {

                if (cardsIndex == index) {
                    index--;
                }
            }

            for (int cacheCardIndex : tableCacheCardIndex) {

                if (cacheCardIndex == index) {

                    index--;
                }
            }

            index--;
            playerCards--;
        }

        index++;

        return index;
    }

    public int findMaxAvailableIndexForCache(int[] playerCardsIndex, int[] tableCacheCardIndex) {

        int index = 51;

        int count = 2;

        while(count != 0) {

            for (int cardsIndex : playerCardsIndex) {

                if (cardsIndex == index) {
                    index--;
                }
            }

            for (int cacheCardIndex : tableCacheCardIndex) {

                if (cacheCardIndex == index) {

                    index--;
                }
            }

            index--;
            count--;
        }

        index++;

        return index;
    }

    public double calculate() {

        int[] playerCardsIndex = findPlayerCardIndex();

        int[][] otherPlayersIndex = new int[playerAmount-1][2];

        TableCache tempCache = new TableCache();

        for(int i = 0; i < otherPlayersIndex.length; i++) {
            for(int j = 0; j < 2; j++) {
                otherPlayersIndex[i][j] = -1;
            }
        }

        int[] tableCacheCardIndex = findTableCacheCardIndex();

        int stageCount = 0;

        int winCount = 0;

        int initialIndex = 0;

        Player[] otherPlayers = new Player[playerAmount - 1];

        PlayerWinCalculator pwin;

        for(int i = tableCacheCardAmount; i < 5 ; i++) {

            while(!isIndexFree(initialIndex, playerCardsIndex, tableCacheCardIndex, otherPlayersIndex)) {
                initialIndex++;
            }
            tableCacheCardIndex[i] = initialIndex;
        }

        for(int i = 0; i < playerAmount -1; i++) {
            for(int j = 0; j < 2; j++) {

                while (!isIndexFree(initialIndex, playerCardsIndex, tableCacheCardIndex, otherPlayersIndex)) {
                    initialIndex++;
                }
                otherPlayersIndex[i][j] = initialIndex;
            }
        }

        int limitForPlayers = findMaxAvailableIndex(playerCardsIndex, tableCacheCardIndex);
        int limitForTableCache = findMaxAvailableIndexForCache(playerCardsIndex, tableCacheCardIndex);

        while(((tableCacheCardAmount == 5 && (otherPlayersIndex[0][0] <= limitForPlayers)) || (tableCacheCardAmount < 5 && (tableCacheCardIndex[4] <= limitForTableCache))) && stageCount < 2000000) {

            for(int i = 0; i < otherPlayers.length; i++) {
                Card[] playerCards = new Card[2];
                playerCards[0] = new Card(deck.getDeck()[otherPlayersIndex[i][0]].getType(), deck.getDeck()[otherPlayersIndex[i][0]].getNumber());
                playerCards[1] = new Card(deck.getDeck()[otherPlayersIndex[i][1]].getType(), deck.getDeck()[otherPlayersIndex[i][1]].getNumber());
                otherPlayers[i] = new Player(playerCards);
            }
            Card card1 = new Card(deck.getDeck()[tableCacheCardIndex[0]].getType(),deck.getDeck()[tableCacheCardIndex[0]].getNumber());
            Card card2 = new Card(deck.getDeck()[tableCacheCardIndex[1]].getType(),deck.getDeck()[tableCacheCardIndex[1]].getNumber());
            Card card3 = new Card(deck.getDeck()[tableCacheCardIndex[2]].getType(),deck.getDeck()[tableCacheCardIndex[2]].getNumber());
            Card card4 = new Card(deck.getDeck()[tableCacheCardIndex[3]].getType(),deck.getDeck()[tableCacheCardIndex[3]].getNumber());
            Card card5 = new Card(deck.getDeck()[tableCacheCardIndex[4]].getType(),deck.getDeck()[tableCacheCardIndex[4]].getNumber());

            Card[] theFlop = {card1, card2, card3};

            tempCache.addFlopCards(theFlop);
            tempCache.addTheTurn(card4);
            tempCache.addTheRiver(card5);

            pwin =  new PlayerWinCalculator(tempCache, mainPlayer, otherPlayers);

            if(pwin.canPlayerWin()) winCount++;

            int initPlayerIndex = otherPlayersIndex[playerAmount - 2][1];

            while(!isIndexFree(initPlayerIndex, playerCardsIndex, tableCacheCardIndex, otherPlayersIndex))
                initPlayerIndex += 1;

            otherPlayersIndex[playerAmount - 2][1] = initPlayerIndex;

            for(int i = playerAmount - 2; i >= 0; i--) {

                if(otherPlayersIndex[i][1] > 51) {

                    initPlayerIndex = otherPlayersIndex[i][0];

                    while(!isIndexFree(initPlayerIndex, playerCardsIndex, tableCacheCardIndex, otherPlayersIndex))
                       initPlayerIndex++;

                    otherPlayersIndex[i][0] = initPlayerIndex;

                    otherPlayersIndex[i][1] = otherPlayersIndex[i][0];

                    initPlayerIndex = otherPlayersIndex[i][1];

                    while(!isIndexFree(initPlayerIndex, playerCardsIndex, tableCacheCardIndex, otherPlayersIndex))
                        initPlayerIndex++;

                    otherPlayersIndex[i][1] = initPlayerIndex;
                }

                if(otherPlayersIndex[i][0] >= 51) {

                    if(i != 0) {

                        initPlayerIndex = otherPlayersIndex[i - 1][1];

                        while (!isIndexFree(initPlayerIndex, playerCardsIndex, tableCacheCardIndex, otherPlayersIndex))
                            initPlayerIndex++;

                        otherPlayersIndex[i - 1][1] = initPlayerIndex;

                        otherPlayersIndex[i][0] = otherPlayersIndex[i - 1][1];

                        initPlayerIndex = otherPlayersIndex[i][0];

                        while (!isIndexFree(initPlayerIndex, playerCardsIndex, tableCacheCardIndex, otherPlayersIndex))
                            initPlayerIndex++;

                        otherPlayersIndex[i][0] = initPlayerIndex;

                    }

                    else {

                        int temporaryIndex = tableCacheCardIndex[4];

                        while(!isIndexFree(temporaryIndex,playerCardsIndex, tableCacheCardIndex,otherPlayersIndex)) {

                            temporaryIndex++;
                        }

                        tableCacheCardIndex[4] = temporaryIndex;

                        for(int j = 4; j >= tableCacheCardAmount; j--) {

                            if(tableCacheCardIndex[j] > 51) {

                                temporaryIndex = tableCacheCardIndex[j - 1];

                                while(!isIndexFree(temporaryIndex, playerCardsIndex, tableCacheCardIndex,otherPlayersIndex)) {
                                    temporaryIndex++;
                                }

                                tableCacheCardIndex[j - 1] = temporaryIndex;

                                temporaryIndex = tableCacheCardIndex[j - 1];

                                while(!isIndexFree(temporaryIndex, playerCardsIndex, tableCacheCardIndex,otherPlayersIndex)) {
                                    temporaryIndex++;
                                }

                                tableCacheCardIndex[j] = temporaryIndex;
                            }
                        }

                        initialIndex = 0;

                        for(int j = 0; j < playerAmount - 1; j++) {
                            for(int k = 0; k < 2; k++) {

                                while(!isIndexFree(initialIndex, playerCardsIndex, tableCacheCardIndex, otherPlayersIndex))
                                    initialIndex++;

                                otherPlayersIndex[j][k] = initialIndex;
                            }
                        }
                    }
                }
            }

            stageCount++;
        }

        System.out.println(stageCount);
        return (double)winCount / (double)stageCount * 100.0;
    }
}
