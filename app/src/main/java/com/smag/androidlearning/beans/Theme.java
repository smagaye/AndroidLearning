package com.smag.androidlearning.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.smag.androidlearning.helper.ConverterHelper;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Theme {

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idthemeColonne")
    private Integer idtheme;

    @ColumnInfo(name = "dateactivationColonne")
    @TypeConverters(ConverterHelper.class)
    private Date dateactivation;

    @Embedded(prefix = "ressourcedescriptionColonne")
    private Ressourcedescription ressourcedescription;

    @TypeConverters(ConverterHelper.class)
    private List<Cours> listeCours =new ArrayList<Cours>();

    @TypeConverters(ConverterHelper.class)
    private List<Exercice> listeExercices;

    public Theme() {
        listeExercices = new ArrayList<Exercice>();
        this.dateactivation=new Date();
    }

    public Integer getIdtheme() {
        return idtheme;
    }

    public void setIdtheme(Integer idtheme) {
        this.idtheme = idtheme;
    }

    public Date getDateactivation() {
        return dateactivation;
    }

    public void setDateactivation(Date dateactivation) {
        this.dateactivation = dateactivation;
    }

    public Ressourcedescription getRessourcedescription() {
        return ressourcedescription;
    }

    public void setRessourcedescription(Ressourcedescription ressourcedescription) {
        this.ressourcedescription = ressourcedescription;
    }

    public List<Cours> getListeCours() {
        return listeCours;
    }

    public List<Exercice> getListeExercices() {
        return listeExercices;
    }

    public void setListeCours(List<Cours> listeCours) {
        this.listeCours = listeCours;
    }

    public void setListeExercices(List<Exercice> listeExercices) {
        this.listeExercices = listeExercices;
    }

    @Override
    public String toString() {
        return "Theme{\n" +
                "\n\tidtheme =" + idtheme +
                "\n\tdateactivation = " + dateactivation +
                "\n\tressourcedescription = " + ressourcedescription +
                "\n}";
    }
}
