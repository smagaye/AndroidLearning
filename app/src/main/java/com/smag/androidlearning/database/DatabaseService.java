package com.smag.androidlearning.database;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.beans.Ressourcedescription;
import com.smag.androidlearning.beans.Theme;

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
    private static List<Theme> listeTheme = new ArrayList<Theme>();

    public class DatabaseServiceBinder extends Binder{
        public DatabaseService getService(){
            return DatabaseService.this;
        }
    }
    private IBinder iBinder =new DatabaseServiceBinder();

    @Override
    public void onCreate() {
        super.onCreate();
        try {
            startDatabaseConfiguration(getAssets().open(getResources().getString(R.string.datafile)));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return iBinder;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static void startDatabaseConfiguration(InputStream inputStream) {
        if(xpp==null) intialize(inputStream);
        storeData();
    }

    private static void intialize(InputStream inputStream){
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

    private static List<Theme> extractThemes() {
        Theme theme = null;
        int idtheme=0;
        try {
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_DOCUMENT:
                        System.out.println("Start document");
                        break;
                    case XmlPullParser.START_TAG:
                        if (tagname.equalsIgnoreCase("theme")) {
                            theme = new Theme();
                            theme.setIdtheme(idtheme+=1);
                            theme.setRessourcedescription(extractRessourceDescription());
                            theme.setListeCours(extractCours(theme));
                            theme.setListeExercices(extractExercices(theme));
                            listeTheme.add(theme);
                        }
                        break;
                    case XmlPullParser.END_DOCUMENT:
                        System.out.println("End Document");
                        break;
                    default:
                        break;
                }
                eventType = xpp.next();
            }
        } catch (Exception er) {
            er.printStackTrace();
        }
        return listeTheme;
    }

    private static Ressourcedescription extractRessourceDescription() {
        Ressourcedescription ressourcedescription =new Ressourcedescription();;
        try{
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        // System.out.println("Start tag "+tagname);
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
        List<Cours> listeCours = new ArrayList<Cours>();
        Cours cours=null;
        try{
            while (eventType != XmlPullParser.END_DOCUMENT) {
                String tagname = xpp.getName();
                switch (eventType) {
                    case XmlPullParser.START_TAG:
                        // System.out.println("Start tag "+tagname);
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
                            listeCours.add(cours);
                        } else if(tagname.equalsIgnoreCase("idressource")){
                            cours.getRessourcedescription().setIdressourcedescription(Integer.parseInt(texte));
                        }
                        else if(tagname.equalsIgnoreCase("idcours")){
                            cours.setIdcours(Integer.parseInt(texte));
                        }else if(tagname.equalsIgnoreCase("titre")){
                            cours.getRessourcedescription().setTitre(texte);
                        } else if(tagname.equalsIgnoreCase("description")){
                            cours.getRessourcedescription().setDescription(texte);
                        } else if(tagname.equalsIgnoreCase("etat")){
                            cours.getRessourcedescription().setEtat(texte);
                        } else if(tagname.equalsIgnoreCase("description")){
                            cours.getRessourcedescription().setPhoto(texte);
                        } else if(tagname.equalsIgnoreCase("photo")){
                            cours.getRessourcedescription().setPhoto(texte);
                        } else if(tagname.equalsIgnoreCase("contenu")){
                            cours.setContenu(texte);
                        } else if(tagname.equalsIgnoreCase("blockcours")){
                            return listeCours;
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
                        //System.out.println("Start tag "+tagname);
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

    private static void storeData() {
    }

    public void showData() {
        for(int i=0;i<extractThemes().size();i++){
            Theme theme = extractThemes().get(i);
            System.out.println("\t\t\t--\t\t\t\t-- \n Debut" +theme);
            System.out.println(theme.getListeCours());
            System.out.println(theme.getListeExercices()+"\t\t\t--\t\t\t\t-- Fin");
        }
    }

}
