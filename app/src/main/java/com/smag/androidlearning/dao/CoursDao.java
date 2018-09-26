package com.smag.androidlearning.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.smag.androidlearning.beans.Cours;

import java.util.List;

@Dao
public interface CoursDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void persist(Cours cours);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void persist(List<Cours> cours);

    @Update
    public void update(Cours cours);

    @Delete
    public void delete(Cours cours);

    @Query("SELECT * FROM Cours")
    public List<Cours> getAllCours();

    @Query("SELECT * FROM Cours where themeColonneidthemeColonne = :idthemeColonne")
    public List<Cours> findById(int idthemeColonne);

    @Query("SELECT * FROM Cours c where themeColonneressourcedescriptionColonnetitreColonne = :titre")
    public List<Cours> findByTheme(String titre);

    @Query("SELECT COUNT(*) FROM Cours where themeColonneidthemeColonne = :idthemeColonne")
    public int nombreCoursParTheme(int idthemeColonne);

    @Query("SELECT COUNT(*) FROM Cours where themeColonneidthemeColonne = :idthemeColonne and ressourcedescriptionColonneetatColonne=1")
    public int nombreCoursLu(int idthemeColonne);

    @Query("SELECT count(idcoursColonne) FROM Cours where ressourcedescriptionColonneetatColonne=1")
    public int nombreCoursLu();

}
