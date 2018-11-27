package com.example.vicol.lab5pam.asynk;

import android.app.Activity;
import android.os.AsyncTask;
import android.widget.EditText;

import com.example.vicol.lab5pam.URLS;
import com.example.vicol.lab5pam.domain.ConsultationResponse;
import com.example.vicol.lab5pam.utils.JsonUtils;
import com.example.vicol.lab5pam.utils.TokenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.HashMap;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class ConsultationRequest extends AsyncTask<Void,Void,ConsultationResponse> {

    private OkHttpClient client;
    private int httpCode = 0 ;
    private ConsultationRequestResponseListner delegate = null;
    private static MediaType app_json = MediaType.parse("application/json; charset=utf-8");
    private Activity activity;
    String json;

    public ConsultationRequest( String json,ConsultationRequestResponseListner delegate) {
        this.delegate = delegate;
        this.json = json;
        client = new OkHttpClient();
    }



    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }



    @Override
    protected ConsultationResponse doInBackground(Void... voids) {
        ConsultationResponse responsePojo = null;
        Response response;
        RequestBody requestBody = RequestBody.create(app_json,json);
        Request request = new Request.Builder()
                .addHeader("Content-Type","application/json")
                .addHeader("token",TokenUtils.getToken())
                .url(URLS.CONSULTATION_REQUEST)
                .post(requestBody)
                .build();

        try {
            response = client.newCall(request).execute();
            httpCode = response.code();
            if(httpCode == 200){
                String json = response.body().string();
                JSONObject jsonObject = new JSONObject(json);
                responsePojo = JsonUtils.getConsultationResponse(jsonObject);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return responsePojo;
    }

    @Override
    protected void onPostExecute(ConsultationResponse response) {
       switch (httpCode){
           case 200 : {
               delegate.onSucces(response);
           }break;
           default:{
               delegate.onError(httpCode);
           }break;
       }
    }
}
