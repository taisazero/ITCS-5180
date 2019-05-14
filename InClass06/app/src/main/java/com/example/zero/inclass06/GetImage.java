package com.example.zero.inclass06;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.AsyncTask;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

/**
 * Created by Zero on 10/3/2017.
 */

public class GetImage extends AsyncTask<String,Integer,Bitmap> {
    ProgressBar pb;
    ImageView v;
    public GetImage(ProgressBar pb, ImageView v){
        this.pb=pb;
        this.v=v;
    }
    @Override
    protected void onPreExecute(){

        pb.setMax(100);
        pb.setProgress(0);
        pb.setVisibility(View.VISIBLE);
    }
    @Override
    protected Bitmap doInBackground(String... strings) {

        String r=strings[0];

        try {
            URL link=new URL(r);
            HttpURLConnection con=(HttpURLConnection)link.openConnection();
            con.setRequestMethod("GET");
            con.connect();
            Log.d("Connection Test image","Connected");
            publishProgress(1);
            BitmapFactory bm=new BitmapFactory();
            Bitmap image = bm.decodeStream(con.getInputStream());
            return image;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onProgressUpdate(Integer...values) {
        for (int i = values[0]; i < 100; i++) {
            pb.setProgress(i);
            Thread t = new Thread();
            try {
                t.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
        @Override
        protected void onPostExecute(Bitmap m){
            pb.setVisibility(View.INVISIBLE);
        v.setImageBitmap(m);
            v.setVisibility(View.VISIBLE);
        v.refreshDrawableState();

    }
    }

