package com.smag.androidlearning.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Embedded;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.TypeConverters;
import com.smag.androidlearning.helper.ConverterHelper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
public class Theme implements Serializable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idthemeColonne")
    private Integer idtheme;

    @ColumnInfo(name = "dateactivationColonne")
    @TypeConverters(ConverterHelper.class)
    private Date dateactivation;

    @Embedded(prefix = "ressourcedescriptionColonne")
    private Ressourcedescription ressourcedescription;

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

    @Override
    public String toString() {
        return "Theme{\n" +
                "\n\tidtheme =" + idtheme +
                "\n\tdateactivation = " + dateactivation +
                "\n\tressourcedescription = " + ressourcedescription +
                "\n}";
    }
}
