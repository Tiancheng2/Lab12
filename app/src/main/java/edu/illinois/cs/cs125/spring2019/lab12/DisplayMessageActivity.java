package edu.illinois.cs.cs125.spring2019.lab12;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Questions screen.
 */
public class DisplayMessageActivity extends AppCompatActivity {
    /**
     * counter.
     */
    private TextView textViewResult;
    /**
     * max questions.
     */
    private final int testsize = 10;
    /**
     * comment.
     */
    private final int max = 3;
    /**
     * Request queue for our API requests.
     */

    private final int size = 50;
    /**
     * comment.
     */
    private String[][] incorrect = new String[size][max];
    /**
     * comment.
     */
    private String[] question = new String[size];
    /**
     * comment.
     */
    private String[] answer = new String[size];

    /**
     * all questions size.
     */
    private static RequestQueue requestQueue;

    /**
     * comment.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_message);

        display();
    }
    /**
     * @param i for number
     * parse json given by api.
     * comment.
     */
/*
    public void jsonParse(final int i) {
        String url = "https://opentdb.com/api.php?amount=50";
        //textViewResult = findViewById(R.id.question);
        final TextView changing1Text = (TextView) findViewById(R.id.riddle);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            JSONObject results = jsonArray.getJSONObject(i);
                            //System.out.println(results);
                            question[i] = results.getString("question");
                            answer[i] = results.getString("correct_answer");
                            final TextView changing1Text = (TextView) findViewById(R.id.riddle);
                            System.out.println(changing1Text);
                            changing1Text.setText(question[i]);

                            //incorrect[i] = results.get("incorrect_answers");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
    }*/
    /**
     * if wrong answer is pressed.
     */
    public void display() {
        final String words = "Wrong answer!";
        final TextView changingText = (TextView) findViewById(R.id.wrongAnswer);
        Button correct = (Button) findViewById(R.id.answer1);
        Button appear = (Button) findViewById(R.id.next_question);
        Button answer1 = (Button) findViewById(R.id.answer2);
        Button answer2 = (Button) findViewById(R.id.answer3);
        Button answer3 = (Button) findViewById(R.id.answer4);
        appear.setVisibility(View.INVISIBLE);
        correct.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                changingText.setText(words);
                appear.setVisibility(View.VISIBLE);
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                correct.setEnabled(false);
            }
        });
    }

}
