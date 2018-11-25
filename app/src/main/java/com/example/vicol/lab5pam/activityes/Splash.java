package com.example.vicol.lab5pam.activityes;

import android.content.Intent;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.vicol.lab5pam.R;

public class Splash extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(Splash.this,Welcome.class);
                startActivity(intent);
            }
        }, 100);
}

    @Override
    protected void onResume() {
        super.onResume();
        finish();
    }
}


