package com.example.vicol.lab5pam.activityes;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.activityes.Home;
import com.example.vicol.lab5pam.asynk.AsyncLoginResponse;
import com.example.vicol.lab5pam.asynk.LoginUserAsynk;
import com.example.vicol.lab5pam.utils.DialogUtils;

public class Login extends AppCompatActivity {

    private EditText email,pass;
    private String emailStr,passStr;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.mailEditText_login);
        pass = findViewById(R.id.passWordEditText_login);


    }



    public void onNextClick(View view){
        this.emailStr = email.getText().toString();
        this.passStr = pass.getText().toString();
        final  Context ctx = this;

        //LoginUserThread
        new LoginUserAsynk(emailStr, passStr, new AsyncLoginResponse() {
            @Override
            public void procesFinishSucces(String token) {
                //after LoginUser thread is executed with succes
                //this lisnter is executed
                //here we send token to main page /*Home page */
                Intent intent = new Intent(ctx,Home.class);
                intent.putExtra("token",token);
                startActivity(intent);
            }

            @Override
            public void procesFinishError(Integer httpCode) {
                //if LoginUser thred throw error this listner is executed
                // and print httpCode
                DialogUtils.getCustomDialog("Error","Login undone you get " + httpCode,"empty",ctx);
            }
        }).execute();
    }
}
