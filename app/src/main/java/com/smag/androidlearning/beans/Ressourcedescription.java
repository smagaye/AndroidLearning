package com.smag.androidlearning.beans;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import java.io.Serializable;

@Entity
public class Ressourcedescription implements Serializable{

    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "idressourcedescriptionColonne")
    private Integer idressourcedescription;

    @ColumnInfo(name = "titreColonne")
    private String titre;

    @ColumnInfo(name = "descriptionColonne")
    private String description;

    @ColumnInfo(name = "photoColonne")
    private String photo;

    @ColumnInfo(name = "etatColonne")
    private String etat;

    public Integer getIdressourcedescription() {
        return idressourcedescription;
    }

    public void setIdressourcedescription(Integer idressourcedescription) {
        this.idressourcedescription = idressourcedescription;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }

    public String getEtat() {
        return etat;
    }

    public void setEtat(String etat) {
        this.etat = etat;
    }

    @Override
    public String toString() {
        return "Ressourcedescription{\n" +
                "\nidressourcedescription=" + idressourcedescription +
                "\ntitre='" + titre  +
                "\ndescription='" + description +
                "\nphoto='" + photo +
                "\n etat='" + etat +
                "\n}";
    }
}
