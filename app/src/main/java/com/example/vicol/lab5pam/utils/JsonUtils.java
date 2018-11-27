package com.example.vicol.lab5pam.utils;

import com.example.vicol.lab5pam.domain.ConsultationResponse;
import com.example.vicol.lab5pam.domain.Doctor;
import com.example.vicol.lab5pam.domain.User;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

public class JsonUtils {

    public static String userToJson(User user) {
        return "{" +
                "\"FullName\"" + ":" + "\"" + user.getFullName() + "\"" +
                ",\"Birthday\"" + ":" + "\"" + user.getBirthday() + "\"" +
                ",\"Email\"" + ":" + "\"" + user.getEmail() + "\"" +
                ",\"Phone\"" + ":" + "\"" + user.getPhone() + "\"" +
                ",\"Address\"" + ":" + "\"" + user.getAddress() + "\"" +
                ",\"Username\"" + ":" + "\"" + user.getUsername() + "\"" +
                ",\"Password\"" + ":" + "\"" + user.getPassword() + "\"" +
                ",\"Base64Photo\"" + ":" + "\"" + user.getBase64Photo() + "\"" +
                '}';
    }

    public static String userLoginJson(String email, String pass) {
        return "{" +
                "\"Email\"" + ":" + "\"" + email + "\"" +
                ",\"Password\"" + ":" + "\"" + pass + "\"" +
                "}";
    }

    public static ArrayList<Doctor> getDoctorsFromJson(JSONArray jsonArray) throws JSONException {
        ArrayList<Doctor> list = new ArrayList<>(jsonArray.length());

        for (int i = 0; i < jsonArray.length(); i++) {
            JSONObject obj = jsonArray.getJSONObject(i);
            //get data from json element and instance doc with that data
            Doctor doc = new Doctor(
                    obj.getString("DocId"),
                    obj.getString("FullName"),
                    obj.getString("Specs"),
                    obj.getString("Address"),
                    obj.getString("About"),
                    obj.getString("Stars"),
                    obj.getString("Photo")
            );
            list.add(doc);
        }
        return list;
    }


    public static User getUserFromJson(JSONArray array) throws JSONException {
        JSONObject jObject = array.getJSONObject(0);
        System.out.println("soze " + array.length());
        return new User(
                jObject.getString("FullName"),
                jObject.getString("Birthday"),
                jObject.getString("Email"),
                jObject.getString("Phone"),
                jObject.getString("Address"),
                jObject.getString("Username"),
                jObject.getString("Base64Photo")

        );

    }

    public static User getUserFromJson(JSONObject obj) throws JSONException {
        return new User(
                obj.getString("FullName"),
                obj.getString("Birthday"),
                obj.getString("Email"),
                obj.getString("Phone"),
                obj.getString("Address"),
                obj.getString("Username"),
                obj.getString("Base64Photo")

        );
    }

    public static String getConsultationJson(String name , String desease, String location , String Description){
            return "{"+
                    "\"Name\""+":" + "\""+name+"\""+
                    ",\"Disease\""+":" + "\""+desease+"\""+
                    ",\"Address\""+":" + "\""+location+"\""+
                    ",\"Description\""+":" + "\""+Description+"\""+"}";

//        return null;
    }
    public static ConsultationResponse getConsultationResponse(JSONObject object) throws JSONException {
        return  new ConsultationResponse(
                object.getString("ConsId"),
                object.getString("Name"),
                object.getString("Disease"),
                object.getString("Address"),
                object.getString("Description"),
                object.getString("DocId"),
                object.getString("IsConfirmed")
        );
    }

    public static  Doctor getDoctorFromJson(JSONObject obj ) throws JSONException {
        return  new Doctor(
                obj.getString("DocId"),
                obj.getString("FullName"),
                obj.getString("Specs"),
                obj.getString("Address"),
                obj.getString("About"),
                obj.getString("Stars"),
                obj.getString("Photo")

        );
    }
}
