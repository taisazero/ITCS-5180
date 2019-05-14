package com.example.zero.midterm;

import java.util.ArrayList;

/**
 * Created by Zero on 10/16/2017.
 */

public class Movie {
    String name;
    String overview;
    String date;
    String rating;
    String sImageURL;
    String posterPath;
    String popularity;
    boolean isFavorite;
    String lImageURL;

    public String getlImageURL() {
        return lImageURL;
    }

    public void setlImageURL(String lImageURL) {
        this.lImageURL = lImageURL;
    }

    public static ArrayList<Movie> results;
    public static ArrayList<Movie>favorites;

    @Override
    public String toString() {
        return "Movie{" +
                "name='" + name + '\'' +
                ", overview='" + overview + '\'' +
                ", date='" + date + '\'' +
                ", rating='" + rating + '\'' +
                ", sImageURL='" + sImageURL + '\'' +
                ", posterPath='" + posterPath + '\'' +
                ", popularity='" + popularity + '\'' +
                ", isFavorite=" + isFavorite +
                ", lImageURL='" + lImageURL + '\'' +
                '}';
    }

    public Movie(){

    }

    public Movie(String name, String overview, String date, String rating, String sImageURL, String posterPath, String popularity) {
        this.name = name;
        this.overview = overview;
        this.date = date;
        this.rating = rating;
        this.sImageURL = sImageURL;
        this.posterPath = posterPath;
        this.popularity = popularity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getRating() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating = rating;
    }

    public String getsImageURL() {
        return sImageURL;
    }

    public void setsImageURL(String sImageURL) {
        this.sImageURL = sImageURL;
    }

    public String getPosterPath() {
        return posterPath;
    }

    public void setPosterPath(String posterPath) {
        this.posterPath = posterPath;
    }

    public String getPopularity() {
        return popularity;
    }

    public void setPopularity(String popularity) {
        this.popularity = popularity;
    }
    public boolean isFavorite() {
        return isFavorite;
    }

    public void setFavorite(boolean favorate) {
        isFavorite = favorate;
    }
}
