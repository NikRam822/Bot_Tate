package com.company.main;

import com.company.commands.*;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;

/**
 * Класс меню пользователя
 */
public class Menu implements IMenu {

    @Override
    public  void doCommandOfGame() {
        boolean exit = false;
        Scanner in = new Scanner(System.in);
        String command=in.nextLine();
        Map<String, Command> gameCommand = new HashMap<>();
        gameCommand.put("/SpeedBaccara", new SpeedBaccaraGame());
        gameCommand.put("/BlackJack", new BlackJackGame());
        gameCommand.put("/exit", new GameExit());
        while (exit == false) {
            try {
                ICommand iCommand = gameCommand.get(command);
                iCommand.execute();
                exit = true;
            } catch (Exception exception) {
                System.out.println("Не понял команду!");
                command = in.nextLine();
            }
        }
    }

    @Override
    public  void doCommandOfMenu(String command) {
        Map<String, Command> menuCommand = new HashMap<>();
        menuCommand.put("/exit", new MenuExit());
        menuCommand.put("/help", new MenuHelp());
        menuCommand.put("/play", new MenuGame());
        try {
            ICommand iCommand2 = menuCommand.get(command);
            iCommand2.execute();
        } catch (Exception exception) {
            System.out.println("Не понял команду!");
        }
    }
}
