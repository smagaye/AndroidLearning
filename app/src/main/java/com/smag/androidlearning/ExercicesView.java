package com.smag.androidlearning;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.smag.androidlearning.helper.Question;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import io.netopen.hotbitmapgg.library.view.RingProgressBar;

public class ExercicesView extends AppCompatActivity {

    //Les Buttons des différentes réponses...
    private Button reponse_text_item1;
    private Button reponse_text_item2;
    private Button reponse_text_item3;
    private Button reponse_text_item4;
    private Button reponse_text_item5;

    //Les différents choix éffectués
    private TextView reponse_indicator_item1;
    private TextView reponse_indicator_item2;
    private TextView reponse_indicator_item3;
    private TextView reponse_indicator_item4;
    private TextView reponse_indicator_item5;
    //Les différents TextFields
    private TextView text_part;
    private TextView outputGameVue;

    //Variable pour gestion du score et de la progression
    private Thread thread;
    private List<String> listTexteClaire;
    private  List<String> listTexteCache;
    private int scoreFinal;
    private int progessionGlobale;
    private int nombreTour ;
    private RingProgressBar ringProgressBar;
    private int progress;
    private Question questionGeneral;
    private int numeroChoix;
    private int progressionTime = 200;
    private List<Button> bouttonIndicateur;
    List<TextView> textViewsIndicator;
    private int nombrePoint;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what ==0){
                if(progress <100){
                    progress++;
                    ringProgressBar.setProgress(progress);
                }
                if(progress ==100){
                    if(nombreTour == 1){
                        thread.interrupt();
                        thread.interrupt();
                        ringProgressBar.setProgress(100);
                        for (TextView textView : textViewsIndicator) textView.setText("?");
                        finishGame.sendEmptyMessage(0);
                        //playground(3);
                        //progessBarGestion(progressionTime);
                    //    Toast.makeText(getApplicationContext() , bouttonIndicateur.size() , Toast.LENGTH_LONG).show();
                        //for (TextView textView : textViewsIndicator) textView.setText("?");
                        //outputGameVue.setText(2/2);
                    }else{
                        thread.interrupt();
                        finishGame.sendEmptyMessage(0);
                    }
                }
            }
            else if(msg.what == 1){
                if(nombreTour ==1){
                    //Bug à corriger plus tard si le temps le permet
                    //playground(3);
                    //progessBarGestion(progressionTime);
                     nombrePoint = 0;
                    String reponse1 = bouttonIndicateur.get(0).getText().toString();
                    String reponse2 = bouttonIndicateur.get(1).getText().toString();
                    String reponse3 = bouttonIndicateur.get(2).getText().toString();
                    if(reponse1.equals(questionGeneral.getTextCache1())) nombrePoint++;
                    if(reponse2.equals(questionGeneral.getTextCache2())) nombrePoint++;
                    if(reponse3.equals(questionGeneral.getTextCache3())) nombrePoint++;
                    thread.interrupt();
                    ringProgressBar.setProgress(100);
                    for (TextView textView : textViewsIndicator) textView.setText("?");
                    finishGame.sendEmptyMessage(0);
                    Toast.makeText(getApplicationContext() , "nombre de Point "+nombrePoint , Toast.LENGTH_LONG).show();
                    //outputGameVue.setText(2/2);
                }else{
                    finishGame.sendEmptyMessage(0);
                }
            }
        }
    };

    private Handler finishGame = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                Intent intent = new Intent(getApplicationContext() , ResultExercice.class);
                intent.putExtra("score" , nombrePoint);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Game Completed...." , Toast.LENGTH_SHORT).show();
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices_view);
        initialiserElements();
        ringProgressBar.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                Toast.makeText(getApplicationContext() , "Completed" , Toast.LENGTH_SHORT);
            }
        });
       progessBarGestion(progressionTime);
       HashMap<String,List<String>> exo =(HashMap<String,List<String>>)getIntent().getSerializableExtra("exerciceParse");
       listTexteCache =  exo.get("bonneReponse");
       listTexteClaire = exo.get("morceaux");
       playground(0);
    }

    private void playground(int ind) {
        System.out.println(listTexteCache);
        System.out.println(listTexteClaire);
        //reinitialiserVoyants();
        Question question = new Question(listTexteClaire.get(ind), listTexteClaire.get(ind+1),listTexteClaire.get(ind+2),listTexteClaire.get(ind+3),
                listTexteCache.get(ind),listTexteCache.get(ind+1),listTexteCache.get(ind+2)
                );
        question.setBruit1(listTexteClaire.get(listTexteClaire.size()-1));
        question.setBruit2(listTexteClaire.get(listTexteClaire.size()-2));
        questionGeneral =question;
        nombreTour++;
        text_part.setText(null);
        text_part.setText(question.getTextClaire1()+"...(1)..."+question.getTextClaire2()+"...(2)..."+question.getTextClaire3()+"...(3)..."+question.getTextClaire4());
       List<String> listRéponses = Arrays.asList(
           question.getTextCache1(),question.getTextCache2(),question.getTextCache3(), question.getBruit1() , question.getBruit2()
        );
        Collections.shuffle(listRéponses);
       reponse_text_item1.setText(listRéponses.get(0));
       reponse_text_item2.setText(listRéponses.get(1));
       reponse_text_item3.setText(listRéponses.get(2));
       reponse_text_item4.setText(listRéponses.get(3));
       reponse_text_item5.setText(listRéponses.get(4));

    }

    private void progessBarGestion(final int progressionTime){
        progress =0;
        thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<100;i++ ){
                    try {
                        Thread.sleep(progressionTime);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        // e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }


    @Override
    protected void onStop() {
        super.onStop();
        thread.interrupt();
        Toast.makeText(getApplicationContext() , "Appli Stopped" , Toast.LENGTH_SHORT).show();
    }

    private void initialiserElements() {
        bouttonIndicateur = new ArrayList<>();
        textViewsIndicator = new ArrayList<>();
        numeroChoix = 0;
        nombreTour =0;

        ringProgressBar = (RingProgressBar) findViewById(R.id.progressBar);
        text_part =(TextView) findViewById(R.id.text_part);
        outputGameVue =(TextView) findViewById(R.id.output_game_vue);
        reponse_text_item1 = (Button) findViewById(R.id.reponse_text_item1);
        reponse_text_item2 = (Button) findViewById(R.id.reponse_text_item2);
        reponse_text_item3 = (Button) findViewById(R.id.reponse_text_item3);
        reponse_text_item4 = (Button) findViewById(R.id.reponse_text_item4);
        reponse_text_item5 = (Button) findViewById(R.id.reponse_text_item5);
        reponse_indicator_item1 = (TextView) findViewById(R.id.reponse_indicator_item1);
        reponse_indicator_item2 = (TextView) findViewById(R.id.reponse_indicator_item2);
        reponse_indicator_item3 = (TextView) findViewById(R.id.reponse_indicator_item3);
        reponse_indicator_item4 = (TextView) findViewById(R.id.reponse_indicator_item4);
        reponse_indicator_item5 = (TextView) findViewById(R.id.reponse_indicator_item5);
        reponse_text_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombreTour ==1)incrementeurNombreReponses(reponse_indicator_item1 ,reponse_text_item1);
              }
        });
        reponse_text_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombreTour ==1)incrementeurNombreReponses(reponse_indicator_item2 ,reponse_text_item2);
            }
        });
        reponse_text_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombreTour ==1)incrementeurNombreReponses(reponse_indicator_item3 , reponse_text_item3);
                }
        });
        reponse_text_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombreTour ==1)incrementeurNombreReponses(reponse_indicator_item4 , reponse_text_item4);
            }
        });
        reponse_text_item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(nombreTour ==1)incrementeurNombreReponses(reponse_indicator_item5 , reponse_text_item5);
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        handler.removeMessages(0);
        finishGame.removeMessages(0);
    }

    private void incrementeurNombreReponses(TextView textView , Button button){
        if(textView.getText().equals("?")){
            if(!incrementerNombreReponses()){
                textView.setText(String.valueOf(numeroChoix));
                textViewsIndicator.add(textView);
                bouttonIndicateur.add(button);
                if (numeroChoix == 3) handler.sendEmptyMessage(1);
            }
        }
    }

    private boolean incrementerNombreReponses() {
        if (numeroChoix == 3){
         return true;
        }
        numeroChoix++;
        return false;
    }

}
