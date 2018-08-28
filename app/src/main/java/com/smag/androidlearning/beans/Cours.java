package com.smag.androidlearning.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.smag.androidlearning.helper.DateConverter;

import java.util.Date;

@Entity
public class Cours {
    private static final long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idcoursColonne")
    private Integer idcours;

    @ColumnInfo(name = "datedernierelectureColonne")
    @TypeConverters(DateConverter.class)
    private Date datedernierelecture;

    @ColumnInfo(name = "contenuColonne")
    private String contenu;

    @Embedded(prefix = "ressourcedescriptionColonne")
    private Ressourcedescription ressourcedescription;

    @Embedded(prefix = "themeColonne")
    private Theme theme;

    public Integer getIdcours() {
        return idcours;
    }

    public void setIdcours(Integer idcours) {
        this.idcours = idcours;
    }

    public Date getDatedernierelecture() {
        return datedernierelecture;
    }

    public void setDatedernierelecture(Date datedernierelecture) {
        this.datedernierelecture = datedernierelecture;
    }

    public Ressourcedescription getRessourcedescription() {
        return ressourcedescription;
    }

    public void setRessourcedescription(Ressourcedescription ressourcedescription) {
        this.ressourcedescription = ressourcedescription;
    }

    public Theme getTheme() {
        return theme;
    }

    public void setTheme(Theme theme) {
        this.theme = theme;
    }

    public String getContenu() {
        return contenu;
    }

    public void setContenu(String contenu) {
        this.contenu = contenu;
    }

    @Override
    public String toString() {
        return "Cours{" +
                "idcours=" + idcours +
                ", datedernierelecture=" + datedernierelecture +
                ", contenu='" + contenu + '\'' +
                ", ressourcedescription=" + ressourcedescription +
                ", theme=" + theme +
                '}';
    }
}
