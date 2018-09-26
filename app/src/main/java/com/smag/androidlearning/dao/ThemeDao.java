package com.smag.androidlearning.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import android.database.Cursor;
import com.smag.androidlearning.beans.Theme;

import java.util.List;

@Dao
public interface ThemeDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void persist(Theme theme);

    @Update
    public void update(Theme theme);

    @Delete
    public void delete(Theme theme);

    @Query("delete FROM Theme")
    public void deleteAll();

    @Query("SELECT * FROM Theme")
    public List<Theme> getAllThemes();

    @Query("SELECT * FROM Theme WHERE idthemeColonne = :idtheme")
    public Cursor findById(int idtheme);

    @Insert
    public void insertAll(List<Theme> list);

}
