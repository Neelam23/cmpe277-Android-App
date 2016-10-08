package com.affectiva.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Neelam on 10/7/2016.
 */
public class LoadStoryDetails  extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loadstorydetails);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String title = getIntent().getStringExtra("title");
            Log.d("neelam title Received:", title);
            }

    }







}
