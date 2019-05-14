package com.example.zero.geotest;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class MainActivity extends AppCompatActivity {

    LocationManager mLoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mLoc=(LocationManager)getSystemService(Context.LOCATION_SERVICE);

    }

    @Override
    protected void onResume() {
        super.onResume();
        if(!mLoc.isProviderEnabled(LocationManager.GPS_PROVIDER)){
            AlertDialog.Builder builder= new AlertDialog.Builder(this);
            builder.setTitle("Enable GPS");
            builder.setMessage("Would you like to enable your GPS?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent (Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    startActivity(intent);
                }
            });
            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                }
            });
           AlertDialog c= builder.create();
           c.show();
        }
        else{
            final LocationListener mLocListenr= new LocationListener() {
                @Override
                public void onLocationChanged(Location location) {
                    Log.d("demo",location.getLatitude()+","+location.getLongitude());
                }

                @Override
                public void onStatusChanged(String s, int i, Bundle bundle) {

                }

                @Override
                public void onProviderEnabled(String s) {

                }

                @Override
                public void onProviderDisabled(String s) {

                }
            };
            mLoc.requestLocationUpdates(LocationManager.GPS_PROVIDER,100,10,mLocListenr);
        }
    }
}
