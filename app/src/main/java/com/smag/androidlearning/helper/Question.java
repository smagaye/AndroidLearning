package com.smag.androidlearning.helper;

public class Question {
    private String textClaire1;
    private String textClaire2;
    private String textClaire3;
    private String textClaire4;
    private boolean resultat;
    private String textCache1;
    private String textCache2;
    private String textCache3;
    private String bruit1;
    private String bruit2;
    public Question() {
    }

    public Question(String textClaire1, String textClaire2, String textClaire3, String textClaire4, String textCache1, String textCache2, String textCache3) {
        this.textClaire1 = textClaire1;
        this.textClaire2 = textClaire2;
        this.textClaire3 = textClaire3;
        this.textClaire4 = textClaire4;
        this.textCache1 = textCache1;
        this.textCache2 = textCache2;
        this.textCache3 = textCache3;
    }

    public String getBruit1() {
        return bruit1;
    }

    public void setBruit1(String bruit1) {
        this.bruit1 = bruit1;
    }

    public String getBruit2() {
        return bruit2;
    }

    public void setBruit2(String bruit2) {
        this.bruit2 = bruit2;
    }

    public String getTextCache1() {
        return textCache1;
    }

    public void setTextCache1(String textCache1) {
        this.textCache1 = textCache1;
    }

    public String getTextCache2() {
        return textCache2;
    }

    public void setTextCache2(String textCache2) {
        this.textCache2 = textCache2;
    }

    public String getTextCache3() {
        return textCache3;
    }

    public void setTextCache3(String textCache3) {
        this.textCache3 = textCache3;
    }

    public String getTextClaire1() {
        return textClaire1;
    }

    public void setTextClaire1(String textClaire1) {
        this.textClaire1 = textClaire1;
    }

    public String getTextClaire2() {
        return textClaire2;
    }

    public void setTextClaire2(String textClaire2) {
        this.textClaire2 = textClaire2;
    }

    public String getTextClaire3() {
        return textClaire3;
    }

    public void setTextClaire3(String textClaire3) {
        this.textClaire3 = textClaire3;
    }

    public String getTextClaire4() {
        return textClaire4;
    }

    public void setTextClaire4(String textClaire4) {
        this.textClaire4 = textClaire4;
    }

    public boolean isResultat() {
        return resultat;
    }

    public void setResultat(boolean resultat) {
        this.resultat = resultat;
    }


}
