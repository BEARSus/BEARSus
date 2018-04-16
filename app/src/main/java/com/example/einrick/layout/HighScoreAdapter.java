package com.example.einrick.layout;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by Lenovo on 09/04/2018.
 */

public class HighScoreAdapter extends RecyclerView.Adapter<HighScoreHolder>{
    private ArrayList<HighScoreItem> list;

    public HighScoreAdapter(ArrayList<HighScoreItem> list){
        this.list = list;
    }

    @Override
    public HighScoreHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.hs_item, parent, false);

        return new HighScoreHolder(itemView);
    }

    @Override
    public void onBindViewHolder(HighScoreHolder holder, int position) {
        HighScoreItem item = list.get(position);
        holder.name.setText(item.getName());
        holder.score.setText(Integer.toString(item.getScore()));
        holder.icon.setImageResource(item.getIcon());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
