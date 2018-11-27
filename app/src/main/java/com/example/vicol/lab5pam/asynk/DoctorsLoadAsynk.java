package com.example.vicol.lab5pam.asynk;

import android.app.Activity;
import android.os.AsyncTask;

import com.example.vicol.lab5pam.URLS;
import com.example.vicol.lab5pam.domain.Doctor;
import com.example.vicol.lab5pam.utils.CustomLoadingDialog;
import com.example.vicol.lab5pam.utils.JsonUtils;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

/**
 *
 *  in this class we make GET request for DOC list
 */
public class DoctorsLoadAsynk extends AsyncTask<String,Void,ArrayList<Doctor>> {
    private String token;
    private int httpCode = 0;
    private DoctorLoadResponse delegate = null;
    private OkHttpClient client = new OkHttpClient();
    private CustomLoadingDialog loadingDialog;
    private Activity activity;


    public DoctorsLoadAsynk(DoctorLoadResponse delegate, Activity activity) {
        this.delegate = delegate;
        loadingDialog = new CustomLoadingDialog(activity);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        loadingDialog.showDialog();
    }

    @Override
    protected ArrayList<Doctor> doInBackground(String... strs)  {
        //get toke from arguments
        token = strs[0];
        ArrayList<Doctor> doctors = new ArrayList<>();

        //create request and add headers
        Request request = new Request.Builder()
                .url(URLS.DOC_LIST)
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .addHeader("token",token)
                .build();

        try {
            Response response =  client.newCall(request).execute();
            String jsonData = response.body().string();
            JSONArray JsonArray = new JSONArray(jsonData);

            if(response.code() == 200){
                doctors = JsonUtils.getDoctorsFromJson(JsonArray);
                this.httpCode = 200 ;
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return doctors;
    }

    @Override
    protected void onPostExecute(ArrayList<Doctor> doctors) {
        loadingDialog.hideDialog();
        switch (httpCode){
            case 200 : {
                //implemented in Doctor fragment
                delegate.onSuccesLoad(doctors);
            }break;
            default:{
                //implemented in Doctor Fragment
                delegate.onErrorLoad();
            }break;
        }
    }
}
