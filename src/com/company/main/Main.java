package com.company.main;

import com.company.speedbaccara.GameVisualizer;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
    /*   App start = new App();
        try {
            start.run();
        } catch (Exception exception) {
            exception.printStackTrace();
        }*/

        Menu menu = new Menu();
        Scanner input = new Scanner(System.in);
        GameVisualizer.getHello();

        for (; ; ) {
            String command = input.nextLine();
            menu.doCommandOfMenu(command);
        }
    }
}
