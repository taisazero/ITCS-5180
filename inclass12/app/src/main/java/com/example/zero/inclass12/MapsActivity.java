package com.example.zero.inclass12;

import android.Manifest;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Color;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMapLongClickListener;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.Polyline;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.ArrayList;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    private static final int MY_PERMISSIONS_REQUEST_FINE_LOCATION = 111;
    LocationManager mLoc;
    LocationListener mListner;

    boolean start = true;
    boolean end = false;
    SupportMapFragment mapFragment;
    ArrayList <LatLng>locations=new ArrayList<LatLng>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        // Obtain the SupportMapFragment and get notified when the map is ready to be used.

        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);

        mapFragment.getMapAsync(this);
        mLoc = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        //mMap.setOnMapLongClickListener(this.);
         //   mMap.addPolyline()



        if (ContextCompat.checkSelfPermission(this,
                Manifest.permission.ACCESS_FINE_LOCATION)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(
                    this, // Activity
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION},
                    MY_PERMISSIONS_REQUEST_FINE_LOCATION);

        }


    }




    @Override
    protected void onResume() {
        super.onResume();
        if (!mLoc.isProviderEnabled(LocationManager.GPS_PROVIDER)) {
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("Enable GPS");
            builder.setMessage("Would you like to enable your GPS?");
            builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                    Toast.makeText(MapsActivity.this,"Tap Back when done setting up your GPS",Toast.LENGTH_LONG).show();
                    startActivity(intent);
                }
            });

            builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialogInterface, int i) {
                    dialogInterface.cancel();
                    Toast.makeText(MapsActivity.this, "This app can't do much :(", Toast.LENGTH_LONG).show();
                    finish();
                }
            });
            AlertDialog c = builder.create();
            c.show();
        } else {

        }
    }

    // Get permission result
    @Override
    public void onRequestPermissionsResult(int requestCode,
                                           String permissions[], int[] grantResults) {
        switch (requestCode) {
            case MY_PERMISSIONS_REQUEST_FINE_LOCATION: {
                if (grantResults.length > 0
                        && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    // permission was granted


                } else {
                    // permission was denied
                    Toast.makeText(this, "This app can't do much :(", Toast.LENGTH_LONG).show();
                }
                return;
            }
        }
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;


        //mMap.setOnMapLongClickListener();
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        mMap.setMyLocationEnabled(true);
        mMap.setMapType(1);
        mMap.setOnMapLongClickListener(new OnMapLongClickListener() {
            @Override
            public void onMapLongClick(LatLng latLng) {
                if(end) {

                    end=false;
                    Log.d("testLongClick", "I am here!!!!");
                    Toast.makeText(mapFragment.getActivity(), "Stop Location Tracking", Toast.LENGTH_LONG).show();
                    if (locations.size() > 1) {
                        drawLines();
                        mMap.addMarker(new MarkerOptions().position(locations.get(locations.size() - 1)).title("End"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(locations.get(locations.size() - 1)));
                        LatLngBounds.Builder builder=new LatLngBounds.Builder();
                        for (LatLng l : locations){
                            builder.include(l);
                            Log.d("Looper",l.latitude+","+l.longitude);
                        }
                        LatLngBounds bounds = builder.build();


                        mMap.animateCamera(CameraUpdateFactory.newLatLngBounds(bounds,100));
                        start=true;


                    }
                }
                else{
                    Toast.makeText(mapFragment.getActivity(), "Start Location Tracking", Toast.LENGTH_LONG).show();
                    mMap.clear();
                    locations.clear();

                    end=true;
                    mListner = new LocationListener() {
                        @Override
                        public void onLocationChanged(Location location) {
                            if(start && end==true) {
                                Log.d("demo", location.getLatitude() + "," + location.getLongitude());
                                LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.addMarker(new MarkerOptions().position(current).title("Start"));
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
                                start=false;



                                locations.add(current);
                            }


                            else if(end &&!start){
                                LatLng current = new LatLng(location.getLatitude(), location.getLongitude());
                                mMap.moveCamera(CameraUpdateFactory.newLatLng(current));
                                locations.add(current);

                            }

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


                    if (ActivityCompat.checkSelfPermission(mapFragment.getActivity(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(mapFragment.getActivity(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        // TODO: Consider calling
                        //    ActivityCompat#requestPermissions
                        // here to request the missing permissions, and then overriding
                        //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
                        //                                          int[] grantResults)
                        // to handle the case where the user grants the permission. See the documentation
                        // for ActivityCompat#requestPermissions for more details.
                        return;
                    }
                    mLoc.requestLocationUpdates(LocationManager.GPS_PROVIDER, 100, 5, mListner);
                }
                }



        });




    }
    public void drawLines(){

            Polyline line = mMap.addPolyline(new PolylineOptions().addAll(locations)
            .color(Color.BLUE)
            .width(5));

    }

    @Override
    public void onBackPressed() {
        recreate();
    }
}
