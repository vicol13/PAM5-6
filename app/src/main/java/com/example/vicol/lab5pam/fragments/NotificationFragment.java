package com.example.vicol.lab5pam.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.utils.FragmentTransactionUtils;

public class NotificationFragment extends Fragment {
    String TOKEN;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification,null);

        FragmentTransactionUtils.turnAllIconsGray(getActivity());
        ImageView fragmentIcon = getActivity().findViewById(R.id.notificationImageView);
        fragmentIcon.setImageResource(R.drawable.green_notif);

        return view;
    }



}
