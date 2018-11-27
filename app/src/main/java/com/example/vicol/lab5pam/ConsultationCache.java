package com.example.vicol.lab5pam;

import com.example.vicol.lab5pam.asynk.ConsultationRequest;

import com.example.vicol.lab5pam.domain.ConsultationResponse;

import java.util.ArrayList;

public class ConsultationCache {
    private static ArrayList<ConsultationResponse> responses = new ArrayList<>();
    private static ConsultationResponse response;
    private static Boolean isEmpty(){
        return  responses.isEmpty();
    }

    public static void setResponse(ConsultationResponse responsea) {
        response = responsea;
    }

    public static ConsultationResponse getResponse() {
        return response;
    }

    public static Boolean checkCache(){
        return response == null;
    }
}
