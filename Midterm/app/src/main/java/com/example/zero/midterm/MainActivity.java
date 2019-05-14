package com.example.zero.midterm;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText movie;
    ListView list;
    Button search;
    LoadData roll;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarMain);
        setSupportActionBar(toolbar);



        movie=(EditText) findViewById(R.id.movieNametxt);
        search=(Button)findViewById(R.id.btnSearch);
        list= (ListView)findViewById(R.id.resultsView);


    setHandlers();

    }
    public void setHandlers(){
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(isConnected()) {
                    RequestParams link = new RequestParams("GET", "https://api.themoviedb.org/3/search/movie?");
                    link.addParam("query", movie.getText().toString());
                    link.addParam("api_key", "a1343339e2d3da7200f267259ac24a6b");
                    link.addParam("page", "1");

                    roll=new LoadData(MainActivity.this);
                    roll.execute(link);

                }
                else {
                    Log.d("Connection","Connection Error");
                }
            }
        });
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.quit) {
            finish();
            return true;
        }
        if(id==R.id.fav){
            //add intent for fav
            Intent i = new Intent(MainActivity.this,MovieFavorites.class);
            startActivity(i);

        }
        if(id==R.id.popularity_sort){
            sortByPop(Movie.results);
            roll.notifyStuff();



        }
        if(id==R.id.rating_sort){
            sortByRating(Movie.results);
            roll.notifyStuff();


        }

        return super.onOptionsItemSelected(item);
    }
    public static void sortByPop(ArrayList<Movie> list){
        for (int i =0; i<list.size()-1;i++){
            for (int z=0; z<list.size()-i-1;z++){
                if(new Double(list.get(z).getPopularity()).compareTo(new Double(list.get(z+1).getPopularity()))<0){
                    Movie temp=list.get(z);
                    list.set(z,list.get(z+1));
                    list.set(z+1,temp);
                }
            }

        }
    }
    public static void sortByRating(ArrayList<Movie> list){
        for (int i =0; i<list.size()-1;i++){
            for (int z=0; z<list.size()-i-1;z++){
                if(new Double(list.get(z).getRating()).compareTo(new Double(list.get(z+1).getRating()))<0){
                    Movie temp=list.get(z);
                    list.set(z,list.get(z+1));
                    list.set(z+1,temp);
                }
            }

        }
    }
    private boolean isConnected() {
        ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();

        if (networkInfo == null || !networkInfo.isConnected() ||
                (networkInfo.getType() != ConnectivityManager.TYPE_WIFI
                        && networkInfo.getType() != ConnectivityManager.TYPE_MOBILE)) {
            return false;
        }
        return true;
    }
}
