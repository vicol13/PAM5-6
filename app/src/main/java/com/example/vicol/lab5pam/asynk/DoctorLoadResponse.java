package com.example.vicol.lab5pam.asynk;

import com.example.vicol.lab5pam.domain.Doctor;

import java.util.ArrayList;

public interface DoctorLoadResponse {
    void onSuccesLoad(ArrayList<Doctor> doctors);
    void onErrorLoad();
}
