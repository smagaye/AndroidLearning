package com.smag.androidlearning.beans;

import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

public class Card {

    private int position;

    private String contenu;

    private Cours cours;

    private int max;

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    public Cours getCours() {
        return cours;
    }

    public void setCours(Cours cours) {
        this.cours = cours;
    }

    public int getMax() {
        return max;
    }

    public void setMax(int max) {
        this.max = max;
    }

    public static List<Card> split(Cours cours){
        StringTokenizer stringTokenizer = new StringTokenizer(cours.getContenu(),"+++");
        List<Card> list = new ArrayList<Card>();
        int i=0;
        while (stringTokenizer.hasMoreTokens()){
            Card card = new Card();
            card.setPosition(++i);
            card.setContenu(stringTokenizer.nextToken());
            card.setCours(cours);
            list.add(card);
        }
        return list;
    }
}
