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
    private final int testsize = 10;

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
            public void onClick(View v) {
                jsonParse(0);
                openActivity2();
            }
        });
    }

    /**
     * comment.
     * comment.
     */
    public void openActivity2() {
        Intent newPage = new Intent(this, DisplayMessageActivity.class);
        startActivity(newPage);
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
     * @param i for number
     * parse json given by api.
     * comment.
     */

    public void jsonParse(final int i) {
        String url = "https://opentdb.com/api.php?amount=50";
        setContentView(R.layout.content_display_message);
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
                            changing1Text.setText(question[i]);
                            //incorrect[i] = results.get("incorrect_answers");
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(final VolleyError error)
            {
                error.printStackTrace();
            }
        });
        requestQueue.add(request);
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
}
