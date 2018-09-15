package com.smag.androidlearning.helper;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.smag.androidlearning.R;

public class RecycleAdapterHome extends RecyclerView.Adapter<RecycleAdapterHome.ViewHolder> {

    private Context context;
    private  int[] images;

    public RecycleAdapterHome(Context context, int[] images) {
        this.context = context;
        this.images = images;
    }

    @Override
    public RecycleAdapterHome.ViewHolder onCreateViewHolder(final ViewGroup parent, final int viewType) {
        final View view = LayoutInflater.from(context).inflate(R.layout.item_theme, parent, false);
        return new RecycleAdapterHome.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.imageView.setImageResource(images[position]);
    }


    @Override
    public int getItemCount() {
        return images.length;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {

        public ImageView imageView;

        public ViewHolder(final View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ressource_image);
            imageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Toast.makeText(itemView.getContext(),"beuss",Toast.LENGTH_LONG).show();
                }
            });
        }
    }
}
