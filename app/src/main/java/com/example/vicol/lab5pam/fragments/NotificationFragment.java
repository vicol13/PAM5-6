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
import android.widget.TextView;
import android.widget.Toast;

import com.example.vicol.lab5pam.ConsultationCache;
import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.asynk.GetDoctorInfo;
import com.example.vicol.lab5pam.utils.FragmentTransactionUtils;

public class NotificationFragment extends Fragment {
    String TOKEN;
    TextView name,deases,description,location;
    TextView docName,docSpeciality,docRate;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_notification,null);
        name = view.findViewById(R.id.NameView_notification);
        deases = view.findViewById(R.id.deseaseView_deases);
        description = view.findViewById(R.id.descriptionView_notification);
        location = view.findViewById(R.id.locationView_notification);

        docName = view.findViewById(R.id.doctorName_notification);
        docSpeciality = view.findViewById(R.id.doctorSpeciality_notification);
        docRate = view.findViewById(R.id.doctorRate_notification);
        FragmentTransactionUtils.turnAllIconsGray(getActivity());
        ImageView fragmentIcon = getActivity().findViewById(R.id.notificationImageView);
        fragmentIcon.setImageResource(R.drawable.green_notif);

        if(ConsultationCache.getResponse() == null){
            Toast.makeText(getActivity(),"cache is  empty ",Toast.LENGTH_LONG).show();



        }else{
            name.setText(ConsultationCache.getResponse().getName());
            deases.setText(ConsultationCache.getResponse().getDisease());
            description.setText(ConsultationCache.getResponse().getDescription());
        Toast.makeText(getActivity(),"cache is  not emty ",Toast.LENGTH_LONG).show();
        new GetDoctorInfo(view).execute(ConsultationCache.getResponse().getDocId());
        }
        return view;
    }



}
