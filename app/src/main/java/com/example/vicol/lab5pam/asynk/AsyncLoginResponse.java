package com.example.vicol.lab5pam.asynk;

import android.content.Intent;

public interface AsyncLoginResponse {
    //for procesing response for thread in activity class
    // without this we have to precess response in onPostExecute
    void procesFinishSucces(String token);
    void procesFinishError(Integer httpCode);
}

/*
public interface DocLoadRespons{

}*/
