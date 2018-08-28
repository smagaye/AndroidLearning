package com.smag.androidlearning.helper;

import android.content.res.AssetManager;
import android.content.res.Resources;
import android.util.Log;

import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.beans.Ressourcedescription;
import com.smag.androidlearning.beans.Theme;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class XmlManager {

    public static Document getDocument(String location) {
        Document doc = null;
        try {

            File inputFile = new File(location);
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return doc;
    }

    public static List<Element> extractElement(Document doc, String tag) {
        List<Element> listElement = new ArrayList<Element>();
        NodeList nList = doc.getElementsByTagName(tag);
        System.out.println("----------------------------");

        for (int temp = 0; temp < nList.getLength(); temp++) {
            Node nNode = nList.item(temp);
            if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                Element element = (Element) nNode;
                listElement.add(element);
            }
        }
        return listElement;
    }

    public static Element getFirstTagName(Element element, String tagname) {
        return ((Element) element.getElementsByTagName(tagname).item(0));
    }

    public static Theme extraireTheme(Element element) {
        Theme theme = null;
        try {
            Ressourcedescription res = new Ressourcedescription();
            res.setIdressourcedescription(Integer.parseInt(XmlManager.getFirstTagName(element, "ressourcedescription").getAttribute("idressource")));
            res.setTitre(XmlManager.getFirstTagName(element, "titre").getTextContent());
            res.setDescription(XmlManager.getFirstTagName(element, "description").getTextContent());
            res.setPhoto(XmlManager.getFirstTagName(element, "photo").getTextContent());
            res.setEtat(XmlManager.getFirstTagName(element, "etat").getTextContent());

            theme = new Theme();
            theme.setIdtheme(Integer.parseInt(element.getAttribute("idtheme")));
            theme.setRessourcedescription(res);

        } catch (Exception Exception) {
            System.out.println("Le parametre effectif ne referencie pas une structure de theme valide");
        }
        return theme;
    }

    public static Cours extraireCours(Element element) {
        Cours cours = null;
        try {
            Ressourcedescription res = new Ressourcedescription();
            res.setIdressourcedescription(Integer.parseInt(XmlManager.getFirstTagName(element, "ressourcedescription").getAttribute("idressource")));
            res.setTitre(XmlManager.getFirstTagName(element, "titre").getTextContent());
            res.setDescription(XmlManager.getFirstTagName(element, "description").getTextContent());
            res.setPhoto(XmlManager.getFirstTagName(element, "photo").getTextContent());
            res.setEtat(XmlManager.getFirstTagName(element, "etat").getTextContent());

            cours = new Cours();
            cours.setIdcours(Integer.parseInt(element.getAttribute("idcours")));
            cours.setDatedernierelecture(new Date());
            cours.setRessourcedescription(res);

        } catch (Exception Exception) {
            System.out.println("Le parametre effectif ne referencie pas une structure de cours valide");
            Exception.printStackTrace();
        }
        return cours;
    }

    public static Exercice extraireExercice(Element element) {
        Exercice exercice = null;
        try {
            exercice = new Exercice();
            exercice.setIdexercice(Integer.parseInt(element.getAttribute("idexercice")));
            exercice.setScore(Integer.parseInt(XmlManager.getFirstTagName(element, "score").getTextContent()));
            exercice.setSequencequestion(XmlManager.getFirstTagName(element, "sequencequestion").getTextContent());
            exercice.setTempsreponse(Integer.parseInt(XmlManager.getFirstTagName(element, "tempsreponse").getTextContent()));

        } catch (Exception Exception) {
            System.out.println("Le parametre effectif ne referencie pas une structure d'exercice valide");
            Exception.printStackTrace();
        }
        return exercice;
    }

    public static Cours getCours(Theme theme, Element element) {
        Cours cours = extraireCours(element);
        cours.setTheme(theme);
        return cours;
    }

    public static Exercice getExercice(Theme theme, Element element) {
        Exercice exercice = extraireExercice(element);
        exercice.setTheme(theme);
        return exercice;
    }

    public static void runXmlFile(Document doc){
        List<Element> themeElements = extractElement(doc,"theme");
        for(Element elementTheme : themeElements){
            Theme theme =extraireTheme(elementTheme);
            System.out.println("\n\n\n-----------------------------------------------------------");
            System.out.println("Theme Item\n\t Cours : "+theme.toString());
            
           NodeList blockcoursItems= getFirstTagName(elementTheme,"blockcours").getElementsByTagName("cours");
           for(int i=0 ; i<blockcoursItems.getLength();i++){
               Element elementCours =(Element) blockcoursItems.item(i);
               Cours cours =getCours(theme, elementCours);
               System.out.println("Cours Item\n\t Cours : "+cours.toString());
           }
           
           System.out.println("\n++++++++++++++++++++++++++++++++++++++++++++++++++++++++++++\n");
           NodeList blockexerciceItems= getFirstTagName(elementTheme,"blockexercices").getElementsByTagName("exercice");
           for(int i=0 ; i<blockexerciceItems.getLength();i++){
               Element elementExercice =(Element) blockexerciceItems.item(i);
               Exercice exercice =getExercice(theme, elementExercice);
               System.out.println("Exercice Item\n\tExercice : "+exercice.toString());
           }
           
           System.out.println("-----------------------------------------------------------\n\n\n");
  
        }
    }
}
