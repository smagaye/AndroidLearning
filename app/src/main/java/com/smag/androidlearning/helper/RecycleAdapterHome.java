package com.smag.androidlearning.helper;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smag.androidlearning.CoursExerciceContainer;
import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Cours;
import com.smag.androidlearning.beans.Theme;
import com.smag.androidlearning.database.DatabaseFactory;
import com.smag.androidlearning.service.DatabaseService;

import java.io.Serializable;
import java.util.List;

public class RecycleAdapterHome extends RecyclerView.Adapter<RecycleAdapterHome.ViewHolder> {

    private static Context context;
    private  int[] images;
    private List<Theme> themes;

    public RecycleAdapterHome(Context context, int[] images ,List<Theme> themes) {
        this.context = context;
        this.images = images;
        this.themes=themes;
    }

    @Override
    public RecycleAdapterHome.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_theme, parent, false);
        return new RecycleAdapterHome.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
        holder.titre.setText(themes.get(position).getRessourcedescription().getTitre());

    }


    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;
        public ImageView plus;
        public TextView titre;
        public TextView noteCours;
        public TextView noteQuizz;

        public ViewHolder(final View itemView) {
            super(itemView);
            titre = itemView.findViewById(R.id.id_theme);
            noteCours = itemView.findViewById(R.id.id_note_cours);
            imageView = itemView.findViewById(R.id.ressource_image);
            plus = itemView.findViewById(R.id.plus);
            noteQuizz = itemView.findViewById(R.id.id_note_quizz);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    DatabaseFactory dbf =DatabaseFactory.getAppDatabase(itemView.getContext());
                    List<Cours>  lc = dbf.getCoursDao().findByTheme(titre.getText().toString());
                    //Envoie des donnees vers CoursExerciceContainer
                    Intent intent = new Intent(itemView.getContext(), CoursExerciceContainer.class);
                    intent.putExtra("listeCours",(Serializable) lc);
                    context.startActivity(intent);
                }
            });
            plus.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"Afficher Cours plus",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
