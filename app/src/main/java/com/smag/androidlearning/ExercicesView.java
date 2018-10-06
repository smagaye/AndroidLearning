package com.smag.androidlearning;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

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


    //Variabe pour gestion du score et de la progression
    private int progessionGlobale;
    private RingProgressBar ringProgressBar;
    private int progress =0;
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            if(msg.what ==0){
                if(progress <100){
                    progress++;
                    ringProgressBar.setProgress(progress);
                }
                if(progress ==100){
                    finishGame.sendEmptyMessage(0);
                }
            }
        }
    };
    private int numeroChoix;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices_view);
        initialiserElements();
        ringProgressBar = (RingProgressBar) findViewById(R.id.progressBar);

        ringProgressBar.setOnProgressListener(new RingProgressBar.OnProgressListener() {
            @Override
            public void progressToComplete() {
                Toast.makeText(getApplicationContext() , "Completed" , Toast.LENGTH_SHORT);
            }
        });

        new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i =0; i<100;i++ ){
                    try {
                        Thread.sleep(250);
                        handler.sendEmptyMessage(0);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
       // HashMap<String,List<String>> exo =(HashMap<String,List<String>>)getIntent().getSerializableExtra("exerciceParse");
   //     System.out.println(exo.get("bonneReponse"));
     //   System.out.println(exo.get("morceaux"));
    }

    private void initialiserElements() {

        numeroChoix = 0;
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
            Toast.makeText(getApplicationContext() , "Déja Choisit...", Toast.LENGTH_SHORT);
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
