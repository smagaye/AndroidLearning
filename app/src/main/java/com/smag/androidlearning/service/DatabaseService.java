package com.smag.androidlearning.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.widget.Toast;

import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.beans.Ressourcedescription;
import com.smag.androidlearning.beans.Theme;
import com.smag.androidlearning.database.DatabaseFactory;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class DatabaseService extends Service {

    private static XmlPullParser xpp;
    private static int eventType;
    private static String texte;
    private boolean isDbEmpty;
    private InputStream inputStream;
    private static List<Theme> listeTheme = new ArrayList<Theme>();
    private static List<Cours> listeCours = new ArrayList<Cours>();
    private static List<Exercice> listeExercice = new ArrayList<Exercice>();
    private DatabaseFactory databaseFactory;

    public class DatabaseServiceBinder extends Binder{
        public DatabaseService getService(){
            return DatabaseService.this;
        }
    }

    private IBinder iBinder =new DatabaseServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();

    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        try {
            inputStream =getAssets().open(getResources().getString(R.string.datafile));
            Toast.makeText(getApplicationContext(),"File Configuration androidlearning.xml loading...",Toast.LENGTH_LONG).show();
            startDatabaseConfiguration(inputStream);
            stopSelf();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(getApplicationContext(),"Service arrete",Toast.LENGTH_LONG).show();
    }

    private static void intializeParser(InputStream inputStream){
        try {
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            factory.setNamespaceAware(true);
            xpp = factory.newPullParser();
            xpp.setInput( inputStream,null );
            eventType = xpp.getEventType();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    private void startDatabaseConfiguration(InputStream inputStream) {
        if(xpp==null) intializeParser(inputStream);
        parseXmlFile();
        Toast.makeText(getApplicationContext(),"Parsing androidlearning.xml ...",Toast.LENGTH_LONG).show();

        if(databaseFactory==null){
            databaseFactory = DatabaseFactory.getAppDatabase(getApplicationContext());
            Toast.makeText(getApplicationContext(),"Setting Database Parameters ...",Toast.LENGTH_LONG).show();
        }
        isDbEmpty=isDatabaseEmpty();

        if(isDbEmpty){
            Toast.makeText(getApplicationContext(),"Database is empty!",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Before Inserting!",Toast.LENGTH_LONG).show();
            showData();
            storeData();
            Toast.makeText(getApplicationContext(),"Upload data Succesfull!",Toast.LENGTH_LONG).show();
            Toast.makeText(getApplicationContext(),"Database Room is ready for CRUD Operations!",Toast.LENGTH_LONG).show();
        }else{
            Toast.makeText(getApplicationContext(),"Database already exsits!",Toast.LENGTH_LONG).show();
        }

    }

    private static void parseXmlFile() {
        Theme theme = null;
        int idtheme=0;
        try {
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("theme")) {
                            theme = new Theme();
                            theme.setIdtheme(idtheme+=1);
                            theme.setRessourcedescription(extractRessourceDescription());
                            listeCours.addAll( extractCours(theme));
                            listeExercice.addAll(extractExercices(theme));
                            listeTheme.add(theme);
                        }
                        break;
                    default:
                        break;
                }
                eventType = xpp.next();
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
    }

    private static Ressourcedescription extractRessourceDescription() {
        Ressourcedescription ressourcedescription =new Ressourcedescription();;
        try{
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if(tagname.equalsIgnoreCase("blockexercices") || tagname.equalsIgnoreCase("blockcours"))
                        {
                            return ressourcedescription;
                        }
                        break;
                    case XmlPullParser.TEXT:
                        texte = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        try{ if(tagname.equalsIgnoreCase("ressourcedescription")){
                            return ressourcedescription;
                        } else if(tagname.equalsIgnoreCase("titre")){
                            ressourcedescription.setTitre(texte);
                        } else if(tagname.equalsIgnoreCase("description")){
                            ressourcedescription.setDescription(texte);
                        } else if(tagname.equalsIgnoreCase("etat")){
                            ressourcedescription.setEtat(texte);
                        } else if(tagname.equalsIgnoreCase("photo")){
                            ressourcedescription.setPhoto(texte);
                        } else if(tagname.equalsIgnoreCase("idressource")){
                            ressourcedescription.setIdressourcedescription(Integer.parseInt(texte));
                        }
                        }catch (Exception e){e.getMessage();}
                        break;
                    default: break;
                }
                eventType = xpp.next();
            }
        }catch (Exception er){er.printStackTrace();}
        return ressourcedescription;
    }

    private static List<Cours> extractCours(Theme theme) {
        List<Cours> liste = new ArrayList<Cours>();
        Cours cours=null;
        try{
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if(tagname.equalsIgnoreCase("cours")){
                            cours = new Cours();
                            cours.setTheme(theme);
                            cours.setRessourcedescription(new Ressourcedescription());
                        }
                        break;
                    case XmlPullParser.TEXT:
                        texte = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(tagname.equalsIgnoreCase("cours")){
                            liste.add(cours);
                        } else if(tagname.equalsIgnoreCase("idressource")){
                            cours.getRessourcedescription().setIdressourcedescription(Integer.parseInt(texte));
                        } else if(tagname.equalsIgnoreCase("idcours")){
                            cours.setIdcours(Integer.parseInt(texte));
                        }else if(tagname.equalsIgnoreCase("titre")){
                            cours.getRessourcedescription().setTitre(texte);
                        } else if(tagname.equalsIgnoreCase("description")){
                            cours.getRessourcedescription().setDescription(texte);
                        } else if(tagname.equalsIgnoreCase("etat")){
                            cours.getRessourcedescription().setEtat(texte);
                        } else if(tagname.equalsIgnoreCase("description")){
                            cours.getRessourcedescription().setDescription(texte);
                        } else if(tagname.equalsIgnoreCase("photo")){
                            cours.getRessourcedescription().setPhoto(texte);
                        } else if(tagname.equalsIgnoreCase("contenu")){
                            cours.setContenu(texte);
                        } else if(tagname.equalsIgnoreCase("blockcours")){
                            return liste;
                        }
                        break;
                    default: break;
                }
                eventType = xpp.next();
            }
        }catch (Exception er){er.printStackTrace();}
        return listeCours;
    }

    private static List<Exercice> extractExercices(Theme theme) {
        List<Exercice> listeExercices = new ArrayList<Exercice>();
        Exercice exercice=null;
        try{
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        if(tagname.equalsIgnoreCase("exercice")){
                            exercice = new Exercice();
                            exercice.setTheme(theme);
                        }
                        break;
                    case XmlPullParser.TEXT:
                        texte = xpp.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        if(tagname.equalsIgnoreCase("exercice")){
                            listeExercices.add(exercice);
                        } else if(tagname.equalsIgnoreCase("idexercice")){
                            exercice.setIdexercice(Integer.parseInt(texte));
                        }
                        else if(tagname.equalsIgnoreCase("sequencequestion")){
                            exercice.setSequencequestion(texte);
                        }else if(tagname.equalsIgnoreCase("tempsreponse")){
                            exercice.setTempsreponse(Integer.parseInt(texte));
                        } else if(tagname.equalsIgnoreCase("score")){
                            exercice.setScore(Integer.parseInt(texte));
                        } else if(tagname.equalsIgnoreCase("blockexercices")){
                            return listeExercices;
                        }
                        break;
                    default: break;
                }
                eventType = xpp.next();
            }
        }catch (Exception er){er.printStackTrace();}
        return listeExercices;
    }

    private void storeData() {

        System.out.println("Store Theme: ");
        for(int i=0;i<listeTheme.size();i++){
            try {
                databaseFactory.getRessourcedescriptionDao().persist(listeTheme.get(i).getRessourcedescription());
                databaseFactory.getThemeDao().persist(listeTheme.get(i));
            }catch (Exception e){System.out.println("Persistence failled ");e.printStackTrace();}
        }

        System.out.println("Store Cours: ");
        for(int i=0;i<listeCours.size();i++){
            try {
                databaseFactory.getRessourcedescriptionDao().persist(listeCours.get(i).getRessourcedescription());
                databaseFactory.getCoursDao().persist(listeCours.get(i));
            }catch (Exception e){System.out.println("Persistence failled ");e.printStackTrace();}
        }

        System.out.println("Store Exercices: ");
        for(int i=0;i<listeExercice.size();i++){
            try {
                databaseFactory.getExerciceDao().persist(listeExercice.get(i));
            }catch (Exception e){System.out.println("Persistence failled ");e.printStackTrace();}
        }

    }

    public void showData() {
        System.out.println(
                "Size Theme: "+databaseFactory.getThemeDao().getAllThemes().size()
                        +"\nSize Cours: "+databaseFactory.getCoursDao().getAllCours().size()
                        +"\nSize Exercice: "+databaseFactory.getExerciceDao().getAllExercices().size()
                        +"\nSize Ressource description: "+databaseFactory.getRessourcedescriptionDao().getAllRessourcedescriptions().size()
        );
        Toast.makeText(getApplicationContext(),
                        "Size Theme: "+databaseFactory.getThemeDao().getAllThemes().size()
                                +"\nSize Cours: "+databaseFactory.getCoursDao().getAllCours().size()
                                +"\nSize Exercice: "+databaseFactory.getExerciceDao().getAllExercices().size()
                                +"\nSize Ressource description: "+databaseFactory.getRessourcedescriptionDao().getAllRessourcedescriptions().size()

                ,Toast.LENGTH_LONG).show();
    }

    private boolean isDatabaseEmpty(){
        return (databaseFactory.getThemeDao().getAllThemes().size() == 0 ||
                databaseFactory.getCoursDao().getAllCours().size() == 0 ||
                databaseFactory.getExerciceDao().getAllExercices().size() == 0 ||
                databaseFactory.getRessourcedescriptionDao().getAllRessourcedescriptions().size()==0);
    }
}
