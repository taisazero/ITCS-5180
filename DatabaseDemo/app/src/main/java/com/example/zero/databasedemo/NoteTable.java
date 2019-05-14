package com.example.zero.databasedemo;

import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

/**
 * Created by Zero on 10/30/2017.
 */

public class NoteTable {
    static final String TABLENAME= "notes";
    static final String COLUMN_ID="_id";
    static final String COLUMN_SUBJECT="_subject";
    static final String COLUMN_TEXT="text";

    static public void onCreate(SQLiteDatabase db){
        StringBuilder sb = new StringBuilder();
        sb.append("CREATE TABLE "+TABLENAME + "(");
        sb.append(COLUMN_ID+ "integer primary key autoincrement");
        sb.append(COLUMN_SUBJECT+ "text not null");
        sb.append(COLUMN_TEXT + "text not null);");
        try{
            db.execSQL(sb.toString());
            Log.d("Creation Success","Creation Success");
        }
        catch(SQLException ex){
            Log.d("Creation failed","Creation failed");
            ex.printStackTrace();
        }
    }
    static public void onUpgrade (SQLiteDatabase db, int oldVersion, int newVersion){
        db.execSQL("DROP TABLE IF EXISTS "+TABLENAME);
        NoteTable.onCreate(db);

    }
}
