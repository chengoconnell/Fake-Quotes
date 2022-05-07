package com.chengoconnell.unquote;

public class Main {
    public static void main(String[] args) {
        Question testQuestion = new Question(0, "Who invented the computer algorithm?", "Bill Gates", "Charles Babbage", "Ada Lovelace", "Leonardo DaVinci", 2);

        System.out.println("The player’s guess is: " + testQuestion.playerAnswer);
        if (testQuestion.playerAnswer == -1) {
            System.out.println("Default answer selected!");
        }
        testQuestion.playerAnswer = 2;
        System.out.println("The player’s guess is: " + testQuestion.playerAnswer);
        if (testQuestion.isCorrect()) {
            System.out.println("The player’s guess is correct!");
        } else {
            System.out.println("The player’s guess is incorrect!");
        }

        MainActivity mainActivity = new MainActivity();
        System.out.println("A random number between 0 and 50: " + mainActivity.generateRandomNumber(50));
    }
}
