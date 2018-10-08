package com.smag.androidlearning.helper;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.smag.androidlearning.LuConfettis;
import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Card;
import com.smag.androidlearning.beans.Cours;

import nl.dionsegijn.konfetti.KonfettiView;
import nl.dionsegijn.konfetti.models.Size;

@Layout(R.layout.item_cours_card_view)
public class CardFormatter extends AppCompatActivity {

    //@View(R.id.profileImageView)
  //  private ImageView profileImageView;

    @View(R.id.contenu)
    private TextView contenu;

    @View(R.id.titre)
    private TextView titre;

    @View(R.id.index)
    private TextView index;

    @View(R.id.konfettiView)
    private KonfettiView konfettiView;

    private Card mCard;
    private Context mContext;
    private SwipePlaceHolderView mSwipeView;
    private String mMaxPosition;

    public CardFormatter(Context context, Card card, SwipePlaceHolderView swipeView,int maxPosition) {
        mContext = context;
        mCard = card;
        mSwipeView = swipeView;
        mMaxPosition = Integer.toString(maxPosition);
    }

    @Resolve
    private void onResolved(){
        titre.setText(mCard.getCours().getRessourcedescription().getTitre());
        contenu.setText(mCard.getContenu());
        index.setText(mCard.getPosition()+ " / " +mMaxPosition);
    }

    @SwipeOut
    private void onSwipedOut(){
        Log.d("EVENT", "onSwipedOut");
        mSwipeView.addView(this);
    }

    @SwipeCancelState
    private void onSwipeCancelState(){
        Log.d("EVENT", "onSwipeCancelState");
      //  if(mSwipeView.getAllResolvers().size()==1) startConfetti();
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
        if(mSwipeView.getAllResolvers().size()==1) startConfetti();

    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
       // if(mSwipeView.getAllResolvers().size()==1) startConfetti();
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
      //  if(mSwipeView.getAllResolvers().size()==1) startConfetti();
    }

    private void startConfetti() {
        mContext.startActivity(new Intent(mContext,LuConfettis.class));
    }
}