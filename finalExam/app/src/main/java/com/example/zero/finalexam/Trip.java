package com.example.zero.finalexam;

import android.net.Uri;

import com.google.android.gms.maps.model.LatLng;

import java.util.ArrayList;

/**
 * Created by Zero on 12/11/2017.
 */

public class Trip {
    private String date;
    private String name;
    ArrayList<Uri> pictures;
    private String id;
    private LatLng latLng;

    public LatLng getLatLng() {
        return latLng;
    }

    public void setLatLng(LatLng latLng) {
        this.latLng = latLng;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Trip(String date, String name, ArrayList<Uri> pictures) {
        this.date = date;
        this.name = name;
        this.pictures = pictures;
    }

    public Trip() {
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public ArrayList<Uri> getPictures() {
        return pictures;
    }

    public void setPictures(ArrayList<Uri> pictures) {
        this.pictures = pictures;
    }
}
