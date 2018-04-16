package com.example.einrick.layout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.Toast;

import java.util.Random;

public class GameOptions extends AppCompatActivity {
    private User user1, user2;
    private Intent i;
    private String previousActivity;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        //setContentView(R.layout.activity_main);
        setContentView(R.layout.activity_game_options);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        i = getIntent();
        user1 = i.getExtras().getParcelable("user1");
        user2 = i.getExtras().getParcelable("user2");
        previousActivity = i.getStringExtra("previousActivity");
    }

    public void didTapButtonRan(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.random);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        Random rand = new Random();
        int x = rand.nextInt(3) + 1;
        Intent i = new Intent (this, Countdown.class);
        i.putExtra("game", Integer.toString(x));
        i.putExtra("user1", user1);
        i.putExtra("user2", user2);
        startActivity(i);
    }

    public void didTapButtonSel(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.highscores);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        Intent i = new Intent (this, selectgame.class);
        i.putExtra("user1", user1);
        i.putExtra("user2", user2);
        startActivity(i);
    }

    @Override
    public void onBackPressed(){
        Intent intent;
        if (previousActivity.equals("winner")){
            intent = new Intent(GameOptions.this, MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        }
        finish();

    }
}
