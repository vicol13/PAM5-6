package com.example.vicol.lab5pam.asynk;

import com.example.vicol.lab5pam.domain.ConsultationResponse;

import java.util.HashMap;

public interface ConsultationRequestResponseListner {
    void onSucces(ConsultationResponse response);
    void onError(int httpCode);
}
