package com.smag.androidlearning.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.smag.androidlearning.R;
import com.smag.androidlearning.beans.Exercice;

import java.util.List;

public class RecycleAdapterSettings extends RecyclerView.Adapter<RecycleAdapterSettings.ViewHolder> {

    private static Context context;
    private  int[] images;
    private List<Exercice> exercices;

    public RecycleAdapterSettings(Context context) {
        this.context = context;
    }

    @Override
    public RecycleAdapterSettings.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_setting, parent, false);
        return new RecycleAdapterSettings.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

    }


    @Override
    public int getItemCount() {
        return 1;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ViewHolder(final View itemView) {
            super(itemView);
        }
    }
}
