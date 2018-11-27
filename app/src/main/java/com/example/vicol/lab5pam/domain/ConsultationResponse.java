package com.example.vicol.lab5pam.domain;

public class ConsultationResponse {
    String ConsId;
    String Name;
    String Disease;
    String Address;
    String Description;
    String DocId;
    String IsConfirmed;

    public ConsultationResponse(String consId, String name, String disease, String address, String description, String docId, String isConfirmed) {
        ConsId = consId;
        Name = name;
        Disease = disease;
        Address = address;
        Description = description;
        DocId = docId;
        IsConfirmed = isConfirmed;
    }

    public String getConsId() {
        return ConsId;
    }

    public void setConsId(String consId) {
        ConsId = consId;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getDisease() {
        return Disease;
    }

    public void setDisease(String disease) {
        Disease = disease;
    }

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getDescription() {
        return Description;
    }

    public void setDescription(String description) {
        Description = description;
    }

    public String getDocId() {
        return DocId;
    }

    public void setDocId(String docId) {
        DocId = docId;
    }

    public String getIsConfirmed() {
        return IsConfirmed;
    }

    public void setIsConfirmed(String isConfirmed) {
        IsConfirmed = isConfirmed;
    }
}
