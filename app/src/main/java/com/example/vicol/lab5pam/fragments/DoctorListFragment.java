package com.example.vicol.lab5pam.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.adapters.DoctorsAdapter;
import com.example.vicol.lab5pam.asynk.DoctorLoadResponse;
import com.example.vicol.lab5pam.asynk.DoctorsLoadAsynk;
import com.example.vicol.lab5pam.domain.Doctor;
import com.example.vicol.lab5pam.utils.FragmentTransactionUtils;

import java.util.ArrayList;

/**
 *  this fragment is responsible for displaying list with doctors
 *  and when user will pres on the of doctors he will be redirected to page of doctor
 *
 *  steps :
 *  1.Create instance of view from layout
 *  2.Set Up recycler view
 *  3.Get Token from Bundle
 *  4.Call the function wich will execute in another thread Http get request
 *      ... inside thread to parse json and convert it into Array of doctors
 *  5.1 if thread is executed if success it will go in onLoadSuccess listner
 *          to set up adapter for recycler view
 *          and set array with doctors for displaying
 *
 *  5.2 if thread is executed with errors
 *          show a dialog with that error
 *
 *
 *  //TODO inside adapter redirect to page with doc wi detail information
 *
 */
public class DoctorListFragment extends Fragment {
    private RecyclerView recyclerView ;
    private String TOKEN;
    private DoctorsAdapter adapter;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //create instance of view from out Layout
        View view = inflater.inflate(R.layout.fragment_doclist,null);

        FragmentTransactionUtils.turnAllIconsGray(getActivity());
        ImageView fragmentIcon = getActivity().findViewById(R.id.homeImage);
        fragmentIcon.setImageResource(R.drawable.green_home);
        //set the id for recycle view
        recyclerView = view.findViewById(R.id.doctorRecView);
        //get tokken
        TOKEN  = getArguments().getString("token");
        setDocList();
        return view;
    }

    private void setDocList(){
        new DoctorsLoadAsynk(new DoctorLoadResponse() {
            @Override
            public void onSuccesLoad(ArrayList<Doctor> doctors) {
            adapter = new DoctorsAdapter(doctors,getContext());
            recyclerView.setAdapter(adapter);
            recyclerView.setItemAnimator(new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
            }

            @Override
            public void onErrorLoad() {

            }
        },getActivity()).execute(TOKEN);
    }
}
