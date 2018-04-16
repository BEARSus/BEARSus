package com.example.einrick.layout;

import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Lenovo on 12/03/2018.
 */

public class Countdown extends AppCompatActivity{
    private ArrayList<String> pictureList;
    private int index;
    private User user1, user2;
    private int cntdwnLayout;
    private String icon1, icon2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        Intent i = getIntent();
        index = Integer.valueOf(i.getStringExtra("game"));
        user1 = i.getExtras().getParcelable("user1");
        user2 = i.getExtras().getParcelable("user2");

        if (index == 0){
            setContentView(R.layout.activity_countdown);
            cntdwnLayout = 1;
        }
        else if (index == 1){
            setContentView(R.layout.activity_countdown_2);
            cntdwnLayout = 2;
        }
        else if (index == 2){
            setContentView(R.layout.activity_countdown);
            cntdwnLayout = 1;
        }
        else{
            setContentView(R.layout.activity_countdown_2);
            cntdwnLayout = 2;
        }

        ImageView logo1 = findViewById(R.id.p1count);
        ImageView logo2 = findViewById(R.id.p2count);

        if (index == 0){
            logo1.setImageResource(R.drawable.patterngame);
            logo2.setImageResource(R.drawable.patterngame);
        }
        else if (index == 1){
            //logo1.setImageResource(R.drawable.tapchloe);
            logo2.setImageResource(R.drawable.tapchloe);
        }
        else if (index == 2){
            logo1.setImageResource(R.drawable.spotthebear);
            logo2.setImageResource(R.drawable.spotthebear);
        }
        else{
            //logo1.setImageResource(R.drawable.bingobear);
            logo2.setImageResource(R.drawable.bingobear);
        }

        pictureList = new ArrayList<String>();
        tutorial();
    }

    public void tutorial(){
        ImageButton tutorialIV = findViewById(R.id.tutorial);
        if (cntdwnLayout == 1){
            if (index == 0){
                final ArrayList<Integer> idArray = new ArrayList<Integer>();
                idArray.add(R.drawable.pg_tutorial1);
                idArray.add(R.drawable.pg_tutorial2);
                ImageButton button = findViewById(R.id.tutorial);
                button.setImageResource(idArray.get(0));
                button.setOnClickListener(new View.OnClickListener(){
                    int ctr=1;
                    @Override
                    public void onClick(View view) {
                        ImageButton tutorialIV = findViewById(R.id.tutorial);
                        tutorialIV.setImageResource(idArray.get(ctr));
                        if (ctr < idArray.size()-1){
                            ctr++;
                        }
                        else if (ctr == idArray.size()-1){
                            ImageButton start = findViewById(R.id.startBtn);
                            start.setVisibility(View.VISIBLE);
                        }
                    }
                });
            }
            else if (index == 2){
                tutorialIV.setImageResource(R.drawable.stb_tutorial1);
                new CountDownTimer(4000, 1000) {
                    public void onTick(long millisUntilFinished) {
                        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
                        TextView text = findViewById(R.id.tutorialTimer);
                        text.setTypeface(custom_font);
                        text.setText(String.valueOf(millisUntilFinished / 1000));
                    }

                    public void onFinish() {
                        ImageButton start = findViewById(R.id.startBtn);
                        start.setVisibility(View.VISIBLE);
                    }
                }.start();
            }
        }
        else if (cntdwnLayout == 2){
            ImageButton button = findViewById(R.id.tutorial2);
            final ArrayList<Integer> idArray = new ArrayList<Integer>();
            if (index == 1){
                idArray.add(R.drawable.tc_tutorial1);
                idArray.add(R.drawable.tc_tutorial2);
            }
            else if (index == 3){
                idArray.add(R.drawable.bb_tutorial1);
                idArray.add(R.drawable.bb_tutorial2);
                idArray.add(R.drawable.bb_tutorial3);
            }

            button.setImageResource(idArray.get(0));
            button.setOnClickListener(new View.OnClickListener(){
                int ctr=1;
                @Override
                public void onClick(View view) {
                    ImageButton tutorialIV = findViewById(R.id.tutorial2);
                    tutorialIV.setImageResource(idArray.get(ctr));
                    if (ctr < idArray.size()-1){
                        ctr++;
                    }
                    else if (ctr == idArray.size()-1){
                        ImageButton start = findViewById(R.id.startBtn2);
                        start.setVisibility(View.VISIBLE);
                    }
                }
            });
        }
    }

    public void didTapButtonCont(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.startBtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        pictureList.add("c3");
        pictureList.add("c2");
        pictureList.add("c1");
        pictureList.add("go");

        button.setEnabled(false);

        new CountDownTimer(6000, 1000) {
            int ctr=0;
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished<=4000){
                    Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
                    TextView p1namepos = findViewById(R.id.player1position);
                    p1namepos.setText(user1.getUsername());
                    p1namepos.setTypeface(custom_font);
                    p1namepos.setVisibility(View.VISIBLE);

                    TextView p2namepos = findViewById(R.id.player2position);
                    p2namepos.setText(user2.getUsername());
                    p2namepos.setTypeface(custom_font);
                    p2namepos.setVisibility(View.VISIBLE);

                    icon1 = user1.getIcon();
                    icon2 = user2.getIcon();

                    ImageView iv = findViewById(R.id.player1iconposition);
                    int id = getResources().getIdentifier(icon1, "drawable", getPackageName());
                    iv.setImageResource(id);
                    iv.setVisibility(View.VISIBLE);

                    iv = findViewById(R.id.player2iconposition);
                    id = getResources().getIdentifier(icon2, "drawable", getPackageName());
                    iv.setImageResource(id);
                    iv.setVisibility(View.VISIBLE);

                    TextView text = findViewById(R.id.tutorialTimer);
                    text.setVisibility(View.GONE);

                    ImageButton tutorialIV = findViewById(R.id.tutorial);
                    tutorialIV.setVisibility(View.GONE);

                    ImageButton start = findViewById(R.id.startBtn);
                    start.setVisibility(View.GONE);

                    ImageView pattern = findViewById(R.id.p1count);
                    int x = getResources().getIdentifier(pictureList.get(ctr), "drawable", getPackageName());
                    pattern.setImageResource(x);

                    ImageView pattern2 = findViewById(R.id.p2count);
                    int y = getResources().getIdentifier(pictureList.get(ctr), "drawable", getPackageName());
                    pattern2.setImageResource(y);
                    ctr++;
                }
            }

            public void onFinish() {
                ImageView pattern = findViewById(R.id.p1count);
                int x = getResources().getIdentifier(pictureList.get(3), "drawable", getPackageName());
                pattern.setImageResource(x);

                ImageView pattern2 = findViewById(R.id.p2count);
                int y = getResources().getIdentifier(pictureList.get(3), "drawable", getPackageName());
                pattern2.setImageResource(y);

                finish();

                if (index == 0){
                    Intent i = new Intent(Countdown.this, pattern_game.class);
                    i.putExtra("user1", user1);
                    i.putExtra("user2", user2);
                    startActivity(i);
                }
                else if (index == 1){
                    Intent i = new Intent(Countdown.this, tap_chloe.class);
                    i.putExtra("user1", user1);
                    i.putExtra("user2", user2);
                    startActivity(i);
                }
                else if (index == 2){
                    Intent i = new Intent(Countdown.this, spot_the_bear.class);
                    i.putExtra("user1", user1);
                    i.putExtra("user2", user2);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(Countdown.this, four_in_a_row.class);
                    i.putExtra("user1", user1);
                    i.putExtra("user2", user2);
                    startActivity(i);
                }
            }
        }.start();
    }

    public void didTapButtonCont2(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.startBtn2);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        pictureList.add("c3");
        pictureList.add("c2");
        pictureList.add("c1");
        pictureList.add("go");

        button.setEnabled(false);

        new CountDownTimer(6000, 1000) {
            int ctr=0;
            public void onTick(long millisUntilFinished) {
                if (millisUntilFinished<=4000){
                    TextView text = findViewById(R.id.tutorialTimer2);
                    text.setVisibility(View.GONE);

                    ImageButton tutorialIV = findViewById(R.id.tutorial2);
                    tutorialIV.setVisibility(View.GONE);

                    ImageButton start = findViewById(R.id.startBtn2);
                    start.setVisibility(View.GONE);

                    /*ImageView pattern = findViewById(R.id.p1count);
                    int x = getResources().getIdentifier(pictureList.get(ctr), "drawable", getPackageName());
                    pattern.setImageResource(x);*/

                    ImageView pattern2 = findViewById(R.id.p2count);
                    int y = getResources().getIdentifier(pictureList.get(ctr), "drawable", getPackageName());
                    pattern2.setImageResource(y);
                    ctr++;
                }
            }

            public void onFinish() {
                /*ImageView pattern = findViewById(R.id.p1count);
                int x = getResources().getIdentifier(pictureList.get(3), "drawable", getPackageName());
                pattern.setImageResource(x);*/

                ImageView pattern2 = findViewById(R.id.p2count);
                int y = getResources().getIdentifier(pictureList.get(3), "drawable", getPackageName());
                pattern2.setImageResource(y);

                finish();

                if (index == 0){
                    Intent i = new Intent(Countdown.this, pattern_game.class);
                    i.putExtra("user1", user1);
                    i.putExtra("user2", user2);
                    startActivity(i);
                }
                else if (index == 1){
                    Intent i = new Intent(Countdown.this, tap_chloe.class);
                    i.putExtra("user1", user1);
                    i.putExtra("user2", user2);
                    startActivity(i);
                }
                else if (index == 2){
                    Intent i = new Intent(Countdown.this, spot_the_bear.class);
                    i.putExtra("user1", user1);
                    i.putExtra("user2", user2);
                    startActivity(i);
                }
                else{
                    Intent i = new Intent(Countdown.this, four_in_a_row.class);
                    i.putExtra("user1", user1);
                    i.putExtra("user2", user2);
                    startActivity(i);
                }
            }
        }.start();
    }

    @Override
    public void onBackPressed(){

    }
}
