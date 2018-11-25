package com.example.vicol.lab5pam.utils;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class DialogUtils {
    public static AlertDialog getCustomDialog(String title, String msg, String button, Context context){
        return new AlertDialog.Builder(context)
                //set icon
                .setIcon(android.R.drawable.ic_dialog_alert)
                //set title
                .setTitle(title)
                //set message
                .setMessage(msg)
                //set positive button
                .show();
    }
}
