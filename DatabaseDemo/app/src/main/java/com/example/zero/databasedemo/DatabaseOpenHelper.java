package com.example.zero.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by Zero on 10/30/2017.
 */

public class DatabaseOpenHelper extends SQLiteOpenHelper {


    static final String DB_NAME = "mynotes.db";
    static final int DB_VERSION = 1;
    public DatabaseOpenHelper (Context context){
        super(context,DB_NAME,null,DB_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        NoteTable.onCreate(sqLiteDatabase);
        Log.d("Creation test", "Succees");
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        NoteTable.onUpgrade(sqLiteDatabase,i,i1);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        return super.getWritableDatabase();
    }
}
