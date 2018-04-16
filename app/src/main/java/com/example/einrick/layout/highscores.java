package com.example.einrick.layout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import com.astuetz.PagerSlidingTabStrip;

import java.util.ArrayList;

public class highscores extends AppCompatActivity {
    private ArrayList<HighScoreItem> list = new ArrayList<HighScoreItem>();
    private RecyclerView recyclerView;
    private HighScoreAdapter adapter;
    private DBHandler db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_highscores);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        db = new DBHandler(this);
/*
        recyclerView = findViewById(R.id.hsrecyclerview);
        adapter = new HighScoreAdapter(list);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
        recyclerView.scrollToPosition(0);
        highScoreModel();
           */

        // Get the ViewPager and set it's PagerAdapter so that it can display items
        ViewPager viewPager = (ViewPager) findViewById(R.id.viewPager);
        viewPager.setAdapter(new SampleFragmentPageAdapter(getSupportFragmentManager(), this));

        // Give the PagerSlidingTabStrip the ViewPager
        PagerSlidingTabStrip tabsStrip = (PagerSlidingTabStrip) findViewById(R.id.tabs);
        // Attach the view pager to the tab strip
        tabsStrip.setViewPager(viewPager);
    }

    private void highScoreModel(){
        //parameter of getHighScores method is the game played (ex. patterngame, spotthebear, tapchloe, and bingobear)
        ArrayList<UserVersusUser> uvulist = db.getHighScores("patterngame");
        int id;
        for (int i=0; i<uvulist.size(); i++){
            UserVersusUser uvu = uvulist.get(i);
            id = getResources().getIdentifier(uvu.getWinner().getIcon(), "drawable", getPackageName());
            list.add(new HighScoreItem(id, uvu.getWinner().getUsername(), uvu.getScoreWin()));
        }

        adapter.notifyDataSetChanged();
    }

    @Override
    public void onBackPressed(){
        /*
        ImageButton button = (ImageButton)findViewById(R.id.hsbackbutton);
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


    public DBHandler getDB(){
        return this.db;
    }
}
