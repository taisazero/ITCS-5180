package com.example.zero.databasedemo;

/**
 * Created by Zero on 10/30/2017.
 */

public class Note {
    private long _id;
    private String subject;
    private String text;

    public long get_id() {
        return _id;
    }

    public void set_id(long _id) {
        this._id = _id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public Note(String subject, String text ){
        this.subject=subject;
        this.text=text;
    }
    public Note(){


    }

}
