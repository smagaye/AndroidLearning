package com.smag.androidlearning.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;

import java.util.List;

public class RecycleAdapterExercice extends RecyclerView.Adapter<RecycleAdapterExercice.ViewHolder> {

    private static Context context;
    private  int[] images;
    private List<Exercice> exercices;

    public RecycleAdapterExercice(Context context, int[] images , List<Exercice> exercices) {
        this.context = context;
        this.images = images;
        this.exercices=exercices;
    }

    @Override
    public RecycleAdapterExercice.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_cours, parent, false);
        return new RecycleAdapterExercice.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        holder.titre.setText(exercices.get(position).getTheme().getRessourcedescription().getTitre());
    }


    @Override
    public int getItemCount() {
        return exercices.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public TextView titre;

        public ViewHolder(final View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.id_titre);
            imageView = itemView.findViewById(R.id.id_image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"Image Cours",Toast.LENGTH_LONG).show();
                    //Envoie des donnees vers CoursExerciceContainer
                }
            });
        }
    }
}
