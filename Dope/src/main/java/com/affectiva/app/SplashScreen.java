package com.affectiva.app;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.EditText;
import android.app.Activity;

/**
 * Created by Neelam on 10/2/2016.
 */
public class SplashScreen extends Activity {

    public static int spalsh_Screen_Delay = 4000;
    EditText input;
    int MY_PERMISSIONS_REQUEST_CAMERA;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splashscreen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, spalsh_Screen_Delay);
    }
}