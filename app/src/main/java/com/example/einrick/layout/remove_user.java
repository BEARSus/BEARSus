package com.example.einrick.layout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.ArrayList;

public class remove_user extends AppCompatActivity {
    private ArrayList<UserItem> list = new ArrayList<UserItem>();
    private RecyclerView recyclerView;
    private RemoveUserAdapter adapter;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_remove_user);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        db = new DBHandler(this);

        recyclerView = findViewById(R.id.removerecyclerview);
        adapter = new RemoveUserAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);

        usersModel();
    }

    private void usersModel(){
        ArrayList<User> userlist = db.getAllUsers();
        int id;
        for (int i=0; i<userlist.size(); i++){
            User user = userlist.get(i);
            id = getResources().getIdentifier(user.getIcon(), "drawable", getPackageName());
            list.add(new UserItem(id, user.getUsername(), user.getId()));
        }
        adapter.notifyDataSetChanged();
    }


    @Override
    public void onBackPressed(){
        /*
        ImageButton button = (ImageButton)findViewById(R.id.ru_backbtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);
        */
        Intent i = new Intent(this, MainActivity.class);
        startActivity(i);
        finish();
    }
}
