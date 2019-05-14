package com.example.zero.finalexam;

/**
 * Created by Zero on 12/11/2017.
 */

import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by Zero on 12/11/2017.
 */





/**
 * Created by Zero on 12/11/2017.
 */

public class AlbumAdapter extends RecyclerView.Adapter<AlbumAdapter.ViewHolder> {
    static ArrayList<Uri> listOfTrips;

    public AlbumAdapter(ArrayList<Uri>list){
        this.listOfTrips=list;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.pic_item,parent,false);
        AlbumAdapter.ViewHolder viewHolder= new AlbumAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Uri t= listOfTrips.get(position);

        holder.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listOfTrips.remove(t);
                AlbumAdapter.this.notifyDataSetChanged();

            }
        });
      holder.pic.setImageURI(t);
    }

    @Override
    public int getItemCount() {
        return listOfTrips.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;
        ImageButton remove;

        public ViewHolder(View itemView) {
            super(itemView);

            remove=itemView.findViewById(R.id.listDelete);
            pic=itemView.findViewById(R.id.listImage);

        }
    }
}
