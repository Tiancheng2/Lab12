package edu.illinois.cs.cs125.spring2019.lab12;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Main class for our UI design lab.
 */

public final class MainActivity extends AppCompatActivity {
    /**
     * bob.
     */
    private static int counter = 0;
    /**
     * comment.
     */
    private TextView textViewResult;
    /**
     * comment.
     */
    private final int max = 3;
    /**
     * comment.
     */
    private final int hi = 2;
    /**
     * comment.
     */
    private final int constant = 0;
    /**
     * comment.
     */
    private final int testsize = 10;

    /**
     * Request queue for our API requests.
     */

    private final int size = 10;
    /**
     * comment.
     */
    private String[] incorrect = new String[size];
    /**
     * comment.
     */
    private String[] question = new String[size];
    /**
     * comment.
     */
    private String[] answer = new String[size];

    /**
     * comment.
     */
    private static final String TAG = "Lab12:Main";
    /**
     * Request queue for our API requests.
     */
    private static RequestQueue requestQueue;

    /**
     * Run when this activity comes to the foreground.
     *
     * @param savedInstanceState unused
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // Set up the queue for our API requests
        setContentView(R.layout.activity_main);
        requestQueue = Volley.newRequestQueue(this);
        Button button = (Button) findViewById(R.id.start);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                jsonParse(counter);
                counter++;
            }
        });
    }

    /**
     * comment.
     * @param x
     * parse json given by api.
     * used the api given by https://opentdb.com/api.php?amount=50, converted into easy to read text from myjson.com.
     * comment.
     */

    public void jsonParse(final int x) {
        String url = "https://api.myjson.com/bins/bnpgw";
        setContentView(R.layout.content_display_message);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(final JSONObject response) {
                        try {
                            JSONArray jsonArray = response.getJSONArray("results");
                            JSONObject results = jsonArray.getJSONObject(x);
                            question[x] = results.getString("question");
                            answer[x] = results.getString("correct_answer");
                            //System.out.println(results);
                            final TextView changing1Text = (TextView) findViewById(R.id.riddle);
                            final Button answer3 = findViewById(R.id.answer3);
                            answer3.setText(answer[x]);
                            changing1Text.setText(question[x]);
                            incorrect[x] = results.getString("incorrect_answers");
                            String a = incorrect[x].replace("[", "");
                            String b = a.replace("]", "");
                            String c = b.replace("\"", "");
                            String[] newIncorrect = c.split(",");
                            final Button answer1 = findViewById(R.id.answer1);
                            final Button answer2 = findViewById(R.id.answer2);
                            final Button answer4 = findViewById(R.id.answer4);
                            answer1.setText(newIncorrect[0]);
                            answer2.setText(newIncorrect[1]);
                            answer4.setText(newIncorrect[2]);
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
        final String words = "correct!";
        final String wow = "wrong!";
        final TextView changingText = (TextView) findViewById(R.id.wrongAnswer);
        Button correct = (Button) findViewById(R.id.answer3);
        Button appear = (Button) findViewById(R.id.next_question);
        Button answer1 = (Button) findViewById(R.id.answer2);
        Button answer2 = (Button) findViewById(R.id.answer1);
        Button answer3 = (Button) findViewById(R.id.answer4);
        appear.setVisibility(View.INVISIBLE);
        answer1.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                changingText.setText(wow);
                appear.setVisibility(View.VISIBLE);
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                correct.setEnabled(false);
            }
        });
        answer2.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                changingText.setText(wow);
                appear.setVisibility(View.VISIBLE);
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                correct.setEnabled(false);
            }
        });
        answer3.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                changingText.setText(wow);
                appear.setVisibility(View.VISIBLE);
                answer1.setEnabled(false);
                answer2.setEnabled(false);
                answer3.setEnabled(false);
                correct.setEnabled(false);
            }
        });
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
        appear.setOnClickListener(new View.OnClickListener() {
            public void onClick(final View v) {
                counter++;
                jsonParse(counter);
            }
            /**
             * Handle the response from our IP geolocation API.
             *
             * @param response response from our IP geolocation API.
            void apiCallDone(final JSONObject response) {
            try {
            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            Log.i(TAG, response.get("hostname").toString());
            } catch (JSONException ignored) {
            }
            }
             */
        });
    }
}
