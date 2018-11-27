package com.example.vicol.lab5pam.fragments;

import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.asynk.ProfileLoadResponse;
import com.example.vicol.lab5pam.asynk.UserProfileReuqest;
import com.example.vicol.lab5pam.domain.User;
import com.example.vicol.lab5pam.utils.FragmentTransactionUtils;

public class UserProfileFragment extends Fragment {
    ImageView photo;
    TextView name,username,phone,email,adress;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        FragmentTransactionUtils.turnAllIconsGray(getActivity());
        ImageView fragmentIcon = getActivity().findViewById(R.id.personImageView);
        fragmentIcon.setImageResource(R.drawable.green_pers);

        View view = inflater.inflate(R.layout.user_profile,null);
        photo = view.findViewById(R.id.userPhoto);
        name = view.findViewById(R.id.fulnameTxtView_user);
        username = view.findViewById(R.id.usernameTxtView_user);
        phone = view.findViewById(R.id.phoneTxtView_user);
        email = view.findViewById(R.id.emailTxtView_user);
        adress = view.findViewById(R.id.adressTxtView_user);

        new UserProfileReuqest(new ProfileLoadResponse() {
            @Override
            public void onSucces(User user) {
                name.setText(user.getFullName());
                username.setText(user.getUsername());
                phone.setText(user.getPhone());
                email.setText(user.getEmail());
                adress.setText(user.getAddress());
                byte[]bytes = Base64.decode(user.getBase64Photo(),Base64.DEFAULT);
                photo.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
            }

            @Override
            public void onError() {

            }
        }).execute();
        return view;
    }
}
