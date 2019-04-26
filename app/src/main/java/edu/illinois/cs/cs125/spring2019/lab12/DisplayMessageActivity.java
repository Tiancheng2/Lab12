package edu.illinois.cs.cs125.spring2019.lab12;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

/**
 * Questions screen.
 */
public class DisplayMessageActivity extends AppCompatActivity {
    /**
     * parse json given by api.
     * @param json
     * comment.
     */
    public void jsonParsing(final String json) {
        try {
            JsonParser jsonParser = new JsonParser();
            JsonObject parsed = jsonParser.parse(json).getAsJsonObject();
            JsonArray multipleQuestions = parsed.getAsJsonArray("question");
            for (JsonElement question : multipleQuestions) {
                JsonObject element = question.getAsJsonObject();
                String questions = element.get("question").getAsString();
                String rightAnswer = element.get("correct_answer").getAsString();
                JsonArray wrongAnswersJson = element.getAsJsonArray("incorrect_answers").getAsJsonArray();
                String[] wrongAnswers = new String[wrongAnswersJson.size()];
                for (int i = 0; i < wrongAnswersJson.size(); i++) {
                    String wrongAnswer = wrongAnswersJson.get(i).getAsString();
                    wrongAnswers[i] = wrongAnswer;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * determine if the answer is correct.
     * @param json
     * comment.
     */
    public void getQuestion(final String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject parsed = jsonParser.parse(json).getAsJsonObject();
        JsonArray multipleQuestions = parsed.getAsJsonArray("question");
        for (JsonElement question : multipleQuestions) {
            JsonObject element = question.getAsJsonObject();
            element.get("correct_answer").getAsString();
        }
    }

    /**
     * get the right answer.
     * @param json
     * comment.
     */
    public void getAnswer(final String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject parsed = jsonParser.parse(json).getAsJsonObject();
        JsonArray multipleQuestions = parsed.getAsJsonArray("question");
        for (JsonElement question : multipleQuestions) {
            JsonObject element = question.getAsJsonObject();
            element.get("correct_answer").getAsString();
        }
    }

    /**
     * return wrong answer array.
     * @param json
     * comment.
     */
    public void getWrongAnswers(final String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject parsed = jsonParser.parse(json).getAsJsonObject();
        JsonArray multipleQuestions = parsed.getAsJsonArray("question");
        for (JsonElement question : multipleQuestions) {
            JsonObject element = question.getAsJsonObject();
            JsonArray wrongAnswersJson = element.getAsJsonArray("incorrect_answers").getAsJsonArray();
            String[] wrongAnswers = new String[wrongAnswersJson.size()];
            for (int i = 0; i < wrongAnswersJson.size(); i++) {
                String wrongAnswer = wrongAnswersJson.get(i).getAsString();
                wrongAnswers[i] = wrongAnswer;
            }
        }
    }
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_message);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });
        displayWrong1();
        displayWrong2();
        displayWrong3();
        displayRight();
    }
    /**
     * if wrong answer is pressed.
     */
    public void displayWrong1() {
        String placeholder = "";
        final String words = "Wrong answer!";
        getWrongAnswers(placeholder);
        final TextView changingText = (TextView) findViewById(R.id.wrongAnswer);
        Button changeTextButton = (Button) findViewById(R.id.answer1);
        Button appear = (Button) findViewById(R.id.next_question);
        Button answer1 = (Button) findViewById(R.id.answer2);
        Button answer2 = (Button) findViewById(R.id.answer3);
        Button answer3 = (Button) findViewById(R.id.answer4);
        appear.setVisibility(View.INVISIBLE);
        changeTextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                changingText.setText(words);
                appear.setVisibility(View.VISIBLE);
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                changeTextButton.setEnabled(false);
            }
        });
    }
    /**
     * if wrong answer is pressed.
     */
    public void displayWrong2() {
        final String words = "Wrong answer again!";
        final TextView changingText = (TextView) findViewById(R.id.wrongAnswer);
        Button changeTextButton = (Button) findViewById(R.id.answer2);
        Button appear = (Button) findViewById(R.id.next_question);
        Button answer1 = (Button) findViewById(R.id.answer1);
        Button answer2 = (Button) findViewById(R.id.answer3);
        Button answer3 = (Button) findViewById(R.id.answer4);
        appear.setVisibility(View.INVISIBLE);
        changeTextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                changingText.setText(words);
                appear.setVisibility(View.VISIBLE);
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                changeTextButton.setEnabled(false);
            }
        });
    }
    /**
     * if wrong answer is pressed.
     */
    public void displayWrong3() {
        final String words = "U dumb!";
        final TextView changingText = (TextView) findViewById(R.id.wrongAnswer);
        Button changeTextButton = (Button) findViewById(R.id.answer4);
        Button appear = (Button) findViewById(R.id.next_question);
        appear.setVisibility(View.INVISIBLE);
        Button answer1 = (Button) findViewById(R.id.answer1);
        Button answer2 = (Button) findViewById(R.id.answer3);
        Button answer3 = (Button) findViewById(R.id.answer2);
        changeTextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                changingText.setText(words);
                appear.setVisibility(View.VISIBLE);
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                changeTextButton.setEnabled(false);
            }
        });
    }

    /**
     * if the right answer if pressed.
     */
    public void displayRight() {
        final String words = "Correct!";
        final TextView changingText = (TextView) findViewById(R.id.wrongAnswer);
        Button changeTextButton = (Button) findViewById(R.id.answer3);
        Button appear = (Button) findViewById(R.id.next_question);
        Button answer1 = (Button) findViewById(R.id.answer1);
        Button answer2 = (Button) findViewById(R.id.answer4);
        Button answer3 = (Button) findViewById(R.id.answer2);
        appear.setVisibility(View.INVISIBLE);
        changeTextButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                changingText.setText(words);
                appear.setVisibility(View.VISIBLE);
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                changeTextButton.setEnabled(false);
            }
        });
    }

}
