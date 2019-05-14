package com.example.zero.inclass06;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.w3c.dom.Text;

public class Results extends AppCompatActivity {
    TextView title;
    TextView URL;
    TextView ingredients;
    ImageView result;
    ProgressBar pb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results);
        title=(TextView)findViewById(R.id.DishName);
        URL=(TextView)findViewById(R.id.urlView);
        ingredients=(TextView)findViewById(R.id.ingredientsView);
        result=(ImageView)findViewById(R.id.imageView);
        pb=(ProgressBar)findViewById(R.id.progressBar);
        new GetImage(pb,result).execute("https://c1.staticflickr.com/5/4286/35513985750_2690303c8b_z.jpg%20");
        URL.setClickable(true);
        title.setText(title.getText().toString()+": "+Recipe.recipes.get(Recipe.index).getTitle());

        URL.setText(URL.getText().toString()+" "+Recipe.recipes.get(Recipe.index).getURL());
        ingredients.setText(ingredients.getText().toString()+" "+Recipe.recipes.get(Recipe.index).getIngredients());
        setHandlers();


    }
    public void setHandlers(){
        Button finish= (Button)findViewById(R.id.finish);
        finish.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });

        ImageButton next = (ImageButton)findViewById(R.id.next);
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Recipe.index<Recipe.recipes.size()-1) {
                    Recipe.index++;
                }
                Intent i = new Intent(Results.this,Results.class);
                startActivity(i);
                finish();
            }
        });
        ImageButton previous = (ImageButton)findViewById(R.id.prev);
        previous.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(Recipe.index>0) {
                    Recipe.index--;
                }
                Intent i = new Intent(Results.this,Results.class);
                startActivity(i);
                finish();
            }
        });
        ImageButton first = (ImageButton)findViewById(R.id.first);
        first.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe.index=0;
                Intent i = new Intent(Results.this,Results.class);
                startActivity(i);
                finish();
            }
        });
        ImageButton last = (ImageButton)findViewById(R.id.last);
        last.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Recipe.index= Recipe.recipes.size()-1;
                Intent i = new Intent(Results.this,Results.class);
                startActivity(i);
                finish();
            }
        });
        URL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                String temp=URL.getText().toString().substring(6);
                Log.d("URL debug", temp);
                Intent i =new Intent(Intent.ACTION_VIEW,Uri.parse(temp));

                startActivity(i);
            }
        });

    }
    @Override
    protected void onStart(){
        super.onStart();
        // todo Add animation


    }
}
