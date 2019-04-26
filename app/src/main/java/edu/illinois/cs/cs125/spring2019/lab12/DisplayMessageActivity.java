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
     * comment.
     */
    private int counter = 0;
    /**
     * comment.
     */
    private final int max = 50;
    /**
     * comment.
     */
    private final int constant = 0;
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
     * return array of questions.
     * @param json
     * comment.
     * @return
     * comment.
     */
    public String[] getQuestion(final String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject parsed = jsonParser.parse(json).getAsJsonObject();
        String[] returnArray = new String[max];
        JsonArray multipleQuestions = parsed.getAsJsonArray("question");
        for (JsonElement question : multipleQuestions) {
            for (int i = 0; i < max; i++) {
                JsonObject element = question.getAsJsonObject();
                returnArray[i] = element.get("question").getAsString();
            }
        }
        return returnArray;
    }

    /**
     * return array of right answers.
     * @param json
     * comment.
     * @return
     * comment.
     */
    public String[] getAnswer(final String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject parsed = jsonParser.parse(json).getAsJsonObject();
        String[] returnArray = new String[max];
        JsonArray multipleQuestions = parsed.getAsJsonArray("question");
        for (JsonElement question : multipleQuestions) {
            for (int i = 0; i < max; i++) {
                JsonObject element = question.getAsJsonObject();
                returnArray[i] = element.get("correct_answer").getAsString();
            }
        }
        return returnArray;
    }

    /**
     * return array of wrong answers.
     * @param json
     * comment.
     * @return
     * comment.
     */
    public String[] getWrongAnswers(final String json) {
        JsonParser jsonParser = new JsonParser();
        JsonObject parsed = jsonParser.parse(json).getAsJsonObject();
        JsonArray multipleQuestions = parsed.getAsJsonArray("question");
        String[] returnArray = new String[max];
        for (JsonElement question : multipleQuestions) {
            JsonObject element = question.getAsJsonObject();
            JsonArray wrongAnswersJson = element.getAsJsonArray("incorrect_answers").getAsJsonArray();
            String[] wrongAnswers = new String[wrongAnswersJson.size()];
            for (int i = 0; i < max; i++) {
                String wrongAnswer = wrongAnswersJson.get(i).getAsString();
                wrongAnswers[i] = wrongAnswer;
                returnArray = wrongAnswers;
            }
        }
        return returnArray;
    }

    /**
     * comment.
     * @param savedInstanceState
     */
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
        final String words = "Wrong answer!";
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
        final String words = "Wrong answer!";
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
        String b = "answer";
        String[] a = getQuestion(b);
        String[] c = getWrongAnswers(b);
        String[] d = getAnswer(b);
        TextView generateQ = (TextView) findViewById(R.id.serious);
        generateQ.setText(a[counter]);
        final TextView changingText = (TextView) findViewById(R.id.wrongAnswer);
        Button changeTextButton = (Button) findViewById(R.id.answer3);
        Button appear = (Button) findViewById(R.id.next_question);
        Button answer1 = (Button) findViewById(R.id.answer1);
        Button answer2 = (Button) findViewById(R.id.answer4);
        Button answer3 = (Button) findViewById(R.id.answer2);
        appear.setVisibility(View.INVISIBLE);
        answer1.setText(c[counter]);
        answer2.setText(c[counter + 1]);
        answer3.setText(c[counter + 2]);
        changeTextButton.setText(d[counter]);
        counter++;
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
