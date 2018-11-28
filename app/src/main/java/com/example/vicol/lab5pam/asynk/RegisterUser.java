package com.example.vicol.lab5pam.asynk;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Handler;

import com.example.vicol.lab5pam.activityes.Login;
import com.example.vicol.lab5pam.URLS;
import com.example.vicol.lab5pam.activityes.Welcome;
import com.example.vicol.lab5pam.domain.User;
import com.example.vicol.lab5pam.utils.DialogUtils;
import com.example.vicol.lab5pam.utils.JsonUtils;


import java.io.IOException;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class RegisterUser extends AsyncTask<User, Integer, Integer> {

    private User user;
    private OkHttpClient client = new OkHttpClient();
    private static MediaType json = MediaType.parse("application/json; charset=utf-8");
    private Context ctx;
    private Activity activity;
    ProgressDialog progDailog;

    public RegisterUser(Context ctx, Activity activity) {
        this.ctx = ctx;
        this.activity = activity;
    }

    @Override
    protected Integer doInBackground(User... users) {
        this.user = users[0];
        int code = 404; //http code

        String formateJson = JsonUtils.userToJson(user);
        RequestBody body = RequestBody.create(json, formateJson);
        Request request = new Request.Builder()
                .url(URLS.USER_REGISTER)
                .post(body)
                .build();
        try {
            Response response = client.newCall(request).execute();
            code = response.code();

        } catch (IOException e) {
//            e.printStackTrace();
            return code;
        }

        return code;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        //Loading spinner
        progDailog = new ProgressDialog(ctx);
        progDailog.setMessage("Loading...");
        progDailog.setIndeterminate(false);
        progDailog.setProgressStyle(ProgressDialog.STYLE_SPINNER);
        progDailog.setCancelable(true);
        progDailog.show();
    }

    @Override
    protected void onPostExecute(Integer code) {
        super.onPostExecute(code);
        progDailog.dismiss();
        switch (code) {
            /**
             *  if code HTTP code is 201 show to user confirmation for few seconds and redirect him to login page
             */
            case 201: {

                final AlertDialog conf = DialogUtils.getCustomDialog(
                        "Succes",
                        "your registration confirmed",
                        "Close",
                        ctx);

                conf.show();

                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (conf.isShowing()) {
                            conf.dismiss();
                        }

                        Intent intent = new Intent(ctx, Login.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        activity.startActivity(intent);
                    }
                }, 3000);
            }
            break;

            /**
             * if HTTP code is not 201 show to user dialog that somenthing wrong and redirect him to welcome page
             *
             * */
            default: {
                final AlertDialog alertDialog = DialogUtils.getCustomDialog("Error",
                        "Sorry something wrong please try again later",
                        "wrong",
                        ctx);
                alertDialog.show();


                new Handler().postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        if (alertDialog.isShowing()) {
                            alertDialog.dismiss();
                        }

                        Intent intent = new Intent(ctx, Welcome.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                        activity.startActivity(intent);
                    }
                }, 5000);
            }
            break;
        }
    }
}
