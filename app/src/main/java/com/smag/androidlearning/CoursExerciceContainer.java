package com.smag.androidlearning;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.smag.androidlearning.beans.Cours;

import java.util.List;

public class CoursExerciceContainer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cours_exercice_container);
        List<Cours> list = (List<Cours>) getIntent().getSerializableExtra("listeCours");
        System.out.println(list);
    }
}
