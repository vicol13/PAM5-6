package com.example.vicol.lab5pam.activityes;

import android.annotation.TargetApi;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.EditText;

import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.asynk.RegisterUser;
import com.example.vicol.lab5pam.domain.User;
import com.example.vicol.lab5pam.utils.InputUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class Register extends AppCompatActivity {
    private static final int OPEN_DOCUMENT_CODE = 2;
    EditText    login,
                pass,
                name,
                birthDay,
                phone,
                adress,
                email;

    ArrayList<String> array;
    String base64;
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
            base64
        );
            //create new thrad for post requst with data
        new RegisterUser(this,this).execute(usr);
        }
    }


    public void getImage(View view) {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("image/*");
        startActivityForResult(intent, OPEN_DOCUMENT_CODE);
    }

    @TargetApi(Build.VERSION_CODES.O)
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent resultData) {
        if (requestCode == OPEN_DOCUMENT_CODE && resultCode == RESULT_OK) {
            if (resultData != null) {
                // this is the image selected by the user
                Uri imageUri = resultData.getData();
                try {
                    //set BitMap from Uri
                    Bitmap bitmap = MediaStore.Images.Media.getBitmap(this.getContentResolver(),imageUri);
                    //Compress bit map
                    bitmap = Bitmap.createScaledBitmap(bitmap,500,500,false);
                   //create Byteoutput for converti bitmap int ByteArray
                    ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                    bitmap.compress(Bitmap.CompressFormat.JPEG, 20, byteArrayOutputStream);
                    //set Byte Array
                    byte[] byteArray = byteArrayOutputStream.toByteArray();
                    //get Base64 string via Android standart base 64 library
                   base64 = Base64.encodeToString(byteArray, Base64.NO_WRAP);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public Bitmap getResizedBitmap(Bitmap image, int maxSize) {
        int width = image.getWidth();
        int height = image.getHeight();

        float bitmapRatio = (float)width / (float) height;
        if (bitmapRatio > 1) {
            width = maxSize;
            height = (int) (width / bitmapRatio);
        } else {
            height = maxSize;
            width = (int) (height * bitmapRatio);
        }
        return Bitmap.createScaledBitmap(image, width, height, true);
    }
}
