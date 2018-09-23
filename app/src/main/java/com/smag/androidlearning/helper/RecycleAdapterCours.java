package com.smag.androidlearning.helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smag.androidlearning.CoursExerciceContainer;
import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.beans.Theme;
import com.smag.androidlearning.database.DatabaseFactory;

import java.io.Serializable;
import java.util.List;

public class RecycleAdapterCours extends RecyclerView.Adapter<RecycleAdapterCours.ViewHolder> {

    private static Context context;
    private  int[] images;
    private List<Cours> cours;

    public RecycleAdapterCours(Context context, int[] images , List<Cours> cours) {
        this.context = context;
        this.images = images;
        this.cours=cours;
    }

    @Override
    public RecycleAdapterCours.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_cours, parent, false);
        return new RecycleAdapterCours.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
       if(cours.get(position).getRessourcedescription().getEtat().equals("0"))holder.icone.setImageResource(R.drawable.no_compris_icon) ;else holder.icone.setImageResource(R.drawable.valide_icon);
        holder.titre.setText(cours.get(position).getRessourcedescription().getTitre());
        holder.description.setText(cours.get(position).getRessourcedescription().getDescription());
    }


    @Override
    public int getItemCount() {
        return cours.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ImageView icone;
        public TextView titre;
        public TextView description;

        public ViewHolder(final View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.id_titre);
            description = itemView.findViewById(R.id.id_description);
            imageView = itemView.findViewById(R.id.id_image);
            icone = itemView.findViewById(R.id.valide_icon);
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
