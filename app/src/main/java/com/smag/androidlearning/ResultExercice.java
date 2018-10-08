package com.smag.androidlearning;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

public class ResultExercice extends Activity {
    private ImageButton returnHome;
    private ImageButton replay;
    private ImageButton return_game;
    private TextView score_vue;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result_exercice);
        returnHome = (ImageButton) findViewById(R.id.return_home);
        return_game = (ImageButton) findViewById(R.id.return_game);
        replay = (ImageButton) findViewById(R.id.replay);
        score_vue = (TextView) findViewById(R.id.score_vue);
        int score = (int) getIntent().getSerializableExtra("score");
        score_vue.setText(score+"/3");
    }
}
