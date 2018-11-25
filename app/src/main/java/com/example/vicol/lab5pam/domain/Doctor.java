package com.example.vicol.lab5pam.domain;

import android.os.Parcel;
import android.os.Parcelable;

import java.io.Serializable;

public class Doctor implements Parcelable {
    private String DocId;
    private String FullName;
    private String Specs;
    private String Address;
    private String About;
    private String Stars;
    private String Photo;

    public Doctor(String docId, String fullName, String specs, String address, String about, String stars, String photo) {
        DocId = docId;
        FullName = fullName;
        Specs = specs;
        Address = address;
        About = about;
        Stars = stars;
        Photo = photo;
    }

    protected Doctor(Parcel in) {
        DocId = in.readString();
        FullName = in.readString();
        Specs = in.readString();
        Address = in.readString();
        About = in.readString();
        Stars = in.readString();
        Photo = in.readString();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(DocId);
        dest.writeString(FullName);
        dest.writeString(Specs);
        dest.writeString(Address);
        dest.writeString(About);
        dest.writeString(Stars);
        dest.writeString(Photo);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<Doctor> CREATOR = new Creator<Doctor>() {
        @Override
        public Doctor createFromParcel(Parcel in) {
            return new Doctor(in);
        }

        @Override
        public Doctor[] newArray(int size) {
            return new Doctor[size];
        }
    };

    public String getDocId() {
        return DocId;
    }

    public void setDocId(String docId) {
        DocId = docId;
    }

    public String getFullName() {
        return FullName;
    }

    public void setFullName(String fullName) {
        FullName = fullName;
    }

    public String getSpecs() {
        return Specs;
    }

    public void setSpecs(String specs) {
        Specs = specs;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getAbout() {
        return About;
    }

    public void setAbout(String about) {
        About = about;
    }

    public String getStars() {
        return Stars;
    }

    public void setStars(String stars) {
        Stars = stars;
    }

    public String getPhoto() {
        return Photo;
    }

    public void setPhoto(String photo) {
        Photo = photo;
    }



}
