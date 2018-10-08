package com.smag.androidlearning;

import android.app.Activity;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Shape;
import nl.dionsegijn.konfetti.models.Size;

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
        final KonfettiView konfettiView = findViewById(R.id.konfettiView);
        konfettiView.build()
                .addColors(Color.YELLOW, Color.GREEN, Color.MAGENTA,Color.BLUE)
                .setDirection(-640.0, -1024)
                .setSpeed(3f, 5f)
                //.setPosition(konfettiView.getX()+130,konfettiView.getY()+140)
                .setFadeOutEnabled(true)
                .setTimeToLive(12000L)
                .addShapes(Shape.RECT, Shape.CIRCLE)
                .addSizes(new Size(10, 2f))
               // .setPosition(-0f, 0f, -0f, -0f)
                .streamFor(900, 25000L);

    }
}
