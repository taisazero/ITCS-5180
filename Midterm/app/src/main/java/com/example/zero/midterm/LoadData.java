package com.example.zero.midterm;


import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;

import android.content.Intent;
import android.os.AsyncTask;
import android.speech.tts.Voice;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

//import org.apache.commons.io.IOUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.ArrayList;
import java.util.MissingFormatArgumentException;

/**
 *
 * @author Erfan Al-Hossami
 *
 * @version 10/16/2017
 */
public class LoadData extends AsyncTask<RequestParams,Void,String> {

    MainActivity m;
   static MovieAdapter adapter;

    public LoadData(MainActivity m){
        this.m=m;
        Movie.favorites=new ArrayList<Movie>();

    }


    @Override
    protected String doInBackground(RequestParams... requests) {
        String data="";
        StringBuilder sb=null;
        if (Movie.results != null) {
            ArrayList<Movie> temp = new ArrayList<>();
            for (Movie result : Movie.results) {
                if (result.isFavorite()) {
                    temp.add(result);
                }
            }
            for (Movie Movie : temp) {
                Movie.favorites.add(Movie);
            }
        }
        Movie.results = new ArrayList<>();
        try {
            HttpURLConnection connection=requests[0].setUpConnection();
            connection.connect();

            if(connection.getResponseCode()== HttpURLConnection.HTTP_OK) {
                // data= IOUtils.toString(connection.getInputStream(),"UTF-8");
                BufferedReader br= new BufferedReader (new InputStreamReader(connection.getInputStream()));
                sb=new StringBuilder();
                while((data=br.readLine())!=null){
                    sb.append(data);
                    Log.d("data",data);
                }
                br.close();
                JSONObject root=new JSONObject(sb.toString());
                JSONArray r=root.getJSONArray("results");






                Log.d("parsing 1","found "+r.length()+"Movies");
                for(int i =0; i<r.length();i++){
                    JSONObject t=r.getJSONObject(i);
                    Movie m=new Movie();
                    m.setName(t.getString("title"));
                    m.setOverview(t.getString("overview"));
                    m.setDate(t.getString("release_date"));
                    m.setPopularity(t.getString("popularity"));
                    m.setRating(t.getString("vote_average"));
                    m.setPosterPath(t.getString("poster_path"));
                    m.setsImageURL("http://image.tmdb.org/t/p/w154"+m.getPosterPath());
                    m.setlImageURL("http://image.tmdb.org/t/p/w342"+m.getPosterPath());

                    Movie.results.add(m);
                }

            }
            else {
                Log.d("Connection", "Connection Failed");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return sb.toString();
    }



    @Override
    protected void onPostExecute(String s) {

        for (Movie m : Movie.results) {
            Log.d("Movies",m.toString() );
        }
         adapter = new MovieAdapter(m,Movie.results);

        m.list.setAdapter(adapter);

        if(Movie.results.size()==0){
            Toast.makeText(m,"No results found",Toast.LENGTH_LONG).show();
        }
        m.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ResultsActivity::", "List clicked");
                Intent i = new Intent(m,MovieDetails.class);
                i.putExtra("position",position);

                m.startActivity(i);




            }
        });



        //m.finish();

    }
    public static void notifyStuff (){
        adapter.notifyDataSetChanged();

    }
}

