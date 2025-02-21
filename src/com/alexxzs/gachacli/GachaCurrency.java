package com.alexxzs.gachacli;

public enum GachaCurrency {
    INSTANCE;

    private final int SINGLE_WISH_COST = 160; //donc MULTI_WISH_COST = 1600;
    private int currentBalance = 1600; //le joueur part avec 1600 primogemmes.
    private final int PROBABILITY_FACTOR = 100;


    public void mathMinigame() {

        System.out.println("\nThe aim of this game is to give the correct answer to the equation that will appear on your screen.\n" +
                            "You'll earn 160 currency for each good answer. Your turn!\n" +
                            "Note : Input \"-1\" to leave the game.");

        while (true) {
            int number1 = (int) (Math.random() * PROBABILITY_FACTOR);
            int number2 = (int) (Math.random() * PROBABILITY_FACTOR);
            int sum = number1 + number2;

            int answer = GachaInput.answerMinigame(number1 + " + " + number2 + " = ", sum);
            if (answer == -1) {
                System.out.println("You now have " + currentBalance + " currency.");
                break;
            }
            currentBalance += (answer == sum) ? SINGLE_WISH_COST : 0;
        }



    }



    public void deductCurrentBalance(int numberOfPulls) {
        currentBalance -= numberOfPulls*SINGLE_WISH_COST;
    }


    public int getCurrentBalance() {
        return currentBalance;
    }

    public int getSINGLE_WISH_COST() {
        return SINGLE_WISH_COST;
    }

    public static GachaCurrency getGachaCurrency() {
        return INSTANCE;
    }
}
