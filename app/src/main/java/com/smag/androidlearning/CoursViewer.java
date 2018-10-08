package com.smag.androidlearning;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.DragEvent;
import android.view.View;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipeDecor;
import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.smag.androidlearning.beans.Card;
import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.helper.CardFormatter;
import com.smag.androidlearning.service.DatabaseService;

import java.util.List;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Size;

public class CoursViewer extends AppCompatActivity {

    private SwipePlaceHolderView mSwipeView;
    private Context mContext;
    private Cours cours;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.cours_viewer);
        cours =(Cours)getIntent().getSerializableExtra("cours");
        mSwipeView = (SwipePlaceHolderView)findViewById(R.id.swipeView);
        mContext = getApplicationContext();

        mSwipeView.getBuilder().setIsUndoEnabled(true)
                .setDisplayViewCount(1)
                .setSwipeDecor(new SwipeDecor()
                        .setPaddingTop(20)
                        .setRelativeScale(0.01f)
                        .setSwipeInMsgLayoutId(R.layout.swipe_in_msg_view)
                        .setSwipeOutMsgLayoutId(R.layout.swipe_out_msg_view)
        );


            List<Card> cards = Card.split(cours);
            int taille =cards.size();
            for (Card card : cards){
                mSwipeView.addView(new CardFormatter(mContext, card, mSwipeView,taille));
            }

        findViewById(R.id.btn_precedent).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                 mSwipeView.doSwipe(false);
                 mSwipeView.undoLastSwipe();
            }
        });

        findViewById(R.id.btn_suivant).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mSwipeView.doSwipe(true);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if(mSwipeView.getAllResolvers().size()==0){
            cours.getRessourcedescription().setEtat("1");
            onBackPressed();
        }
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
