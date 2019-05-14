package com.example.zero.finalexam;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import com.google.android.gms.maps.model.LatLng;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONStringer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class newAlbum extends AppCompatActivity  implements DatePickerDialog.OnDateSetListener {
    EditText name;
    EditText date;
    ImageButton datePicker;
    ImageButton home;
    ImageButton add;
    ImageButton search;
    RecyclerView picList;
    EditText exitPlace;
    Button save;
    Button cancel;
    String TAG= "newAlbum: ";
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("Trips");
    ArrayList <Uri> pics;
    LatLng myLoc;
    LinearLayoutManager lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_album);
        name= findViewById(R.id.editName);
        date= findViewById(R.id.editDate);
        datePicker=findViewById(R.id.datePicker);
        home=findViewById(R.id.home);
        add=findViewById(R.id.addImage);
        search= findViewById(R.id.searchBtn);
        picList= findViewById(R.id.picList);
        exitPlace= findViewById(R.id.exitPlace);
        save=findViewById(R.id.save);
        cancel=findViewById(R.id.cancel);
        pics=new ArrayList<Uri>();
        myLoc=new LatLng(0,0);
        AlbumAdapter ab= new AlbumAdapter(pics);
        lm=new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        picList.setLayoutManager(lm);
        picList.setAdapter(ab);
        ab.notifyDataSetChanged();
        setHandlers();
    }

    private void setHandlers() {
        datePicker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dp=new DatePickerDialog(view.getContext(),newAlbum.this,2017,12,11);
                dp.show();
            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



            }
        });
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!exitPlace.getText().toString().equals("")){
                    String response=connect(exitPlace.getText().toString());
                    try {
                        JSONObject root=new JSONObject(response);
                        double lon=root.getDouble("lon");
                       double lat= root.getDouble("lat");
                       myLoc=new LatLng(lat,lon);
                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
                    Log.d(TAG, "onClick: "+response);
                    if (response!=null){
                        Toast.makeText(newAlbum.this,"Location Found!",Toast.LENGTH_SHORT).show();

                    }
                    else{
                        Toast.makeText(newAlbum.this,"Location Not Found!",Toast.LENGTH_SHORT).show();
                    }

                }
                else{
                    Toast.makeText(newAlbum.this,"Empty search box",Toast.LENGTH_SHORT).show();
                }
            }
        });
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(newAlbum.this,MainActivity.class);
                startActivity(i);
                finish();

            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(newAlbum.this,MainActivity.class);
                startActivity(i);
                finish();
            }
        });
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (readyToSave()){
                    saveToDB();
                    Intent i = new Intent(newAlbum.this,MainActivity.class);
                    startActivity(i);
                    finish();

                }
                else{
                    Toast.makeText(newAlbum.this,"Empty field!",Toast.LENGTH_LONG).show();
                }

            }
        });
    }

    private void saveToDB() {
        String tempId=myRef.push().getKey();
        Trip t= new Trip();
        t.setDate(date.getText().toString());
        t.setName(name.getText().toString());
        t.setPictures(pics);
        t.setLatLng(myLoc);
        myRef.child(tempId).setValue(t);

    }

    @Override
    public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
        String year = new Integer(i).toString();
        String m = new Integer(i1).toString();
        String d = new Integer(i2).toString();
        date.setText(year+"/"+m+"/"+d);
    }

    public static String connect(String address){
        StringBuilder stringBuilder = new StringBuilder();
        HttpURLConnection connection = null;
        BufferedReader reader = null;
        String result = null;
        try {
            URL url = new URL("https://maps.googleapis.com/maps/api/geocode/json?address="+address+"&key="+"key");
            connection = (HttpURLConnection) url.openConnection();
            connection.connect();
            if (connection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                reader = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line = "";
                while ((line = reader.readLine()) != null) {
                    stringBuilder.append(line);
                }
                result = stringBuilder.toString();
            }
        }
            //Handle the exceptions
         catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //Close open connections and reader
            if (connection != null) {
                connection.disconnect();
            }
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return result;

    }
    /*
     name= findViewById(R.id.editName);
        date= findViewById(R.id.editDate);
        datePicker=findViewById(R.id.datePicker);
        home=findViewById(R.id.home);
        add=findViewById(R.id.addImage);
        search= findViewById(R.id.searchBtn);
        picList= findViewById(R.id.picList);
        exitPlace= findViewById(R.id.exitPlace);
        save=findViewById(R.id.save);
        cancel=findViewById(R.id.cancel);
     */
    public boolean readyToSave(){
        if(name.getText().toString().equals("") || date.getText().toString().equals("")|| exitPlace.getText().toString().equals("")){
            return false;
        }
        else {
            return true;
        }
    }
}
