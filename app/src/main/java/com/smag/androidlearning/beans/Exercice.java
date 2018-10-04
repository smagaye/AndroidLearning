package com.smag.androidlearning.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.StringTokenizer;

@Entity
public class Exercice implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idexerciceColonne")
    private Integer idexercice;

    @ColumnInfo(name = "sequencequestionColonne")
    private String sequencequestion;

    @ColumnInfo(name = "scoreColonne")
    private Integer score;

    @ColumnInfo(name = "tempsreponseColonne")
    private Integer tempsreponse;

    @Embedded(prefix = "themeColonne")
    private Theme theme;

    public Integer getIdexercice() {
        return idexercice;
    }

    public void setIdexercice(Integer idexercice) {
        this.idexercice = idexercice;
    }

    public String getSequencequestion() {
        return sequencequestion;
    }

    public void setSequencequestion(String sequencequestion) {
        this.sequencequestion = sequencequestion;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Integer getTempsreponse() {
        return tempsreponse;
    }

    public void setTempsreponse(Integer tempsreponse) {
        this.tempsreponse = tempsreponse;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public static HashMap<String,List<String>> extractExercise(Cours cours){
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
            if(k==2) break;
        }
        parts.put("bonneReponse",bonneReponse);
        parts.put("morceaux",morceaux);
        return parts;
    }

    @Override
    public String toString() {
        return "Exercice{" +
                "idexercice=" + idexercice +
                ", sequencequestion='" + sequencequestion + '\'' +
                ", score=" + score +
                ", tempsreponse=" + tempsreponse +
                ", theme=" + theme +
                '}';
    }
}
