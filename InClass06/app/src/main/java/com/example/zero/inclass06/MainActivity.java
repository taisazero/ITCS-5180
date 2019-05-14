package com.example.zero.inclass06;

import android.content.Intent;
import android.media.Image;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.ButtonBarLayout;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;

import org.w3c.dom.Text;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
 static int ingredients;
    static ArrayList<View> views;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ImageButton add=(ImageButton)findViewById(R.id.add);
        ImageButton remove=(ImageButton)findViewById(R.id.remove);
        Button Search= (Button)findViewById(R.id.btnSearch);



        ingredients=1;
       /* for (int x=0;x<5;x++){
            ConstraintLayout layout=(ConstraintLayout) findViewById(R.id.layout);
            ImageButton i = new ImageButton(MainActivity.this);
            i.setBackgroundResource(R.drawable.add);
            i.setVisibility(View.VISIBLE);
            i.setLayoutParams(add.getLayoutParams());
            i.setId(View.generateViewId());


            ImageButton b = new ImageButton(MainActivity.this);
            b.setId(View.generateViewId());
            b.setBackgroundResource(R.drawable.remove);
            b.setVisibility(View.INVISIBLE);
            b.setLayoutParams(remove.getLayoutParams());
            EditText ing = new EditText(MainActivity.this);
            ing.setText("");

            layout.addView(i);
            layout.addView(b);
            layout.addView(ing);

        }*/
        setHandlers();


    }

    public void setHandlers(){
        ImageButton add=(ImageButton)findViewById(R.id.add);
        ImageButton remove=(ImageButton)findViewById(R.id.remove);
        Button Search= (Button)findViewById(R.id.btnSearch);

        add.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
               //todo

            }
        });

        remove.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                //todo


            }
        });

        Search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i =new Intent(MainActivity.this,LoadingActivity.class);
                String [] list= new String[5];
                EditText txt=(EditText) findViewById(R.id.dishTxt);
                EditText in=(EditText)findViewById(R.id.editText2);
                list[0]=  in.getText().toString();

                i.putExtra("DISH",txt.getText().toString());
                Log.d("Dish",txt.getText().toString());
                i.putExtra("INGREDIENTS",list);
                startActivity(i);
                finish();



            }
        });
    }

}

