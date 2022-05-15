package com.chengoconnell.unquote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions = new ArrayList<Question>();

    public void startNewGame() {
        Question questionOne = new Question(921238, "How tall is the Eiffel tower?", "1024 ft", "1063 ft", "1124 ft", "1163 ft", 1);
        Question questionTwo = new Question(107343, "Who invented the computer algorithm?", "Charles Babbage", "John Carmack", "Alan Turing", "Ada Lovelace", 3);
        Question questionThree = new Question(748294, "What is the name for the patch of skin found on your elbow?", "Elbow Skin", "Fascia Elbora", "Wenis", "Todd", 2);

        questions.add(questionOne);
        questions.add(questionTwo);
        questions.add(questionThree);

        totalCorrect = 0;
        totalQuestions = questions.size();

        Question firstQuestion = chooseNewQuestion();
        // displayQuestion(firstQuestion);
        // displayQuestionsRemaining(questions.size());
    }

    public Question chooseNewQuestion() {
        int randomNumber = generateRandomNumber(totalQuestions);
        currentQuestionIndex = randomNumber;
        return questions.get(currentQuestionIndex);
    }

    public Question getCurrentQuestion() {
        return questions.get(currentQuestionIndex);
    }

    public void onAnswerSubmission() {
        Question currentQuestion = getCurrentQuestion();
        if (currentQuestion.isCorrect()) {
            totalCorrect ++;
        }
        questions.remove(currentQuestion);

        // displayQuestionsRemaining(questions.size());
        if (questions.size() == 0) {
            getGameOverMessage(totalCorrect, totalQuestions);
            startNewGame();
        } else {
            chooseNewQuestion();
            // TODO: uncomment after implementing displayQuestion()
            // displayQuestion(getCurrentQuestion());
        }
    }

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