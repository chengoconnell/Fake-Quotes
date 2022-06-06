package com.chengoconnell.unquote;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.nio.channels.Channels;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    int currentQuestionIndex;
    int totalCorrect;
    int totalQuestions;
    ArrayList<Question> questions = new ArrayList<Question>();
    ImageView questionImageView;
    TextView questionTextView;
    TextView questionRemainingTextView;
    Button answer0Button;
    Button answer1Button;
    Button answer2Button;
    Button answer3Button;
    Button submitButton;

    public void startNewGame() {
        Question questionOne = new Question(R.drawable.img_quote_0, "Pretty good advice, and perhaps a scientist did say it... Who actually did?", "Albert Einstein", "Isaac Newton", "Rita Mae Brown", "Rosalind Franklin", 2);
        Question questionTwo = new Question(R.drawable.img_quote_1, "Was honest Abe honestly quoted? Who authored this pithy bit of wisdom?", "Edward Stieglitz", "Maya Angelou", "Abraham Lincoln", "Ralph Waldo Emerson", 0);
        Question questionThree = new Question(R.drawable.img_quote_2, "Easy advice to read, difficult advice to follow — who actually said it?", "Martin Luther King Jr.", "Mother Teresa", "Fred Rogers", "Oprah Winfrey", 1);

        questions.add(questionOne);
        questions.add(questionTwo);
        questions.add(questionThree);

        totalCorrect = 0;
        totalQuestions = questions.size();

        Question firstQuestion = chooseNewQuestion();
        displayQuestion(firstQuestion);
        displayQuestionsRemaining(questions.size());
    }

    public Question chooseNewQuestion() {
        int newQuestionIndex = generateRandomNumber(questions.size());
        currentQuestionIndex = newQuestionIndex;
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

        displayQuestionsRemaining(questions.size());
        if (questions.size() == 0) {
            String gameOverMessage = getGameOverMessage(totalCorrect, totalQuestions);
            System.out.println(gameOverMessage);
            startNewGame();
        } else {
            chooseNewQuestion();
            displayQuestion(getCurrentQuestion());
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

    public void displayQuestionsRemaining(int questionRemaining) {
        questionRemainingTextView.setText(questionRemaining + "");
    }

    public void displayQuestion(Question currentQuestion) {
        questionImageView.setImageResource(currentQuestion.imageId);
        questionTextView.setText(currentQuestion.questionText);
        answer0Button.setText(currentQuestion.answer0);
        answer1Button.setText(currentQuestion.answer1);
        answer2Button.setText(currentQuestion.answer2);
        answer3Button.setText(currentQuestion.answer3);
    }

    public void onAnswerSelected(int answerSelection) {
        Question currentQuestion = getCurrentQuestion();
        currentQuestion.playerAnswer = answerSelection;
        answer0Button.setText(currentQuestion.answer0);
        answer1Button.setText(currentQuestion.answer1);
        answer2Button.setText(currentQuestion.answer2);
        answer3Button.setText(currentQuestion.answer3);

        if (answerSelection == 0) {
            answer0Button.setText("✔ " + currentQuestion.answer0);
        } else if (answerSelection == 1) {
            answer1Button.setText("✔ " + currentQuestion.answer1);
        } else if (answerSelection == 2) {
            answer2Button.setText("✔ " + currentQuestion.answer2);
        } else {
            answer3Button.setText("✔ " + currentQuestion.answer3);
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setLogo(R.drawable.ic_unquote_icon);
        getSupportActionBar().setDisplayUseLogoEnabled(true);
        getSupportActionBar().setElevation(0);
        setContentView(R.layout.activity_main);

        questionImageView = findViewById(R.id.iv_main_question_image);
        questionTextView = findViewById(R.id.tv_main_question_title);
        questionRemainingTextView = findViewById(R.id.tv_main_questions_remaining_count);
        answer0Button = findViewById(R.id.btn_main_answer_0);
        answer1Button = findViewById(R.id.btn_main_answer_1);
        answer2Button = findViewById(R.id.btn_main_answer_2);
        answer3Button = findViewById(R.id.btn_main_answer_3);
        submitButton = findViewById(R.id.btn_main_submit_answer);

        answer0Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(0);
            }
        });
        answer1Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(1);
            }
        });
        answer2Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(2);
            }
        });
        answer3Button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onAnswerSelected(3);
            }
        });

        startNewGame();
    }
}