package edu.illinois.cs.cs125.spring2019.lab12;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.google.gson.JsonObject;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;


/**
 * Main class for our UI design lab.
 */

public final class MainActivity extends AppCompatActivity {
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
    private final int constant = 0;
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
     * parse json given by api.
     * comment.
     */

    public void jsonParse() {
        String url = "https://opentdb.com/api.php?amount=50";
        textViewResult = findViewById(R.id.serious);
        Button button = findViewById(R.id.answer1);
        JsonObjectRequest request = new JsonObjectRequest(Request.Method.GET, url, null,
                new Response.Listener<JSONObject>() {
                @Override
                public void onResponse(final JSONObject response) {
                    try {
                        JSONArray jsonArray = response.getJSONArray("results");
                        for (int i = 0; i < jsonArray.length(); i++) {
                            JSONObject results = jsonArray.getJSONObject(i);
                            String question = results.getString("question");
                            String answer = results.getString("correct_answer");
                            String[] incorrectAnswer = new String[max];
                            incorrectAnswer[i] = results.getString("incorrect_answers");
                        }
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
    }
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
        requestQueue = Volley.newRequestQueue(this);

        setContentView(R.layout.activity_main);
        /*
        Toolbar myToolbar = (Toolbar) findViewById(R.id.my_toolbar);
        setSupportActionBar(myToolbar);
        final TextView address = (TextView) findViewById(R.id.serious);
        final String ipAddress = "128.2.42.95";
        startAPICall(ipAddress);*/

    }
    /**
     * comment.
     * @param view
     * comment.
     */
    public void sendMessage(final View view) {
        Intent newPage = new Intent(this, DisplayMessageActivity.class);
        startActivity(newPage);
    }

    /**
     * Run when this activity is no longer visible.
     */
    @Override
    protected void onPause() {
        super.onPause();
    }


    /**
     * Make a call to the IP geolocation API.
     *
     * @param ipAddress IP address to look up
     */
    void startAPICall(final String ipAddress) {
        try {
            JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                    Request.Method.GET,
                    "https://opentdb.com/api.php?amount=50",
                    null,
                    new Response.Listener<JSONObject>() {
                        @Override
                        public void onResponse(final JSONObject response) {
                            apiCallDone(response);
                        }
                    }, new Response.ErrorListener() {
                        @Override
                public void onErrorResponse(final VolleyError error) {
                            Log.e(TAG, error.toString());
                        }
                    });
            jsonObjectRequest.setShouldCache(false);
            requestQueue.add(jsonObjectRequest);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handle the response from our IP geolocation API.
     *
     * @param response response from our IP geolocation API.
     */
    void apiCallDone(final JSONObject response) {
        try {
            Log.d(TAG, response.toString(2));
            // Example of how to pull a field off the returned JSON object
            Log.i(TAG, response.get("hostname").toString());
        } catch (JSONException ignored) {
        }
    }

    /**
     * @param json gets Json Information
     * @return prettyJson
     */
    public String getJson(final java.lang.String json) {
        JsonParser parser = new JsonParser();
        JsonObject result = parser.parse(json).getAsJsonObject();
        Gson pretty = new GsonBuilder().setPrettyPrinting().create();
        String prettyJson = pretty.toJson(json);
        return prettyJson;
    }
}
