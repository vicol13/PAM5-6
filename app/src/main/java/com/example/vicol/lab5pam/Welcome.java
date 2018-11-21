package com.example.vicol.lab5pam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class Welcome extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    public void singUpActivity(View view) {
        Intent intent = new Intent(Welcome.this,Register.class);
        startActivity(intent);
    }

    public void loginActivity(View view) {
        Intent intent = new Intent(Welcome.this,Login.class);
        startActivity(intent);
    }

    public void urjent(View view) {
        Intent intent = new Intent(Welcome.this,Home.class);
        startActivity(intent);
    }
}
