package com.example.vicol.lab5pam;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends Home {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
    }

    @Override
    protected int getLayResouce() {
        return R.layout.activity_home;
    }


}
