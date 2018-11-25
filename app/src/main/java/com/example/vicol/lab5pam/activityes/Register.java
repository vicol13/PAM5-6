package com.example.vicol.lab5pam.activityes;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.asynk.RegisterUser;
import com.example.vicol.lab5pam.domain.User;
import com.example.vicol.lab5pam.utils.InputUtils;

import java.util.ArrayList;
import java.util.HashMap;

public class Register extends AppCompatActivity {
    EditText    login,
                pass,
                name,
                birthDay,
                phone,
                adress,
                email;

    ArrayList<String> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        this.login = findViewById(R.id.loginInput);
        this.pass  = findViewById(R.id.passwordInput);
        this.name = findViewById(R.id.nameInput);
        this.birthDay = findViewById(R.id.birthdayInput);
        this.phone = findViewById(R.id.phoneInput);
        this.adress = findViewById(R.id.adressInput);
        this.email = findViewById(R.id.emailInput);



    }

    public void saveUser(View view){
        array = InputUtils.getInputArray(login,pass,name,birthDay,phone,adress,email);
        if(InputUtils.validateInputs(array)) {

            //maping user from inputs
        User usr = new User(
            name.getText().toString(),
            birthDay.getText().toString(),
            email.getText().toString(),
            phone.getText().toString(),
            adress.getText().toString(),
            login.getText().toString(),
            pass.getText().toString(),
            "empty"

        );
            //create new thrad for post requst with data
        new RegisterUser(this,this).execute(usr);
        }
    }




}
