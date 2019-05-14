package com.example.zero.finalexam;

/**
 * Created by Zero on 12/11/2017.
 */

import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.ImageView;

import java.util.ArrayList;




public class PicAdapter extends RecyclerView.Adapter<PicAdapter.ViewHolder> {
    static ArrayList<Uri> listOfTrips;

    public PicAdapter(ArrayList<Uri>list){
        this.listOfTrips=list;

    }
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.only_pic_item,parent,false);
        PicAdapter.ViewHolder viewHolder= new PicAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        final Uri t= listOfTrips.get(position);


        holder.pic.setImageURI(t);
    }

    @Override
    public int getItemCount() {
        return listOfTrips.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pic;


        public ViewHolder(View itemView) {
            super(itemView);


            pic=itemView.findViewById(R.id.onlyPic);

        }
    }
}
