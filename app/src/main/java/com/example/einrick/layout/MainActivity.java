package com.example.einrick.layout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<User> usersList;
    private Intent svc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        //create database
        DBHandler db = new DBHandler(this);

        /*insert shops/rows
        Log.d("Insert: ", "Inserting...");
        db.insertUser(new User(0,"Tony", "pandaicon"));
        db.insertUser(new User(0,"Steve", "icebearicon"));
        db.insertUser(new User(0,"Clark", "pandaicon"));
        db.insertUser(new User(0,"Bruce", "grizzicon"));
        */
        Log.d("Reading: ", "Reading...");
        usersList = db.getAllUsersIncInactive();
        for (int i=0; i<usersList.size(); i++){
            String log = usersList.get(i).getId() + "//" + usersList.get(i).getUsername() + "//" + usersList.get(i).getIcon();
            Log.d("User: ", log);
        }

        ArrayList<UserVersusUser> list = db.getHighScores("pattern_game");
        for (int i=0; i<list.size(); i++){
            UserVersusUser uvu = list.get(i);
            String log = uvu.getId() + " || " + uvu.getWinner().getId() + uvu.getWinner().getUsername() + " || " + uvu.getScoreWin();
            Log.d("UVU: ", log);
        }


        svc=new Intent(this, BackgroundSoundService.class);
        svc.putExtra("media", String.valueOf(R.raw.bgm_main));
    }

    public void didTapButtonA(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.play);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        stopService(svc);

        Intent i = new Intent(this, select_bear.class);
        /*
        User user = new User(100, "xx", "grizzicon");
        i.putExtra("user", user);*/
        startActivity(i);
    }

    public void didTapButton(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.highscores);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        stopService(svc);

        Intent i = new Intent(this, highscores.class);
        startActivity(i);
        finish();
    }

    public void didTapHelp(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.help);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        stopService(svc);

        Intent i = new Intent(this, help.class);
        startActivity(i);
        finish();
    }

    public void didTapRemove(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.removeusers);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        stopService(svc);

        Intent i = new Intent(this, remove_user.class);
        startActivity(i);
        finish();
    }

    public ArrayList<User> getUsersList(){
        return usersList;
    }

    @Override
    public void onBackPressed(){
        stopService(svc);
        finish();
    }

    @Override
    public void onPause() {
        super.onPause();
        stopService(svc);
    }

    @Override
    public void onResume() {
        super.onResume();
        startService(svc);
    }

    @Override
    public void onStart() {
        super.onStart();
        startService(svc);
    }

    @Override
    public void onStop() {
        super.onStop();
        stopService(svc);
    }

    public void Info(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.info);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        Intent i = new Intent(this, pop_info.class);
        startActivity(i);
    }
}
