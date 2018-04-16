package com.example.einrick.layout;

import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Lenovo on 09/04/2018.
 */

public class RemoveUserHolder extends RecyclerView.ViewHolder {
    public ImageView icon;
    public TextView name;
    public ImageButton removeBtn;

    public RemoveUserHolder(View itemView) {
        super(itemView);

        icon = (ImageView) itemView.findViewById(R.id.ru_icon);
        name = (TextView) itemView.findViewById(R.id.ru_name);
        removeBtn = (ImageButton) itemView.findViewById(R.id.ru_remove);
    }
}
