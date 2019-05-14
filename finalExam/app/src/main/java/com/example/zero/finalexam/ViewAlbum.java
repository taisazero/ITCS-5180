package com.example.zero.finalexam;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;

public class ViewAlbum extends AppCompatActivity {
    static Trip t;
    RecyclerView alum;
    LinearLayoutManager lm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_album);
        alum=findViewById(R.id.album_list);
        lm= new LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false);
        if(t!=null){
            ArrayList<Uri> list =t.getPictures();
            PicAdapter pd= new PicAdapter(list);
            alum.setLayoutManager(lm);
            alum.setAdapter(pd);

        }
    }
}
