package com.example.zero.inclass06;

import android.renderscript.ScriptGroup;
import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;

/**
 * Created by Zero on 10/2/2017.
 */

public class RecipeUtil {

    static public class RecipePullParser {

        static ArrayList<Recipe> parseRecipes (InputStream in) throws XmlPullParserException, IOException {
            XmlPullParser parser = XmlPullParserFactory.newInstance().newPullParser();
            parser.setInput(in,"UTF-8");
            Recipe r=null;
            ArrayList<Recipe> list=new ArrayList<Recipe>();
            int event= parser.getEventType();
            while (event!=XmlPullParser.END_DOCUMENT){
                int select=0;
               switch (event){
                   case XmlPullParser.START_TAG:
                       if(parser.getName().equals("recipe")){
                          r=new Recipe();
                            select=0;

                       }
                       else if (parser.getName().equals("title")){


                               r.setTitle(parser.nextText().trim());





                       }
                       else if (parser.getName().equals("href")){
                           r.setURL(parser.nextText().trim());
                       }
                       else if(parser.getName().equals("ingredients")){

                           r.setIngredients(parser.nextText().trim());
                       }
                       break;






                   case XmlPullParser.END_TAG:
                       if(parser.getName().equals("recipe")) {
                           list.add(r);
                           r = null;
                       }

                       break;
                   default:
                       break;

               }
               event=parser.next();
            }
            return list;
        }
    }

}
