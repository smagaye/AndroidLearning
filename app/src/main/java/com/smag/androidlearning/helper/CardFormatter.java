package com.smag.androidlearning.helper;

import android.content.Context;
import android.util.Log;
import android.widget.TextView;

import com.mindorks.placeholderview.SwipePlaceHolderView;
import com.mindorks.placeholderview.annotations.Layout;
import com.mindorks.placeholderview.annotations.Resolve;
import com.mindorks.placeholderview.annotations.View;
import com.mindorks.placeholderview.annotations.swipe.SwipeCancelState;
import com.mindorks.placeholderview.annotations.swipe.SwipeIn;
import com.mindorks.placeholderview.annotations.swipe.SwipeInState;
import com.mindorks.placeholderview.annotations.swipe.SwipeOut;
import com.mindorks.placeholderview.annotations.swipe.SwipeOutState;
import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Card;
import com.smag.androidlearning.beans.Cours;

@Layout(R.layout.item_cours_card_view)
public class CardFormatter {

    //@View(R.id.profileImageView)
  //  private ImageView profileImageView;

    @View(R.id.contenu)
    private TextView contenu;

    @View(R.id.titre)
    private TextView titre;

    @View(R.id.index)
    private TextView index;

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
    }

    @SwipeIn
    private void onSwipeIn(){
        Log.d("EVENT", "onSwipedIn");
    }

    @SwipeInState
    private void onSwipeInState(){
        Log.d("EVENT", "onSwipeInState");
    }

    @SwipeOutState
    private void onSwipeOutState(){
        Log.d("EVENT", "onSwipeOutState");
    }
}