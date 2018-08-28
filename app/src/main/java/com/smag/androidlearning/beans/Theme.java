package com.smag.androidlearning.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Index;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;

import com.smag.androidlearning.helper.DateConverter;

import java.util.Date;

@Entity
public class Theme {

    private static final long serialVersionUID = 1L;

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idthemeColonne")
    private Integer idtheme;

    @ColumnInfo(name = "titreColonne")
    private String titre;

    @ColumnInfo(name = "dateactivationColonne")
    @TypeConverters(DateConverter.class)
    private Date dateactivation;

    @Embedded(prefix = "ressourcedescriptionColonne")
    private Ressourcedescription ressourcedescription;

    public Integer getIdtheme() {
        return idtheme;
    }

    public void setIdtheme(Integer idtheme) {
        this.idtheme = idtheme;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
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
}
