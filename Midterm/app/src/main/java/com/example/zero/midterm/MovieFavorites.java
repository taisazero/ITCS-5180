package com.example.zero.midterm;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import java.util.List;

public class MovieFavorites extends AppCompatActivity {
    ListView list;
   static MovieAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_favorites);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarFavorites);
        setSupportActionBar(toolbar);
         adapter= new MovieAdapter(this,Movie.favorites);
        list=(ListView)findViewById(R.id.favoritesList);
        list.setAdapter(adapter);
        if(Movie.favorites.size()==0){
            Toast.makeText(this, "There are no favorites", Toast.LENGTH_SHORT).show();
        }
        setHandlers();


    }

    public void setHandlers(){
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.d("ResultsActivity::", "List clicked");
                Intent i = new Intent(MovieFavorites.this,MovieDetails.class);
                i.putExtra("position",position);

                startActivity(i);




            }
        });





    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fav_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.fav_q) {
            finishAffinity();

        }
        if(id==R.id.fav_home){
            //add intent for fav
            finish();

        }
        if(id==R.id.fav_pop){
            MainActivity.sortByPop(Movie.favorites);
           adapter.notifyDataSetChanged();

        }
        if(id==R.id.fav_rating){
            MainActivity.sortByRating(Movie.favorites);
            adapter.notifyDataSetChanged();

        }

        return super.onOptionsItemSelected(item);
    }

}
