package edu.illinois.cs.cs125.spring2019.lab12;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

/**
 * Questions screen.
 */
public class DisplayMessageActivity extends AppCompatActivity {
    /**
     * bob.
     */
    private int counter = 0;
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
     * comment.
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(final Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_display_message);
        requestQueue = Volley.newRequestQueue(this);
        MainActivity a = new MainActivity();
        counter++;
        a.jsonParse(counter);
    }
}
