package com.example.vicol.lab5pam.asynk;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.vicol.lab5pam.URLS;
import com.example.vicol.lab5pam.domain.User;
import com.example.vicol.lab5pam.utils.JsonUtils;
import com.example.vicol.lab5pam.utils.TokenUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class UserProfileReuqest extends AsyncTask<Void,Void,User> {

    private int httpCode = 0;
    private ProfileLoadResponse  delegate = null;
    private OkHttpClient client;
    private Activity activity;

    public UserProfileReuqest(ProfileLoadResponse delegate) {
        this.client = new OkHttpClient();
        this.delegate = delegate;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected User doInBackground(Void... voids) {
        User u = null;
        Request request = new Request.Builder()
                .url(URLS.USER_DATA)
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .addHeader("token",TokenUtils.getToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            httpCode =response.code();
            String jsonData = response.body().string();
            if(httpCode == 200){

                JSONObject Jobject = new JSONObject(jsonData);
                u = JsonUtils.getUserFromJson(Jobject);
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return  u;
    }

    @Override
    protected void onPostExecute(User user) {
        switch (httpCode){
            case 200:{
                delegate.onSucces(user);
            }break;
            default:{
                delegate.onError();
            }break;
        }
    }
}
