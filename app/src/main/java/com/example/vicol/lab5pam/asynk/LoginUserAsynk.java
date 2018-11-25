package com.example.vicol.lab5pam.asynk;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.vicol.lab5pam.URLS;
import com.example.vicol.lab5pam.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
//TODO change name
public class LoginUserAsynk extends AsyncTask<Void, Void, HashMap<String, String>> {

    private String email, pass;

    //delegate is is implemented when LoginUser class is created
    //in constructor
    private AsyncLoginResponse delegate = null;

    private OkHttpClient client = new OkHttpClient();
    private static MediaType json = MediaType.parse("application/json; charset=utf-8");

    public LoginUserAsynk(String email, String pass, AsyncLoginResponse delegate) {
        this.email = email;
        this.pass = pass;
        this.delegate = delegate;
    }


    @Override
    protected HashMap<String, String> doInBackground(Void... voids) {
        Response response;
        HashMap<String, String> map = new HashMap<>();
        /**
         *  set default http code
         */
        map.put("code", "404");

        //convert email and password to json
        String jsonStr = JsonUtils.userLoginJson(email, pass);
        //set up body
        RequestBody body = RequestBody.create(json, jsonStr);
        Request request = new Request.Builder()
                .url(URLS.USER_AUTH)
                .post(body)
                .build();

        try {
          response = client.newCall(request).execute();
            if (response.code() == 200) {
                map.put("code", String.valueOf(response.code()));
                String jsonData = response.body().string();
                JSONObject Jobject = new JSONObject(jsonData);

                /**
                 *      extract from json token
                 * */
                String token = Jobject.getString("Message");
                map.put("token",token);

            }

        } catch (IOException e) {
//            e.printStackTrace();
//            map.put("code", String.valueOf(r/esponse.code()));
        } catch (JSONException e) {
            e.printStackTrace();

        }
        /**
         *   if something wrong happen http code default
         *   is 404
         *
         *   //TODO procces errors
         */

        return map;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();

    }


    //called when doInBackGround is donne
    @Override
    protected void onPostExecute(HashMap<String, String> map) {
        int code = Integer.parseInt(
                map.get("code")
        );

        switch (code) {
            case 200: {
                String token = map.get("token");
                delegate.procesFinishSucces(token);
            }
            break;
            default: {
                delegate.procesFinishError(code);
            }
            break;
        }


    }
}
