package com.example.zero.midterm;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Zero on 10/16/2017.
 */
public class MovieAdapter extends ArrayAdapter<Movie> {
    Context context;
    ArrayList<Movie> list;

    public MovieAdapter(Context context, ArrayList<Movie> movieArrayList) {
        super(context, R.layout.item_row_layout, movieArrayList);
        this.context = context;
        this.list = movieArrayList;
    }

    @Override
    public View getView(final int position, View convertView, final ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(R.layout.item_row_layout, parent, false);
            holder = new ViewHolder();
            holder.image = (ImageView) convertView.findViewById(R.id.smoviePic);
            holder.releaseDate = (TextView) convertView.findViewById(R.id.releaseDate);
            holder.name = (TextView) convertView.findViewById(R.id.smallName);
            holder.favorate = (ImageButton) convertView.findViewById(R.id.favBtn);
            convertView.setTag(holder);

        }
        holder = (ViewHolder) convertView.getTag();
        TextView name = holder.name;
        TextView aName = holder.releaseDate;
        ImageView pic = holder.image;
        final ImageButton favorate = holder.favorate;
        name.setText(list.get(position).getName());
        aName.setText(list.get(position).getDate());
        new LoadImage(pic).execute(list.get(position).getsImageURL());

        if (list.get(position).isFavorite()) {
            favorate.setImageResource(android.R.drawable.star_big_on);
        } else {
            favorate.setImageResource(android.R.drawable.star_big_off);
        }


        favorate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (list.get(position).isFavorite()) {
                    Log.d("MovieAdapter", "getView:isFavorite");
                    favorate.setImageResource(android.R.drawable.star_big_off);
                    list.get(position).setFavorite(false);
                    Movie.favorites.remove(list.get(position));
                    Toast.makeText(context,"Item removed from favorites",Toast.LENGTH_LONG).show();
                    if (context instanceof MainActivity || context instanceof MovieFavorites){
                        Log.d("MovieAdapter", "getView:isFavorite:list removed item");
                        notifyDataSetChanged();
                        LoadData.notifyStuff();

                    }
                } else {
                    favorate.setImageResource(android.R.drawable.star_big_on);
                    list.get(position).setFavorite(true);
                    Movie.favorites.add(list.get(position));
                    Log.d("MovieAdapter", "getView:isFavorite:list removed item");
                    Toast.makeText(context,"Item added to favorites",Toast.LENGTH_LONG).show();
                    notifyDataSetChanged();
                    LoadData.notifyStuff();

                }
            }
        });

        return convertView;
    }

    static class ViewHolder {
        ImageView image;
        TextView name;
        TextView releaseDate;
        ImageButton favorate;
    }

}