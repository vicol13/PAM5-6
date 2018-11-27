package com.example.vicol.lab5pam.asynk;

import android.os.AsyncTask;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.URLS;
import com.example.vicol.lab5pam.domain.Doctor;
import com.example.vicol.lab5pam.utils.JsonUtils;
import com.example.vicol.lab5pam.utils.TokenUtils;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GetDoctorInfo extends AsyncTask<String,Integer,Doctor> {
    int httpCode = 0 ;
    View view;
    OkHttpClient client;

    public GetDoctorInfo(View view) {
        this.view = view;
        client = new OkHttpClient();
    }

    @Override
    protected Doctor doInBackground(String... strings) {
        String id = strings[0];
        String responseJson;
        Doctor doctor = null;
        Request request = new Request.Builder()
                .url(URLS.DOC_INFO+id)
                .addHeader("Content-Type","application/x-www-form-urlencoded")
                .addHeader("token",TokenUtils.getToken())
                .build();

        try {
            Response response = client.newCall(request).execute();
            httpCode = response.code();
            if(httpCode == 200){
                        responseJson = response.body().string();
                        doctor = JsonUtils.getDoctorFromJson(new JSONObject(responseJson));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return  doctor;
    }

    @Override
    protected void onPostExecute(Doctor doctor) {
    if(httpCode==200){
        TextView name,speciality,ratting,location;
        name = (TextView) view.findViewById(R.id.doctorName_notification);
        speciality = (TextView) view.findViewById(R.id.doctorSpeciality_notification);
        ratting = (TextView) view.findViewById(R.id.doctorRate_notification);
//        location = (TextView) view.findViewById(R.id.locationView_notification);

        name.setText(doctor.getFullName());
        speciality.setText(doctor.getSpecs());
        ratting.setText(doctor.getStars());
//        location.setText(doctor.getAddress());
    }
    }
}
