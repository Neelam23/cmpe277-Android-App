package com.affectiva.app;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Neelam on 10/7/2016.
 */
public class Loading extends Activity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
           String jsonString = getIntent().getStringExtra("jsonObjectString");
            try{

                JSONObject jsonObj = new JSONObject(jsonString);
                Log.d("neelam json", jsonObj.toString());

            }catch (JSONException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }

    }
}
