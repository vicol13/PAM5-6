package com.example.vicol.lab5pam.utils;

import android.widget.EditText;

import java.util.ArrayList;

public class InputUtils {



    public static ArrayList<String> getInputArray(EditText ... editTexts){
        ArrayList<String> arr  = new ArrayList<>();
        for(EditText edt : editTexts){
            arr.add(edt.getText().toString());
        }
        return arr;
    }


    public static boolean validateInputs(ArrayList<String> editTexts){
        for (String str : editTexts){
            if( str == null && str.isEmpty()){
                return false;
            }
        }
        return true;
    }
}
