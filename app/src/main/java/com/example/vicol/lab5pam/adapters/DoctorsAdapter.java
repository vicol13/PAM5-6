package com.example.vicol.lab5pam.adapters;

import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.constraint.ConstraintLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.util.Base64;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.vicol.lab5pam.R;
import com.example.vicol.lab5pam.domain.Doctor;
import com.example.vicol.lab5pam.fragments.DoctorInfoFragment;
import com.example.vicol.lab5pam.utils.FragmentTransactionUtils;

import java.util.ArrayList;

public class DoctorsAdapter  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private ArrayList<Doctor> docList;
    private Context context;

    public DoctorsAdapter(ArrayList<Doctor> docList, Context context) {
        this.docList = docList;
        this.context = context;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        View viewHolder = inflater.inflate(R.layout.doctor_bloc_view,viewGroup,false);
        RecyclerView.ViewHolder holder = new DoctorViewHolder(viewHolder);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int position) {
        final  int pos = position;
        final Doctor doc = docList.get(position);
        DoctorViewHolder view = (DoctorViewHolder)viewHolder;


        view.fullName.setText(doc.getFullName());
        view.speciality.setText(doc.getSpecs());
        view.docRate.setText(doc.getStars());
        view.docLocation.setText(doc.getAddress());
        //decode Base64 img

         int rating = (int) Float.parseFloat(doc.getStars());

        for (int i = 4 ; i >= rating;i--){
            view.stars[i].setVisibility(View.INVISIBLE);
        }

        byte[]bytes = Base64.decode(docList.get(position).getPhoto(),Base64.DEFAULT);
        view.photo.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));

        /**
                when user press on doctor he will be redirected to page with this doctor
                i setted here an clickListner because here i already have object Doc mapped

                inside click listener i change displaying fragment send to that fragment
                send in fragment Doctor pojo or doctor id for request from server

         */
        view.mainContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putParcelable("doc",doc);
                DoctorInfoFragment fragment = new DoctorInfoFragment();
                fragment.setArguments(bundle);

                AppCompatActivity activity = (AppCompatActivity) view.getContext();
                FragmentManager manager = activity.getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                transaction.replace(R.id.fragmentContainer,fragment);
                transaction.addToBackStack(null);
                transaction.commit();
            }
        });
    }



    @Override
    public int getItemCount() {
        return docList.size();
    }

    public static class DoctorViewHolder extends RecyclerView.ViewHolder{
        //TODO decode image 64
        ConstraintLayout mainContainer;
        TextView fullName,speciality,docRate,docLocation;
        ImageView photo;
        ImageView[] stars = new ImageView[5];
        public DoctorViewHolder(@NonNull View itemView) {
            super(itemView);
            mainContainer = itemView.findViewById(R.id.doctorBloc_adapter);
            fullName = itemView.findViewById(R.id.doctorName_adapter);
            speciality = itemView.findViewById(R.id.doctorSpeciality_adapter);
            photo = itemView.findViewById(R.id.doctorPhoto_adapter);
            docRate = itemView.findViewById(R.id.doctorRate_adapter);
            docLocation = itemView.findViewById(R.id.locationTextView_adapter);
            stars[0] = itemView.findViewById(R.id.star1_adapter);
            stars[1] = itemView.findViewById(R.id.star2_adapter);
            stars[2] = itemView.findViewById(R.id.star3_adapter);
            stars[3] = itemView.findViewById(R.id.star4_adapter);
            stars[4] = itemView.findViewById(R.id.star5_adapter);
        }
    }
}
