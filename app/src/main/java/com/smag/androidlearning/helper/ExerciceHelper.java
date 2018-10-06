package com.smag.androidlearning.helper;

import android.content.Context;
import android.support.v4.app.DialogFragment;

import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Theme;
import com.smag.androidlearning.database.DatabaseFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

public class ExerciceHelper {


    public static HashMap<String,List<String>> extractExercise(Cours cours,Context context){
        HashMap<String,List<String>> components=parse(cours);
        components.get("morceaux").addAll(getBadAnswer(context,cours));
        return components;
    }

    private static List<String> getBadAnswer(Context context,Cours cours) {
        Cours crs = getRandomCoursByTheme(context,cours);
        List<String> list = parse(crs).get("bonneReponse");
        List<String> bad = new ArrayList<String>();
        int index = list.size();
        int pos1 = (int) (Math.random() * ( index -1));
        bad.add(list.get(pos1));
        int pos2 = (int) (Math.random() * ( index  -1));
        while(pos1==pos2){
            pos2 = (int) (Math.random() * ( index -1));
        }
        bad.add(list.get(pos2));
        return  bad;
    }

    private static HashMap<String,List<String>> parse(Cours cours){
        HashMap<String,List<String>> parts = new HashMap<String,List<String>>();
        List<String> bonneReponse = new ArrayList<String>();
        List<String> morceaux = new ArrayList<String>();
        List<String> mauvaiseReponse = new ArrayList<String>();

        StringTokenizer stringTokenizer = new StringTokenizer(cours.getContenu(),"+++");
        int k=0;
        while (stringTokenizer.hasMoreTokens()){
            k++;
            String phrase = stringTokenizer.nextToken();
            StringTokenizer stringTokenizer1 = new StringTokenizer(phrase,"--");
            int i=0;
            while (stringTokenizer1.hasMoreTokens()){
                i++;
                String mot =stringTokenizer1.nextToken();
                if(i%2==0){
                    bonneReponse.add(mot);
                }
                else {
                    morceaux.add(mot);
                }
            }
            if(k==3) break;
        }
        parts.put("bonneReponse",bonneReponse);
        parts.put("morceaux",morceaux);
        return parts;
    }

    public static Cours getRandomCoursByTheme(Context context ,Cours cours){
        List<Cours> crs=DatabaseFactory.getAppDatabase(context).getCoursDao().findByTheme(cours.getTheme().getRessourcedescription().getTitre());

        int taille = crs.size();
        int pos = (int) (Math.random() * ( taille ));
        while(cours.getIdcours()==crs.get(pos).getIdcours()){
            pos = (int) (Math.random() * ( taille ));
        }
        return crs.get(pos);
    }

}
