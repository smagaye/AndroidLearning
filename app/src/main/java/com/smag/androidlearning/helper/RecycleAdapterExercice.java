package com.smag.androidlearning.helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.smag.androidlearning.ExercicesView;
import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.helper.ExerciceHelper;
import com.smag.androidlearning.database.DatabaseFactory;

import java.util.List;

public class RecycleAdapterExercice extends RecyclerView.Adapter<RecycleAdapterExercice.ViewHolder> {

    private static Context context;
    private  int[] images;
    private List<Exercice> exercices;
    static  List<Cours> cours;

    public RecycleAdapterExercice(Context context, int[] images , List<Exercice> exercices) {
        this.context = context;
        this.images = images;
        this.exercices=exercices;
        DatabaseFactory databaseFactory= DatabaseFactory.getAppDatabase(context);
        cours = databaseFactory.getCoursDao().findByTheme(exercices.get(0).getTheme().getRessourcedescription().getTitre());
    }

    @Override
    public RecycleAdapterExercice.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_exercice, parent, false);
        return new RecycleAdapterExercice.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        int indice =position+1;
        holder.titre.setText("Niveau de difficulté : "+indice);
        holder.ratingBar.setRating(indice);
        holder.ratingBar.setIsIndicator(true);
    }


    @Override
    public int getItemCount() {
        return exercices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView titre;
        public RatingBar ratingBar;
        public ViewHolder(final View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.id_titre);
            imageView = itemView.findViewById(R.id.id_image);
            ratingBar =itemView.findViewById(R.id.ratingBar);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(context,ExercicesView.class);
                    intent.putExtra("exerciceParse", ExerciceHelper.extractExercise(cours.get((int)ratingBar.getRating()),context));
                    context.startActivity(intent);

                }
            });
        }
    }
}
