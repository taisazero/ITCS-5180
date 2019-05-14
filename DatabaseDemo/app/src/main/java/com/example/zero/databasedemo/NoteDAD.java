package com.example.zero.databasedemo;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Zero on 10/30/2017.
 */

public class NoteDAD {
    private SQLiteDatabase db;
    public NoteDAD(SQLiteDatabase db){
        this.db=db;
    }
    public long save (Note note){
        ContentValues values= new ContentValues();
        values.put(NoteTable.COLUMN_SUBJECT,note.getSubject());
        values.put(NoteTable.COLUMN_TEXT,note.getText());

        return db.insert(NoteTable.TABLENAME,null,values);

    }
    public boolean update (Note note){
        ContentValues values= new ContentValues();
        values.put(NoteTable.COLUMN_SUBJECT,note.getSubject());
        values.put(NoteTable.COLUMN_TEXT,note.getText());

         return db.update(NoteTable.TABLENAME,values,NoteTable.COLUMN_ID+"=?",new String[]{note.get_id()+""}) >0;


    }
    public boolean delete (Note note){
       return  db.delete(NoteTable.TABLENAME,NoteTable.COLUMN_ID+"=?",new String[]{note.get_id()+""})>0;



    }
    public Note get (long id){
        Note note=null;
        Cursor c= db.query(true,NoteTable.TABLENAME, new String[]{NoteTable.COLUMN_ID, NoteTable.COLUMN_SUBJECT,NoteTable.COLUMN_TEXT},NoteTable.COLUMN_ID+"=?",new String[]{id+""},null,null,null,null,null);

        if (c!=null && c.moveToFirst()){
            note=buildNoteFromCursor(c);
            if(!c.isClosed()){
                c.close();
            }

        }
        return note;
    }
    public List<Note> getAll() {
        List <Note>notes=new ArrayList<Note>();
        Cursor c= db.query(NoteTable.TABLENAME,new String[]{NoteTable.COLUMN_ID,NoteTable.COLUMN_SUBJECT,NoteTable.COLUMN_TEXT},null,null,null,null,null);

        if(c!=null && c.moveToFirst()){
            do{
                Note note= buildNoteFromCursor(c);
                if (note != null) {
                notes.add(note);

                }


            }while(c.moveToNext());
            if(!c.isClosed()){
                c.close();
            }
        }
        return notes;
    }
    private Note buildNoteFromCursor (Cursor c){
        Note note = null;
        if (c!=null){
            note = new Note();
            note.set_id(c.getLong(0));
            note.setSubject(c.getString(1));
            note.setText(c.getString(2));

        }
        return note;

    }
}
