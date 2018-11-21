package com.example.vicol.lab5pam;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.vicol.lab5pam.fragments.DoctorInfoFragment;
import com.example.vicol.lab5pam.fragments.HomeFragment;
import com.example.vicol.lab5pam.fragments.NotificationFragment;
import com.example.vicol.lab5pam.utils.FragmentTransaction;

public abstract class  Home extends AppCompatActivity {

    ImageView currentFragmentIcon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


//        currentFragmentIcon = findViewById(R.id.homeImage);
//        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
    }
    protected abstract int getLayResouce();

    public void openHomeFragment(View view) {
        FragmentTransaction.turnAllIconsGray(this);
        currentFragmentIcon = findViewById(R.id.homeImage);
        currentFragmentIcon.setImageResource(R.drawable.green_home);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new HomeFragment()).commit();
    }

    public void openNotificationFragment(View view) {

        FragmentTransaction.turnAllIconsGray(this);
        currentFragmentIcon = findViewById(R.id.notificationImageView);
        currentFragmentIcon.setImageResource(R.drawable.green_notif);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new NotificationFragment()).commit();

    }

    public void openScheuldeFragment(View view) {

    }

    public void openProfileFragment(View view) {

    }




    public void redirectToDoctorInfoFragment(View v){

        Fragment fragment = new DoctorInfoFragment();
        FragmentManager fragmentManager = getSupportFragmentManager();
        android.support.v4.app.FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragmentContainer,fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

}
