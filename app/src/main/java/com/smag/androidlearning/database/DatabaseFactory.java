package com.smag.androidlearning.database;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.arch.persistence.room.migration.Migration;
import android.content.Context;
import android.support.annotation.NonNull;

import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.beans.Ressourcedescription;
import com.smag.androidlearning.beans.Theme;
import com.smag.androidlearning.dao.CoursDao;
import com.smag.androidlearning.dao.ExerciceDao;
import com.smag.androidlearning.dao.RessourcedescriptionDao;
import com.smag.androidlearning.dao.ThemeDao;

@Database(entities = {Ressourcedescription.class, Theme.class, Exercice.class, Cours.class}, version = 3, exportSchema = false)
public abstract class DatabaseFactory extends RoomDatabase {

    private static DatabaseFactory INSTANCE;

    private static String DB_NAME="androidlearning.db";

    public static DatabaseFactory getAppDatabase(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(), DatabaseFactory.class, DB_NAME)
                            .fallbackToDestructiveMigration()
                            .allowMainThreadQueries()
                            .build();
        }
        return INSTANCE;
    }

    public static void destroyInstance() {
        INSTANCE = null;
    }

    public abstract RessourcedescriptionDao getRessourcedescriptionDao();

    public abstract ThemeDao getThemeDao();

    public abstract ExerciceDao getExerciceDao();

    public abstract CoursDao getCoursDao();
}