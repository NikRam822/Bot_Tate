package com.company.speedbaccara;

import com.company.module.Exodus;
import com.company.module.Card;
import com.company.module.Deck;
import com.company.main.IGame;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

import static com.company.speedbaccara.GameVisualizer.getBet;

/**
 * Класс для реализации игры SpeedBaccara.
 */
public class SpeedBaccara implements IGame, ISpeedBaccara {

    @Override
    public String isGameOver(int Player, int Banker) {
        if (Banker > Player) {
            System.out.println("Банкир выиграл!");
            return String.valueOf(com.company.module.Exodus.Banker);
        } else if (Banker < Player) {
            System.out.println("Игрок выиграл!");
            return String.valueOf(Exodus.Player);
        } else {
            System.out.println("Ого! Ничья!");
            return String.valueOf(Exodus.Draw);
        }
    }

    @Override
    public void countinuationResolution(Stack<Card> deckBanker, Stack<Card> deckPlayer, String text) {
        if (text.equals("/exit")) {
            System.out.println("До свидания! Возвращайтесь еще!");
            deckBanker.clear();
            deckPlayer.clear();
        }
    }

    @Override
    public  String paymentNaturalWin(int sumPlayer, int sumBanker) {
        if (sumBanker == 9) {
            System.out.println("Вау! Натуральная победа у Банкира!");
            return String.valueOf(Exodus.Banker);
        } else if (sumPlayer == 9) {
            System.out.println("Вау! Натуральная победа у Игрока!");
            return String.valueOf(Exodus.Player);
        } else { return String.valueOf(Exodus.None); }
    }

    @Override
    public int countOfBank(ArrayList<Integer> bets, int bank, String exodus, String exodusNaturalWin) {
        int win = 0;
        bank = bank - (bets.get(0) + bets.get(1) + bets.get(2) + bets.get(3) + bets.get(4));
        switch (exodus) {
            case "Banker" -> win = win + (bets.get(0) * 2);
            case "Player" -> win = win + (bets.get(2) * 2);
            case "Draw" -> win = win + (bets.get(3) * 21) + bets.get(0) + bets.get(2);
        }
        switch (exodusNaturalWin) {
            case "Banker" -> win = win + (bets.get(3) * 10);
            case "Player" -> win = win + (bets.get(4) * 10);
        }
        bank += win;
        return bank;
    }

    @Override
    public void realizationGame(Stack<Card> deckBanker, Stack<Card> deckPlayer, int bank) {
        Deck deck = new Deck();
        Scanner scan = new Scanner(System.in);
        int numberGame = 0;
        while (deckBanker.size() + deckPlayer.size()>21 ) {
            numberGame++;
            getBet();
            String textAct = scan.nextLine();
            countinuationResolution(deckBanker,deckPlayer,textAct);
            if (!deckBanker.isEmpty() & !deckPlayer.isEmpty()) {
                int[] banks = deck.giveCards(deckBanker, deckPlayer, numberGame);
                String exodus = isGameOver(banks[1], banks[0]);
                String exodusNaturalWin = paymentNaturalWin(banks[1], banks[0]);
                ArrayList<Integer> takeBets = com.company.module.Bet.Takebet(textAct,bank);
                int win = countOfBank(takeBets, bank, exodus, exodusNaturalWin) - bank;
                if (win < 0) { win = 0; }
                System.out.println("Выигрыш: " + win + " руб.\nБанк: " + countOfBank( takeBets , bank, exodus, exodusNaturalWin) + " руб.");
                bank = countOfBank( takeBets , bank, exodus, exodusNaturalWin);
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } else {
                deckBanker.clear();
                deckPlayer.clear();
            }
        }
        if ( numberGame == 21) {
            System.out.println("Колода закончилась! Начнем заново?\n/start - да\n/exit - нет");
        }
    }

    @Override
    public void play() {
        GameVisualizer.getInstractionGreeting();
        doCycle();
    }

    @Override
    public void doCycle() {
        Deck deck = new Deck();
        Scanner scan = new Scanner(System.in);
        boolean heartGame = true;
        while (heartGame) {
            String text = scan.nextLine();
            String textStartRepeat = "/start";
            String textStopNoRepeat = "/exit";
            if (textStartRepeat.contains(text) & !text.equals("")) {
                int bank = 10000;
                System.out.println("Хорошо! Колоды перетасованы!\nБанк: " + bank + " руб.");
                realizationGame(deck.creatingDeck(), deck.creatingDeck(), bank/*, deck*/);
            } else if (textStopNoRepeat.contains(text)) {
                System.out.println("Хорошо! Захочешь поиграть, заходи!");
                heartGame = false;
            } else if (text.equals("/instrumentation")) {
                System.out.println("Хорошо! Вот инструкция!");
                GameVisualizer.getHowPlay();
            } else {
                System.out.println("Не понял команду!");
            }
        }
    }
}