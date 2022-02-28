package com.company.speedbaccara;

import com.company.module.Card;

import java.util.Stack;

/**
 * Интерфейс для обязательной реализации специальных методов игры SpeedBaccara.
 */
public interface ISpeedBaccara {

    /**
     * Основной цикл программы.
     */
    void doCycle();

    /**
     * Метод для сравнения суммы очков на наличие наутральной победы у Игрока и/или у Банкира.
     * @param sumPlayer сумма очков Игрока
     * @param sumBanker сумма очков Банкира
     * @return результат наличия наутральной победы у Игрока и/или у Банкира
     */
    String paymentNaturalWin(int sumPlayer, int sumBanker);

    /**
     * Метод для обработки основного кода программы.
     * @param deckBanker колода Банкира
     * @param deckPlayer колода Игрока
     * @param bank сумма средств
     */
    void realizationGame(Stack<Card> deckBanker, Stack<Card> deckPlayer, int bank);

    /**
     * Метод для проверки выхода из игры.
     * @param text текст от пользоваателя
     * @param deckBanker колода Банкира
     * @param deckPlayer колода Игрока
     */
    void countinuationResolution(Stack<Card> deckBanker, Stack<Card> deckPlayer, String text);
}
