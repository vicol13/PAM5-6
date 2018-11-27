package com.example.vicol.lab5pam.asynk;

import com.example.vicol.lab5pam.domain.User;

public interface ProfileLoadResponse {
    void onSucces(User user);
    void onError();
}
