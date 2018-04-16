package com.example.einrick.layout;

import android.content.Context;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.ArrayList;

/**
 * Created by Lenovo on 09/04/2018.
 */

public class RemoveUserAdapter extends RecyclerView.Adapter<RemoveUserHolder>{
    private ArrayList<UserItem> list;

    public RemoveUserAdapter(ArrayList<UserItem> list){
        this.list = list;
    }

    @Override
    public RemoveUserHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.remove_user_item, parent, false);

        return new RemoveUserHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RemoveUserHolder holder, final int position) {
        UserItem item = list.get(position);
        holder.name.setText(item.getUsername());
        holder.icon.setImageResource(item.getIconId());
        holder.removeBtn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                ImageButton button = (ImageButton)view.findViewById(R.id.ru_remove);
                final Animation myAnim = AnimationUtils.loadAnimation(view.getContext(), R.anim.bounce);

                // Use bounce interpolator with amplitude 0.2 and frequency 20
                MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
                myAnim.setInterpolator(interpolator);

                button.startAnimation(myAnim);

                DBHandler db = new DBHandler(view.getContext());
                db.archiveUserIcon(list.get(position).getUserId());
                list.remove(position);

                notifyItemRemoved(position);
                notifyItemRangeChanged(position,list.size());
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }}
