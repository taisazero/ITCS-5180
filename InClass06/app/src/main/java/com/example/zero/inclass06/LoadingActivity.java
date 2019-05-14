package com.example.zero.inclass06;

import android.content.Intent;
import android.net.ConnectivityManager;
import android.os.AsyncTask;
import android.support.annotation.IntegerRes;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.Toast;

import org.xmlpull.v1.XmlPullParserException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;

public class LoadingActivity extends AppCompatActivity {
    static ProgressBar pb;
    public static String toSend="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_loading);
        pb=(ProgressBar)findViewById(R.id.Loading);


        pb.setProgress(0);
        pb.setMax(100);
        if(getIntent().getExtras()!=null) {
            String[] ings=getIntent().getExtras().getStringArray("INGREDIENTS");

            String url="http://www.recipepuppy.com/api/?format=xml&";
            RequestParams temp=new RequestParams("GET",url);
            StringBuilder sb=new StringBuilder();
            for (int i=0;i<ings.length;i++) {
                if(!(i-ings.length==-1)) {
                    sb.append(ings[i] + ",");
                }
                else{
                    sb.append(ings[i]);
                }



            }
            temp.addParam("i" ,sb.toString() );
            temp.addParam("q",getIntent().getExtras().getString("DISH"));
            new getData().execute(temp);
        }
        //ToDo set Max

    }
    private class getData extends AsyncTask<RequestParams,Integer,ArrayList<Recipe>>{
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
        protected ArrayList<Recipe> doInBackground(RequestParams... strings) {
            BufferedReader r=null;
            try{
                HttpURLConnection con=strings[0].setUpConnection();
                Log.d("ConnectionTest","Setup");
                con.connect();
                Log.d("ConnectionTest","Connected");



                    publishProgress(1);


                int code=con.getResponseCode();
                if(code==HttpURLConnection.HTTP_OK){

                    Log.d("ConnectionTest","Getting Stream");
                    InputStream in= con.getInputStream();


                    return RecipeUtil.RecipePullParser.parseRecipes(in);

                }

            } catch (IOException e) {
                e.printStackTrace();
            } catch (XmlPullParserException e) {
                e.printStackTrace();
            }


            return null;
        }
      @Override
        protected void onPostExecute(ArrayList<Recipe>r){
          Log.d("Async",r.toString());

          Recipe.recipes=r;
          Intent i =new Intent(LoadingActivity.this,Results.class);
          startActivity(i);
          finish();
      }

    }

    public boolean isConnected(){
        ConnectivityManager cm=(ConnectivityManager)getSystemService(CONNECTIVITY_SERVICE);
        //todo finish
        return isConnected();
    }

}
