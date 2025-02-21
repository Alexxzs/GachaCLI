package com.alexxzs.gachacli;

import java.util.InputMismatchException;
import java.util.Scanner;

public final class GachaInput {

    private GachaInput() {}

    private static final Scanner scanner = new Scanner(System.in);

    public static int inputChoice(String prompt, int min, int max) {
        System.out.println(prompt);
        int choice;

        while (true) {
            System.out.print("> ");
            try {
                choice = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: invalid input! Please input a number.");
                scanner.next();
                continue;
            }
            if (choice >= min && choice <= max)
                break;
            else
                System.out.println("Error: invalid input! Please input a number between " + min + " and " + max + "!");
        }

        return choice;
    }

    public static int numberOfPullsChoice(GachaCurrency currency) {
        int numberOfPulls = -1; //dans Main.java, si numberOfPulls est à -1, revient au menu.

        int choice = inputChoice("You have " + currency.getCurrentBalance() + " currency.\n" +
                "============\n " +
                "1. Do a single pull\n" +
                "2. Do a multi pull (10 wishes)\n" +
                "3. Cancel", 1, 3);

        switch (choice) {
            case 1 -> numberOfPulls = (currency.getCurrentBalance() >= currency.getSINGLE_WISH_COST()) ? 1 : 0;
            case 2 -> numberOfPulls = (currency.getCurrentBalance() >= 10*currency.getSINGLE_WISH_COST()) ? 10 : 0;
        }

        return numberOfPulls;
    }

    public static Banner bannerChoice() {
        Banner banner = Banner.NONE; //si l'utilisateur renvoie 3, renvoie NONE, ce qui ne lancera pas la méthode pull() dans Main.java.

        int choice = inputChoice("1. Temp banner\n" +
                                        "2. Perma banner\n" +
                                        "3. Cancel", 1, 3);

        switch (choice) {
            case 1 -> banner = Banner.LIMITED;
            case 2 -> banner = Banner.STANDARD;
        }
        return banner;
    }


    public static int answerMinigame(String input, int goodAnswer) {
        int answer = -1;


        while (true) {
            System.out.println(input);
            System.out.print("> ");
            try {
                answer = scanner.nextInt();
            } catch (InputMismatchException e) {
                System.out.println("Error: invalid input! Please input a number.");
                scanner.next();
            }

            if (answer == goodAnswer) {
                System.out.println("Good job, you found the good answer!");
                break;
            } else if (answer == -1)
                break;
            else
                System.out.println("No! Try again.");

        }

        return answer;
    }

    public static void closeScanner() {
        scanner.close();
    }

}
