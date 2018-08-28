package com.smag.androidlearning.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity
public class Exercice {
    private static final long serialVersionUID = 1L;

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
