package com.example.vicol.lab5pam.utils;

import android.app.Activity;
import android.media.Image;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.example.vicol.lab5pam.R;

public class FragmentTransactionUtils  {

    /**

            this function will change color to all icons into gray

     */
    public static void turnAllIconsGray(Activity activity){
        ImageView  home = activity.findViewById(R.id.homeImage);
        ImageView notification = activity.findViewById(R.id.notificationImageView);
        ImageView scheulde = activity.findViewById(R.id.scheuldeImageView);
        ImageView profile = activity.findViewById(R.id.personImageView);

        home.setImageResource(R.drawable.gray_home);
        notification.setImageResource(R.drawable.gray_notif);
        scheulde.setImageResource(R.drawable.gray_calendar);
        profile.setImageResource(R.drawable.gray_person);
    }
}
