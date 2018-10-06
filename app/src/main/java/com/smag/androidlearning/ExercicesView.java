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
    private Button reponse_indicator_item1;
    private Button reponse_indicator_item2;
    private Button reponse_indicator_item3;
    private Button reponse_indicator_item4;
    private Button reponse_indicator_item5;

    //Les différents TextFields
    private TextView text_part;
    private TextView outputGameVue;

    //Variable pour gestion du score et de la progression
    private List<String> listTexteClaire;
    private  List<String> listTexteCache;
    private int scoreFinal;
    private int progessionGlobale;
    private int nombreTour ;
    private RingProgressBar ringProgressBar;
    private int progress =0;
    private int numeroChoix;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what ==0){
                if(progress <100){
                    progress++;
                    ringProgressBar.setProgress(progress);
                }
                if(progress ==100){
                    Toast.makeText(getApplicationContext() , "nombre De Tour :"+nombreTour , Toast.LENGTH_SHORT);
                    if(nombreTour == 1){
                        playground(3);
                    }else{
                        finishGame.sendEmptyMessage(0);
                    }
                }
            }

        }
    };
    private Handler finishGame = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what == 0){
                Intent intent = new Intent(getApplicationContext() , ResultExercice.class);
                startActivity(intent);
                Toast.makeText(getApplicationContext(), "Game Completed...." , Toast.LENGTH_SHORT).show();
            }
        }
    };
    private Thread thread;

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

       thread =  new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<100;i++ ){
                    try {
                        Thread.sleep(250);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                       // e.printStackTrace();
                    }
                }
            }
        });
       thread.start();
       HashMap<String,List<String>> exo =(HashMap<String,List<String>>)getIntent().getSerializableExtra("exerciceParse");
       listTexteCache =  exo.get("bonneReponse");
       listTexteClaire = exo.get("morceaux");
       playground(0);
    }

    private void playground(int ind) {
        Question question = new Question(listTexteClaire.get(ind), listTexteClaire.get(ind+1),listTexteClaire.get(ind+2),listTexteClaire.get(ind+3),
                listTexteCache.get(ind),listTexteCache.get(ind+1),listTexteCache.get(ind+2)
                );
        nombreTour++;
        text_part.setText(null);
        text_part.setText(question.getTextClaire1()+"...(1)..."+question.getTextClaire2()+"...(2)..."+question.getTextClaire3()+"...(3)..."+question.getTextClaire4());

    }



    @Override
    protected void onStop() {
        super.onStop();
        thread.interrupt();
        Toast.makeText(getApplicationContext() , "Appli Stopped" , Toast.LENGTH_SHORT).show();
    }

    private void initialiserElements() {

        numeroChoix = 0;
        nombreTour =0;

        ringProgressBar = (RingProgressBar) findViewById(R.id.progressBar);
        text_part =(TextView) findViewById(R.id.text_part);
        outputGameVue =(TextView) findViewById(R.id.output_game_vue);
        reponse_indicator_item1 = (Button) findViewById(R.id.reponse_indicator_item1);
        reponse_indicator_item2 = (Button) findViewById(R.id.reponse_indicator_item2);
        reponse_indicator_item3 = (Button) findViewById(R.id.reponse_indicator_item3);
        reponse_indicator_item4 = (Button) findViewById(R.id.reponse_indicator_item4);
        reponse_indicator_item5 = (Button) findViewById(R.id.reponse_indicator_item5);

        reponse_text_item1 = (Button) findViewById(R.id.reponse_text_item1);
        reponse_text_item2 = (Button) findViewById(R.id.reponse_text_item2);
        reponse_text_item3 = (Button) findViewById(R.id.reponse_text_item3);
        reponse_text_item4 = (Button) findViewById(R.id.reponse_text_item4);
        reponse_text_item5 = (Button) findViewById(R.id.reponse_text_item5);

        reponse_text_item1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "valeur :"+ numeroChoix , Toast.LENGTH_SHORT);
               incrementeurNombreReponse(reponse_indicator_item1);
            }
        });
        reponse_text_item2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementeurNombreReponse(reponse_indicator_item2);
            }
        });
        reponse_text_item3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementeurNombreReponse(reponse_indicator_item3);
            }
        });
        reponse_text_item4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementeurNombreReponse(reponse_indicator_item4);
            }
        });
        reponse_text_item5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                incrementeurNombreReponse(reponse_indicator_item5);
            }
        });

    }
    private void incrementeurNombreReponse(Button button){
       // Toast.makeText(getApplicationContext() , String.valueOf(numeroChoix) , Toast.LENGTH_LONG).show();
        if(button.isEnabled()){
            if( ! incrementerNombreReponses()){
                button.setText(String.valueOf(numeroChoix));
                button.setVisibility(View.VISIBLE);
                button.setEnabled(false);
                if(numeroChoix ==3){
                    finishGame.sendEmptyMessage(0);
                }
            }
        }else{
            Toast.makeText(getApplicationContext() , "Déja Choisit...", Toast.LENGTH_SHORT).show();
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
