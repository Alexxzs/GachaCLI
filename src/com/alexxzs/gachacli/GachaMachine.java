package com.alexxzs.gachacli;

import java.util.ArrayList;
import java.util.List;

public class GachaMachine {

    GachaUI gachaUI;
    GachaCurrency currency;

    public GachaMachine(GachaCurrency currency) {
        this.currency = currency;
    }

    private List<GachaItem> pullHistory = new ArrayList<>();

    private int fiveStarPity = 0;
    private int fourStarPity = 0;
    private final int FIVE_STAR_HARD_PITY = 90;
    private final int FOUR_STAR_HARD_PITY = 10; //si le joueur n'a pas eu un personnage [4/5]* depuis [10/90] voeux, en donne un, par système de pity.

    private final double FIVE_STAR_PROBABILITY = 1; // 2% (0-1) de chance d'obtenir un 5*
    private final double FOUR_STAR_PROBABILITY = 11; // 10% (2-11) de chance d'obtenir un 4*
    private final double PROBABILITY_FACTOR = 100; //probabilités déterminées sur 100 (pourcentages sans unité)

    private final int WINNING_FIFTY_FIFTY_PROBABILITY = 49; //50% (0-49) de chance de gagner le 50/50
    private boolean isGuaranteed = false; //système de garantie lors d'une perte de 50/50 dans rollFiftyFifty()




    // TODO: factoriser cette méthode. séparer logique conditionnelle et gestion des pity?

    public void pull(int numberOfPulls, Banner banner) {
        int probability;

        for (int pullNumber = 0; pullNumber < numberOfPulls; pullNumber++) {

            fiveStarPity++;
            fourStarPity++;

                probability = (int) (Math.random() * PROBABILITY_FACTOR);

                if (probability <= FIVE_STAR_PROBABILITY || fiveStarPity == FIVE_STAR_HARD_PITY) {  //obtention d'un 5*, condition mis avant 4* pour prioriser le 5* face au 4*
                    pullHistory.add(obtainItem(banner));
                    fiveStarPity = 0;
                }

                else if ((probability > FIVE_STAR_PROBABILITY && probability <= FOUR_STAR_PROBABILITY) || fourStarPity >= FOUR_STAR_HARD_PITY) { //obtention d'un 4*
                    pullHistory.add(obtainItem(GachaItem.FOUR_STAR_POOL));
                    fourStarPity = 0;
                }

                else //obtention d'un 3*
                    pullHistory.add(obtainItem(GachaItem.THREE_STAR_POOL));

        }

        currency.deductCurrentBalance(numberOfPulls); //on déduit le solde du joueur finalement.
    }



    // TODO : réécrire la méthode obtainItem pour proprement l'overload.

    private GachaItem obtainItem(Banner banner) {  //pour les 5*, nécessitent la bannière comme paramètre.
        GachaItem resultat;
        GachaItem[] pool = GachaItem.STANDARD_FIVE_STAR_POOL; //initialisation sur la bannière standard.

        switch (banner) {
            case LIMITED -> {
                if (isGuaranteed) { //s'il est en garantie (s'il a perdu au dernier 50/50), alors il va obligatoirement avoir un personnage LIMITED.
                    pool = GachaItem.LIMITED_FIVE_STAR_POOL;
                    isGuaranteed = false; //on lui enlève la garantie aussitôt.
                } else
                    pool = rollFiftyFifty();
            }
            case STANDARD -> pool = GachaItem.STANDARD_FIVE_STAR_POOL; //s'il choisit standard, piocher dans standard.
        }

        resultat = pool[(int)(Math.random()*pool.length)];
        gachaUI.printResult(resultat.getName(), resultat.getRarity());
        return resultat;

    }


    private GachaItem obtainItem(GachaItem[] pool) {
        GachaItem resultat;
        resultat = pool[(int)(Math.random()*pool.length)];
        gachaUI.printResult(resultat.getName(), resultat.getRarity());
        return resultat;

    }



    private GachaItem[] rollFiftyFifty() {
        GachaItem[] pool;
        int roll = (int) (Math.random() * PROBABILITY_FACTOR);

        if (roll < WINNING_FIFTY_FIFTY_PROBABILITY) {
            pool = GachaItem.LIMITED_FIVE_STAR_POOL; //50% de chance d'obtenir un personnage limité
        } else {
            pool = GachaItem.STANDARD_FIVE_STAR_POOL;
            isGuaranteed = true;
        }

        return pool;
    }


    public List<GachaItem> getPullHistory() {
        return pullHistory;
    }

    public int getFiveStarPity() {
        return fiveStarPity;
    }

    public void setGachaUI(GachaUI gachaUI) {
        this.gachaUI = gachaUI;
    }
}
