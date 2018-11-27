package com.example.vicol.lab5pam.activityes;

import android.content.Intent;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.fragments.AddRequestFragment;
import com.example.vicol.lab5pam.fragments.DoctorInfoFragment;
import com.example.vicol.lab5pam.fragments.DoctorListFragment;

import com.example.vicol.lab5pam.fragments.NotificationFragment;
import com.example.vicol.lab5pam.fragments.UserProfileFragment;
import com.example.vicol.lab5pam.test.DocActivity;
import com.example.vicol.lab5pam.utils.FragmentTransactionUtils;
import com.example.vicol.lab5pam.utils.TokenUtils;


/**
 *      view home is view with bottom bar
 *      and fragment container
 *
 *      Bundle - kind of intent where we can put some not heavy weight data and send it to activity or fragment
 *
 */
public  class  Home extends AppCompatActivity {

    ImageView currentFragmentIcon;
    String TOKEN ;
    Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        //get the TOKE from success login action
        TOKEN = getIntent().getStringExtra("token");
        TokenUtils.setToken(TOKEN);

        /**
         *  Token is send to fragment because from that fragment we will
         *  make an Http Get request for list with doctors and for this request
         *  we need to set in the request header token
         *
         *      List with doctors is seted as main page
         */
        //instancie bundle for sending token to fragment
         bundle = new Bundle();
        bundle.putString("token",TOKEN); //put toke in bundle
        DoctorListFragment fragment = new DoctorListFragment(); // create instance of Doctor List
        fragment.setArguments(bundle); //set argument for Doctor list



        FragmentManager manager = getSupportFragmentManager();
        FragmentTransaction transaction = manager.beginTransaction();
        transaction.replace(R.id.fragmentContainer,fragment);
        transaction.addToBackStack(null); // add fragment to backstack
        //when press back is redirected to fragment
        transaction.commit();

    }


    public void openHomeFragment(View view) {
//        FragmentTransactionUtils.turnAllIconsGray(this);
        bundle = new Bundle();
        bundle.putString("token",TOKEN); //put toke in bundle
        DoctorListFragment fragment = new DoctorListFragment(); // create instance of Doctor List
        fragment.setArguments(bundle); //set argument for Doctor list
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }

    public void openNotificationFragment(View view) {
 

//        FragmentTransaction.turnAllIconsGray(this);
        currentFragmentIcon = findViewById(R.id.notificationImageView);
        currentFragmentIcon.setImageResource(R.drawable.green_notif);
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,new NotificationFragment()).commit();

    }

    public void openAddNotificationFragment(View view){
        bundle = new Bundle();
        bundle.putString("token",TOKEN); //put toke in bundle
        AddRequestFragment fragment = new AddRequestFragment(); // create instance of Doctor List
        fragment.setArguments(bundle); //set argument for Doctor list
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
    }

    public void openScheuldeFragment(View view) {

    }

    public void openProfileFragment(View view) {
        UserProfileFragment fragment = new UserProfileFragment();
        getSupportFragmentManager().beginTransaction().replace(R.id.fragmentContainer,fragment).commit();
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
