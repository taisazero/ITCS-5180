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
import android.widget.ImageView;
import android.widget.TextView;

public class MovieDetails extends AppCompatActivity {

    TextView title;
    TextView overview;
    TextView date;
    TextView rating;
    ImageView pic;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbarDetails);
        setSupportActionBar(toolbar);
        title=(TextView)findViewById(R.id.titleView);
        overview=(TextView)findViewById(R.id.overviewtxt);
        date=(TextView)findViewById(R.id.releasetxt);
        rating= (TextView)findViewById(R.id.ratingtxt);
        pic=(ImageView)findViewById(R.id.lMoviePic);
        if(getIntent().getExtras()!=null){
          int location=  (int) getIntent().getExtras().get("position");
            Movie selection= Movie.results.get(location);
            title.setText(title.getText().toString()+selection.getName());
            overview.setText(overview.getText().toString()+selection.getOverview());
            date.setText(date.getText().toString()+selection.getDate());
            rating.setText(rating.getText().toString()+selection.getRating());
            new LoadImage(pic).execute(selection.getlImageURL());
        }
        else{
            Log.d("Intent error","null");
        }



    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.detail_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.quit_detail) {

            finishAffinity();

            //return true;
        }
        if(id==R.id.home){
            //add intent for fav

            finish();

        }


        return super.onOptionsItemSelected(item);
    }

}
