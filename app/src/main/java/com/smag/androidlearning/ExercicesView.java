package com.smag.androidlearning;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

public class ExercicesView extends AppCompatActivity {

    //Les Buttons des différentes réponses...
    private Button reponse_text_item1;
    private Button reponse_text_item2;
    private Button reponse_text_item3;
    private Button reponse_text_item4;
    private Button reponse_text_item5;

    //Les différents choix éffectuées
    private Button reponse_indicator_item1;
    private Button reponse_indicator_item2;
    private Button reponse_indicator_item3;
    private Button reponse_indicator_item4;
    private Button reponse_indicator_item5;


    //Variabe pour gestion du score et de la progression
    private ProgressBar progressBar;
    private Handler handler = new Handler();
    private int numeroChoix;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exercices_view);
        initialiserElements();
        initialiserProgressBar();

        HashMap<String,List<String>> exo =(HashMap<String,List<String>>)getIntent().getSerializableExtra("exerciceParse");
        System.out.println(exo.get("bonneReponse"));
        System.out.println(exo.get("morceaux"));
    }

    private void initialiserElements() {

        numeroChoix = 0;
        progressBar = (ProgressBar) findViewById(R.id.progress_time);

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
        Toast.makeText(getApplicationContext() , String.valueOf(numeroChoix) , Toast.LENGTH_LONG);
        if(button.isEnabled()){
            button.setVisibility(View.VISIBLE);
            button.setEnabled(false);
            if( ! incrementerNombreReponses()){
                button.setText(String.valueOf(numeroChoix));
            }
        }else{
            if(numeroChoix < 3){
                numeroChoix--;
                button.setEnabled(false);
                button.setVisibility(View.INVISIBLE);
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
    private void initialiserProgressBar(){
        Log.i("verife", "Works");
        new Thread(new Runnable() {
            int i= 0;
            int progressStatus = 0;

            @Override
            public void run() {
                while (progressStatus < 100){
                    progressStatus += doWork();
                    try {
                        Thread.sleep(500);
                    }catch (InterruptedException e){
                        e.printStackTrace();
                    }
                    //Update the progress bar
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            progressBar.setProgress(progressStatus);
                            i++;
                        }
                    });
                }
            }

            private int doWork() {
                return i * 3;
            }
        });
    }
}
