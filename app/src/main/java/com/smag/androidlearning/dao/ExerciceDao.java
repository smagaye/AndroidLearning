package com.smag.androidlearning.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.beans.Theme;

import java.util.List;

@Dao
public interface ExerciceDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void persist(Exercice exercice);

    @Update
    public void update(Exercice exercice);

    @Delete
    public void delete(Exercice exercice);

    @Query("SELECT * FROM Exercice")
    public List<Exercice> getAllExercices();

    @Query("SELECT * FROM Exercice WHERE idexerciceColonne = :idexercice")
    public List<Exercice> findById(int idexercice);

    @Query("SELECT * FROM Exercice where themeColonneressourcedescriptionColonnetitreColonne = :titre")
    public List<Exercice> findExerciceByThemeTitle(String titre);
}

