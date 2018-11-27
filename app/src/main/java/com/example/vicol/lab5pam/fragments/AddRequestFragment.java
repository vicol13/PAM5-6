package com.example.vicol.lab5pam.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Toast;

import com.example.vicol.lab5pam.ConsultationCache;
import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.asynk.ConsultationRequest;
import com.example.vicol.lab5pam.asynk.ConsultationRequestResponseListner;
import com.example.vicol.lab5pam.domain.ConsultationResponse;
import com.example.vicol.lab5pam.utils.JsonUtils;


public class AddRequestFragment extends Fragment {

    EditText name, deasease, location, description;
    ConstraintLayout btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, null);

        name = view.findViewById(R.id.nameEditText_fHome);
        deasease = view.findViewById(R.id.deseassEditText_fHome);
        location = view.findViewById(R.id.locationEditText_fHome);
        description = view.findViewById(R.id.descriptionEditText_fHome);
        btn = view.findViewById(R.id.requestBtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String json = JsonUtils.getConsultationJson(
                        name.getText().toString(),
                        deasease.getText().toString(),
                        location.getText().toString(),
                        description.getText().toString()
                );
                new ConsultationRequest(json, new ConsultationRequestResponseListner() {
                    @Override
                    public void onSucces(ConsultationResponse response) {
                        Toast.makeText(getActivity(),"Check notifications for consultation details",Toast.LENGTH_LONG).show();
                        ConsultationCache.setResponse(response);

                        NotificationFragment  notificationFragment = new NotificationFragment();
                        FragmentManager  manager = getActivity().getSupportFragmentManager();
                        FragmentTransaction transaction = manager.beginTransaction();
                        transaction.replace(R.id.fragmentContainer,notificationFragment);
                        transaction.addToBackStack(null);
                        transaction.commit();
                    }

                    @Override
                    public void onError(int httpCode) {
//                        Toast.makeText(getActivity(),"Something wrong",Toast.LENGTH_LONG).show();
                    }
                }).execute();
            }
        });

        return view;
    }


    public void requestConsultationBtn(View view) {

    }
}
