package com.example.zero.inclass06;

import java.util.ArrayList;

/**
 * Created by Zero on 10/2/2017.
 */

public class Recipe {
    public static ArrayList<Recipe> recipes=new ArrayList<Recipe>();
    String URL;
    String ingredients;
    String title;
    public static int index=0;
    public  Recipe(){

    }
    public Recipe(String t, String u, String i){
        this.URL=u;
        this.title=t;
        this.ingredients=i;
        recipes.add(this);

    }

    public String getTitle(){return this.title;}
    public String getURL(){return this. URL;}
    public String getIngredients(){return this.ingredients;}
    public void setTitle(String s ){this.title=s;}
    public void setURL(String s ){this.URL=s;}
    public void setIngredients(String s){
        this.ingredients=s;
    }
    @Override
    public String toString(){
        return "Title: "+this.title+","+"\tIngredients: "+this.ingredients+"\tURL: "+this.URL;
    }

}
