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
import com.example.vicol.lab5pam.domain.Doctor;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

/***
 *  fragment with doctor details
 *
 *  here we recive from doctor adapter DOC pojo and set it up
 *
 */

public class DoctorInfoFragment extends Fragment  {

/*
    public static final String MAPVIEW_BUNDLE_KEY = "MapViewBundleKey";
    private static final LatLng PERTH = new LatLng(-31.952854, 115.857342);
    private static final LatLng SYDNEY = new LatLng(-33.87365, 151.20689);
    private static final LatLng BRISBANE = new LatLng(-27.47093, 153.0235);
*/

    TextView name , speciality ,location,rate,description;
    ImageView[]stars;
    ImageView photo,mapView;
    Doctor doc;
    //MapView mapView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        stars = new ImageView[5];
        Bundle bundle = this.getArguments();
        doc = (Doctor)bundle.getParcelable("doc");
        View view = inflater.inflate(R.layout.fragment_doc,null);

        name = (TextView) view.findViewById(R.id.doctorName);
        speciality = view.findViewById(R.id.doctorSpeciality);
        description = view.findViewById(R.id.doctorDescription);
        rate = view.findViewById(R.id.doctorRate);
        location = view.findViewById(R.id.doctorLocation);
        photo = view.findViewById(R.id.doctorPhoto);


        stars[0] = view.findViewById(R.id.star1);
        stars[1] = view.findViewById(R.id.star2);
        stars[2] = view.findViewById(R.id.star3);
        stars[3] = view.findViewById(R.id.star4);
        stars[4] = view.findViewById(R.id.star5);


        int rating = (int) Float.parseFloat(doc.getStars());

        for (int i = 4 ; i >= rating;i--){
            stars[i].setVisibility(View.INVISIBLE);
        }

        byte[]bytes = Base64.decode(doc.getPhoto(),Base64.DEFAULT);
        photo.setImageBitmap(BitmapFactory.decodeByteArray(bytes,0,bytes.length));
/*

        mapView = view.findViewById(R.id.mapView);
        mapView.getMapAsync(this);
        Bundle mapViewBundle = null;
        if (savedInstanceState != null) {
            mapViewBundle = savedInstanceState.getBundle(MAPVIEW_BUNDLE_KEY);
        }
*/

        //TODO request btn


        name.setText(doc.getFullName());
        speciality.setText(doc.getSpecs());
        rate.setText(doc.getStars());
        description.setText(doc.getAbout());
        location.setText(doc.getAddress());

        return view;
    }

/*
    @Override
    public void onMapLoaded() {
        Marker marker;
        GoogleMap google
    }
*/
/*

    @Override
    public void onMapReady(GoogleMap googleMap) {
        googleMap.addMarker(new MarkerOptions()
                    .position(SYDNEY)
                    .title("sydney"));

    }
*/






}
