package com.example.einrick.layout;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lenovo on 09/04/2018.
 */

public class HighScoreHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView name;
    public TextView score;


    public HighScoreHolder(View itemView) {
        super(itemView);

        icon = (ImageView) itemView.findViewById(R.id.hsicon);
        name = (TextView) itemView.findViewById(R.id.hsname);
        score = (TextView) itemView.findViewById(R.id.hsscore);
    }
}
