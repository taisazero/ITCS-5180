package com.example.zero.databasedemo;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import java.util.List;

/**
 * Created by Zero on 10/30/2017.
 */

public class DatabaseDataManager {
    private Context mContext;
    private DatabaseOpenHelper dbOpenHelper;
    private SQLiteDatabase db;
    private NoteDAD noteDAD;

    public DatabaseDataManager (Context mContext){
        this.mContext= mContext;
        dbOpenHelper = new DatabaseOpenHelper(this.mContext);
        db=dbOpenHelper.getWritableDatabase();
        noteDAD=new NoteDAD(db);
    }

    public void close(){
        if (db!=null){
            db.close();
        }
    }
    public NoteDAD getNoteDAD(){
        return this.noteDAD;
    }
    public long saveNote (Note note){

        return this.noteDAD.save(note);

    }
    public boolean updateNote(Note note)
    {
        return this.noteDAD.update(note);

    }
    public  boolean deleteNote (Note note) {
        return this.noteDAD.delete(note);
    }
    public Note getNote(long id){
        return this.noteDAD.get(id);

    }
    public List<Note> getAllNotes(){
        return this.noteDAD.getAll();
    }

}
