package com.alexxzs.gachacli;

public class Main {

    public static void main(String[] args) {

        GachaCurrency currency = GachaCurrency.getGachaCurrency();
        GachaMachine gacha = new GachaMachine(currency);
        GachaUI gachaUI = new GachaUI(gacha);
        Banner banner = Banner.NONE;
        boolean active = true;

        gacha.setGachaUI(gachaUI);


        while (active) {
            int choice = GachaInput.inputChoice("Welcome to Gacha CLI! Make your choice: :\n" +
                    "1. View my inventory\n" +
                    "2. Earn currency\n" +
                    "3. Make a pull\n" +
                    "4. Quit", 1, 4);


            switch (choice) {
                case 1 -> gachaUI.printHistory(gacha.getPullHistory());
                case 2 -> currency.mathMinigame();

                case 3 -> {

                    int numberOfPulls = GachaInput.numberOfPullsChoice(currency);

                    if (numberOfPulls > 0)
                        banner = GachaInput.bannerChoice();
                    else if (numberOfPulls == 0)
                        System.out.println("Error: You don't have enough currency to make this much pulls!");

                    if (banner.equals(Banner.STANDARD) || banner.equals(Banner.LIMITED))
                        gacha.pull(numberOfPulls, banner);
                    else
                        System.out.println("Back to main menu...");

                }

                case 4 -> {
                    System.out.println("Thanks for using Gacha CLI! See you next time!");
                    GachaInput.closeScanner();
                    active = false;
                }

            }
        }

    }

}
