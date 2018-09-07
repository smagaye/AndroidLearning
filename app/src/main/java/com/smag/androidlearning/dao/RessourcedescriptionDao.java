package com.smag.androidlearning.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;
import com.smag.androidlearning.beans.Ressourcedescription;
import java.util.List;

@Dao
public interface RessourcedescriptionDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void persist(Ressourcedescription ressourcedescription);

    @Update
    public void update(Ressourcedescription ressourcedescription);

    @Delete
    public void delete(Ressourcedescription ressourcedescription);

    @Query("SELECT * FROM Ressourcedescription")
    public List<Ressourcedescription> getAllRessourcedescriptions();

    @Query("SELECT * FROM Ressourcedescription WHERE idressourcedescriptionColonne = :idressourcedescription")
    public List<Ressourcedescription> findById(int idressourcedescription);
}