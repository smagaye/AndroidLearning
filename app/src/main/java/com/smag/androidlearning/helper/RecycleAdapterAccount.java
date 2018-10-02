package com.smag.androidlearning.helper;

import android.content.Context;
import android.content.Intent;
import android.content.res.Resources;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.smag.androidlearning.EditProfil;
import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Exercice;
import com.smag.androidlearning.database.DatabaseFactory;

import java.util.List;

public class RecycleAdapterAccount extends RecyclerView.Adapter<RecycleAdapterAccount.ViewHolder> {

    private static Context context;
    private  int[] images;
    private List<Exercice> exercices;

    public RecycleAdapterAccount(Context context) {
        this.context = context;
    }

    @Override
    public RecycleAdapterAccount.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_user, parent, false);
        return new RecycleAdapterAccount.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageProfil.setImageResource(R.drawable.user_default_icon);
        holder.imageTheme.setImageResource(R.drawable.theme_image);
        holder.imageCours.setImageResource(R.drawable.classeur);
        holder.imageQuizz.setImageResource(R.drawable.quizz_image);
        holder.imageStat.setImageResource(R.drawable.barre_chart);
        holder.prenom.setText(R.string.prenom);
        holder.nom.setText(R.string.nom);
        holder.statut.setText(R.string.statut);

        DatabaseFactory databaseFactory = DatabaseFactory.getAppDatabase(context);
        /*

        holder.theme_ouvert;
        holder.cours_lu;
        holder.note_quizz;

        */
    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageProfil;
        ImageView imageTheme;
        ImageView imageCours;
        ImageView imageQuizz;
        ImageView imageStat;

        TextView prenom;
        TextView nom;
        TextView statut;
        TextView theme_ouvert;
        TextView cours_lu;
        TextView note_quizz;

        Button editButton ;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageProfil = itemView.findViewById(R.id.id_profile);
            imageTheme = itemView.findViewById(R.id.id_img_theme);
            imageCours = itemView.findViewById(R.id.id_img_cours);
            imageQuizz = itemView.findViewById(R.id.id_img_quizz);
            imageStat = itemView.findViewById(R.id.id_stat);
            prenom = itemView.findViewById(R.id.id_prenom);
            nom = itemView.findViewById(R.id.id_nom);
            statut = itemView.findViewById(R.id.id_statut);
            theme_ouvert = itemView.findViewById(R.id.id_theme_ouvert);
            cours_lu = itemView.findViewById(R.id.id_note_cours);
            note_quizz =itemView.findViewById(R.id.id_note_quizz);

            editButton = itemView.findViewById(R.id.id_btn_editer);

            editButton.setOnClickListener(new View.OnClickListener() {
                public void onClick(View v) {
                    context.startActivity( new Intent( itemView.getContext(), EditProfil.class));
                }
            });
        }
    }
}
