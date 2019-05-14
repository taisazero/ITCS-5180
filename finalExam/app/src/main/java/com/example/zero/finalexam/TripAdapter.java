package com.example.zero.finalexam;

/**
 * Created by Zero on 12/11/2017.
 */

import android.content.Intent;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Zero on 12/11/2017.
 */

public class TripAdapter extends RecyclerView.Adapter<TripAdapter.ViewHolder> {
    static ArrayList<Trip> listOfTrips;
    MainActivity m;
    public TripAdapter(ArrayList<Trip>list,MainActivity m){
        this.listOfTrips=list;
        this.m=m;
    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.trip_item,parent,false);
        TripAdapter.ViewHolder viewHolder= new TripAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Trip t= listOfTrips.get(position);
    holder.tripName.setText(t.getName());
    holder.pic.setImageURI(t.getPictures().get(0));
        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOfTrips.remove(t);
                TripAdapter.this.notifyDataSetChanged();

            }
        });
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ViewAlbum.t=t;
                Intent i = new Intent (m,ViewAlbum.class);
                m.startActivity(i);


            }
        });
    }

    @Override
    public int getItemCount() {
        return listOfTrips.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tripName;
        ImageView pic;
        ImageButton remove;

        public ViewHolder(View itemView) {
            super(itemView);
            tripName=itemView.findViewById(R.id.tripName);
            remove=itemView.findViewById(R.id.deleteTrip);
            pic=itemView.findViewById(R.id.tripPic);

        }
    }
}
