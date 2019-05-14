package com.example.zero.finalexam;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    ImageButton add;
    RecyclerView tripList;
    Button exit;
    Button viewMap;
    GridLayoutManager lm;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        add=findViewById(R.id.addTrip);
        tripList=findViewById(R.id.tripsList);
        exit=findViewById(R.id.exitBtn);
        viewMap=findViewById(R.id.travelMap);
        lm=new GridLayoutManager(this,2);
        tripList.setLayoutManager(lm);
        TripAdapter tp= new TripAdapter(readFromDB(),this);
        tripList.setAdapter(tp);
        tp.notifyDataSetChanged();


        setHandlers();

    }

    public void setHandlers(){

        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this,newAlbum.class);
                startActivity(i);


            }
        });
        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
                finishAffinity();
            }
        });
        viewMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(i);

            }
        });

    }
    public ArrayList<Trip> readFromDB(){
        ArrayList<Trip> list= new ArrayList<Trip>();


        return list;

    }
}
