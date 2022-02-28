package com.company.module;

import com.company.speedbaccara.GameVisualizer;
import java.util.Collections;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Stack;

/**
 * Класс описывающий сущность колода.
 */
public class Deck {

    /**
     * Метод для генерации колод.
     * @return сгенерированная колода
     */
    public Stack<Card> creatingDeck() {
        int i = 0;
        Stack<Card> deck = new Stack<>();
        String[] deckChanged = new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K", "A"};
        String[] deckSuit = new String[]{"♥", "♦", "♣", "♠"};
        Card[] deckStart = new Card[52];
        for (String changed : deckChanged){
            for (String suit : deckSuit) {
                deckStart[i] = new Card(changed, suit);
                i++;
            }
        }
        Collections.shuffle(Arrays.asList(deckStart));
        for(int j=0; j<52; j++) {
            deck.push(deckStart[j]);
        }
        return deck;
    }

    /**
     * Метод для выдачи карты из колоды.
     * @param deckStart колода
     * @return карта выбранная из колоды
     */
    public  Card getCard( Stack<Card> deckStart) {
        Card card =deckStart.peek();
        return deckStart.pop();
    }

    /**
     * Метод выдачи карт Банкиру и Игроку.
     * @param deckBanker колода Банкира
     * @param deckPlayer колода Игрока
     * @return карты Банкира и карты Игрока
     */
    public  int[] giveCards(Stack<Card> deckBanker, Stack<Card> deckPlayer, int numberGame) {
        Deck newCard=new Deck();
        Card cardBanker1 = newCard.getCard( deckBanker);
        Card cardBanker2 = newCard.getCard( deckBanker);
        Card cardPlayer1 = newCard.getCard( deckPlayer);
        Card cardPlayer2 = newCard.getCard(deckPlayer);
        CardHand handBanker = new CardHand(new ArrayList<>(Arrays.asList(cardBanker1,cardBanker2)));
        CardHand handPlayer = new CardHand(new ArrayList<>(Arrays.asList(cardPlayer1,cardPlayer2)));
        GameVisualizer.getDistributionNumber(numberGame);
        int sumBanker = resetPoints(toValueCard(cardBanker1.valueCards) + toValueCard(cardBanker2.valueCards));
        int sumPlayer = resetPoints(toValueCard(cardPlayer1.valueCards) + toValueCard(cardPlayer2.valueCards));
        GameVisualizer.getDistributionHands(handBanker,handPlayer);
        return new int[]{sumBanker, sumPlayer};
    }

    /**
     * Метод для сброса очков привысивших или равных 10.
     * @param points очки Банкира или Игрока
     * @return очки меньше 10
     */
    private    int resetPoints(int points) {
        while (points >= 10) { points -= 10; }
        return points;
    }

    /**
     * Метод для перевода карт значения String в тип int.
     * @param card значение карты
     * @return значение карты типа int
     */
    private  int toValueCard(String card) {
        return switch (card) {
            case "J", "Q", "K", "10" -> 0;
            case "A" -> 1;
            default -> Integer.parseInt(card);
        };
    }
}
