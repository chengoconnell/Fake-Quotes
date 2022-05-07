package com.chengoconnell.unquote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    public int generateRandomNumber(int max) {
        double randomNumber = Math.random();
        double randomNumberWithinRange = randomNumber * max;
        int randomIntegerWithinRange = (int)randomNumberWithinRange;
        return randomIntegerWithinRange;
    }

    public String getGameOverMessage(int totalCorrect, int totalQuestions) {
        if (totalCorrect == totalQuestions) {
            String allCorrectMessage = "You got all " + totalQuestions + " correct! You won!";
            return allCorrectMessage;
        } else {
            String someOrNoneCorrectMessage = "You got " + totalCorrect + " right out of " + totalQuestions + ". Better luck next time!";
            return someOrNoneCorrectMessage;
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }
}