package com.alexxzs.gachacli;

import java.util.List;

public class GachaUI {

    private final GachaMachine gacha;

    public GachaUI(GachaMachine gacha) {
        this.gacha = gacha;
    }


    private final String BOLD = "\033[0;1m";
    private final String YELLOW = "\u001B[33m";
    private final String RESET = "\u001B[0m";
    private final String PURPLE = "\u001B[35m"; //pour pouvoir afficher des couleurs dans la console, pour consoles compatibles

    public void printResult(String name, int rarity) { //pour alléger le code de la méthode pull()
        if (rarity == 5)
            System.out.println(BOLD + "Congratulations !! You just got " + YELLOW + name + " (" + rarity + "*)" + RESET + BOLD + " !! (pity " + gacha.getFiveStarPity() + ")" + RESET);
        else if (rarity == 4)
            System.out.println("GG, you got " + BOLD + PURPLE + name + " (" + rarity + "*)" + RESET + " !");
        else
            System.out.println("You got " + BOLD + name + " (" + rarity + "*)" + RESET + ".");
    }


    public void printHistory(List<GachaItem> pullHistory) {

        if (pullHistory.isEmpty()) {
            System.out.println("Your pull history is empty!");
            return;
        }

        System.out.println("===== PULL HISTORY =====");

        pullHistory.forEach(pull -> {
            String formattedPrint = (pull.getName() + " (" + pull.getRarity() +"*)"); //affichage du nom + rareté

            switch (pull.getRarity()) {
                case 5 -> System.out.println(BOLD + YELLOW + formattedPrint + RESET);
                case 4 -> System.out.println(PURPLE + formattedPrint + RESET); //couleurs pour chaque rareté
                case 3 -> System.out.println(formattedPrint);
            }
        });

        System.out.println("================");

    }


}