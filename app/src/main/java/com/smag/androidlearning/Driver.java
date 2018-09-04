package com.smag.androidlearning;

import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.beans.Ressourcedescription;
import com.smag.androidlearning.beans.Theme;
import com.smag.androidlearning.database.DatabaseFactory;
import com.smag.androidlearning.helper.XmlManager;

import org.w3c.dom.Document;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Date;

public class Driver extends AppCompatActivity {

    DatabaseFactory databaseFactory;

    private static String MsgLog ="MsgConsole";

   /* public static void main(String[] args) {
        Document doc= XmlManager.getDocument("/Users/apple/AndroidStudioProjects/androidLearning/app/src/main/res/xml/androidlearningdata.xml");
        XmlManager.runXmlFile(doc);
    }
   */
    public static void daoTest(){
         /*databaseFactory = DatabaseFactory.getAppDatabase(getApplicationContext());

        //Persistence Ressourcedescription
        Ressourcedescription ressourcedescription = new Ressourcedescription();
        ressourcedescription.setTitre("Layout");
        ressourcedescription.setDescription("Les vues sur android");
        ressourcedescription.setEtat("non lu");
        ressourcedescription.setPhoto("");
        databaseFactory.getRessourcedescriptionDao().persist(ressourcedescription);

        Log.i(MsgLog,"Titre Ressource " + databaseFactory.getRessourcedescriptionDao().getAllRessourcedescriptions().get(0).toString());

        //Persistence Theme
        Theme theme = new Theme();
        theme.setRessourcedescription(ressourcedescription);
        theme.setDateactivation(new Date());
        theme.setTitre("Theme Widget");
        databaseFactory.getThemeDao().persist(theme);

        Log.i(MsgLog,"Date activation Theme: " + databaseFactory.getThemeDao().getAllThemes().get(0).toString());

        // Persistence Cours
        Cours cours = new Cours();
        Ressourcedescription ressourcedescription1 = new Ressourcedescription();
        ressourcedescription1.setTitre("LinearLayout");
        ressourcedescription1.setDescription("Dispositon des elements");
        ressourcedescription1.setEtat("lu");
        ressourcedescription1.setPhoto("pop.png");

        cours.setTheme(theme);
        cours.setRessourcedescription(ressourcedescription1);
        cours.setDatedernierelecture(new Date());
        databaseFactory.getCoursDao().persist(cours);

        Log.i(MsgLog,"Identifiant Cours: " + databaseFactory.getCoursDao().getAllCours().get(0).toString());


        //Persistence Exercice
        Exercice exercice =new Exercice();
        exercice.setScore(10);
        exercice.setTheme(theme);
        exercice.setSequencequestion("1-3;2-7");
        exercice.setTempsreponse(27);

        databaseFactory.getExerciceDao().persist(exercice);

        Log.i(MsgLog,"\nExercice : " + databaseFactory.getExerciceDao().getAllExercices().get(0).toString());
    */

    }

}
