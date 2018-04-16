package com.example.einrick.layout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Random;
import java.util.Timer;

public class tap_chloe extends AppCompatActivity {

    private int player1Score;
    private int player2Score;
    private int timer;
    private int currentpicture;
    private ArrayList<String> picList;
    private User user1, user2;
    private String icon1, icon2;
    private DBHandler db;
    private Intent svc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_tap_chloe);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        db = new DBHandler(this);

        Intent i = getIntent();
        user1 = i.getExtras().getParcelable("user1");
        user2 = i.getExtras().getParcelable("user2");

        icon1 = user1.getIcon();
        icon2 = user2.getIcon();

        TextView tx = (TextView)findViewById(R.id.chloeTimer);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.tcp1Name);
        tx.setText(user1.getUsername());
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.tcp1NameTap);
        tx.setText("Tap this, " + user1.getUsername() + "!");
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.tcp1Score);
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.tcp2Name);
        tx.setText(user2.getUsername());
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.tcp2NameTap);
        tx.setText("Tap this, " + user2.getUsername() + "!");
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.tcp2Score);
        tx.setTypeface(custom_font);

        ImageView iv = findViewById(R.id.player1icon);
        int id = getResources().getIdentifier(icon1, "drawable", getPackageName());
        iv.setImageResource(id);

        iv = findViewById(R.id.player2icon);
        id = getResources().getIdentifier(icon2, "drawable", getPackageName());
        iv.setImageResource(id);

        initList();
        initVariables();

        svc=new Intent(this, BackgroundSoundService.class);
        svc.putExtra("media", String.valueOf(R.raw.bgm_catch));
    }

    public void initList(){
        picList = new ArrayList<String>();
        picList.add("1");

    }

    public void initVariables(){
        player1Score = 0;
        player2Score = 0;
        timer = 30;
        currentpicture = 0;
        TextView tx = (TextView)findViewById(R.id.chloeTimer);
        tx.setText(""+timer);
        TextView tx1 = (TextView)findViewById(R.id.tcp1Score);
        tx1.setText("Score: "+player1Score);
        TextView tx2 = (TextView)findViewById(R.id.tcp2Score);
        tx2.setText("Score: "+player2Score);
        startTimer();
    }

    public void changePicture(){
        Random rand = new Random();
        int newPic = rand.nextInt(15);
        while (newPic == currentpicture){
            newPic = rand.nextInt(15);
        }
        if (newPic != 0){
            currentpicture = newPic;
            ImageView logo = findViewById(R.id.tcLogo);
            int x = getResources().getIdentifier("tc"+newPic, "drawable", getPackageName());
            logo.setImageResource(x);

        }else{
            newPic = 15;
        }

        ImageButton button = (ImageButton)findViewById(R.id.tcp1Btn);
        ImageButton button2 = (ImageButton)findViewById(R.id.tcp2Btn);

        button.setEnabled(true);
        button.setClickable(true);
        button.setBackgroundResource(R.drawable.tap1btn);
        button.setImageResource(R.drawable.tap1btn);
        button2.setEnabled(true);
        button2.setClickable(true);
        button2.setBackgroundResource(R.drawable.tap2btn);
        button2.setImageResource(R.drawable.tap2btn);
    }

    public void startTimer(){
        new CountDownTimer(31000, 1000) {
            @Override
            public void onTick(long l) {
                Log.d("Tick: ", String.valueOf(l));
                timer = (int) l/1000;
                TextView tx = (TextView) findViewById(R.id.chloeTimer);
                tx.setText("" + timer);
                boolean s = false;
                changePicture();
                /*
                if (timer > 15 && l%1000 > 500 ){
                    changePicture();
                }else if (timer > 0 && timer <= 15){
                    changePicture();
                }*/
            }

            @Override
            public void onFinish() {
                // Get instance of Vibrator from current Context
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // Vibrate for 1000 milliseconds
                v.vibrate(1000);

                Intent i = new Intent(tap_chloe.this, winner.class);
                UserVersusUser uvu;
                int uvuID = db.getMaxUVUID();
                if (uvuID == -1)
                    uvuID = 0;
                else
                    uvuID++;
                if (player1Score > player2Score){
                    uvu = new UserVersusUser(uvuID, user1, player1Score, user2, player2Score, "tapchloe");
                    i.putExtra("winner", uvu);
                    i.putExtra("winnerscore", player1Score);
                }
                else{
                    uvu = new UserVersusUser(uvuID, user2, player2Score, user1, player1Score, "tapchloe");
                    i.putExtra("winner", uvu);
                    i.putExtra("winnerscore", player2Score);
                }

                stopService(svc);

                db.insertMatch(uvu);
                i.putExtra("user1", user1);
                i.putExtra("user2", user2);
                finish();
                startActivity(i);
            }
        }.start();
    }

    public void p1tap(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.tcp1Btn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        ImageButton button2 = (ImageButton)findViewById(R.id.tcp2Btn);

        button.setEnabled(false);
        button.setClickable(false);
        button.setBackgroundResource(R.drawable.disabledbtn);
        button.setImageResource(R.drawable.disabledbtn);
        button2.setEnabled(false);
        button2.setClickable(false);
        button2.setBackgroundResource(R.drawable.disabledbtn);
        button2.setImageResource(R.drawable.disabledbtn);

        if (currentpicture%3 == 0 && currentpicture != 0){
            player1Score++;
        }else{
            player1Score--;
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 300 milliseconds
            v.vibrate(300);
        }

        TextView tx1 = (TextView)findViewById(R.id.tcp1Score);
        tx1.setText("Score: "+player1Score);
    }

    public void p2tap(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.tcp2Btn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        ImageButton button1 = (ImageButton)findViewById(R.id.tcp1Btn);

        button.setEnabled(false);
        button.setClickable(false);
        button.setBackgroundResource(R.drawable.disabledbtn);
        button.setImageResource(R.drawable.disabledbtn);
        button1.setEnabled(false);
        button1.setClickable(false);
        button1.setBackgroundResource(R.drawable.disabledbtn);
        button1.setImageResource(R.drawable.disabledbtn);

        if (currentpicture%3 == 0 && currentpicture != 0){
            player2Score++;
        }
        else{
            player2Score--;
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 300 milliseconds
            v.vibrate(300);
        }

        TextView tx1 = (TextView)findViewById(R.id.tcp2Score);
        tx1.setText("Score: "+player2Score);
    }

    @Override
    public void onBackPressed(){

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

}
