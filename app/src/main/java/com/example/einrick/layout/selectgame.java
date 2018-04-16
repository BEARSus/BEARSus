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
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class selectgame extends AppCompatActivity {
    private Spinner s;
    private User user1, user2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_selectgame);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        Intent i = getIntent();
        user1 = i.getExtras().getParcelable("user1");
        user2 = i.getExtras().getParcelable("user2");

        String[] arraySpinner = new String[] {
                "Follow the Pattern", "Catch Kray Kray", "Tap 'Em All", "Bingo Bear"
        };
        s = (Spinner) findViewById(R.id.gameSpinner);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_dropdown_item, arraySpinner);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s.setAdapter(adapter);

        s.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                ImageView logo = findViewById(R.id.gameLogo);
                TextView text = findViewById(R.id.gameInfo);
                if (s.getSelectedItemPosition()==0){
                    logo.setImageResource(R.drawable.patterngame1);
                    text.setText("Each player will have 50 seconds to copy different patterns. The orange dot indicates the starting point. Player with the highest score wins.");
                }
                else if (s.getSelectedItemPosition()==1){
                    logo.setImageResource(R.drawable.tapchloe1);
                    text.setText("Players will be shown random pictures and players must catch Kray Kray when she appears. The first player to tap his/her button earns a point. When a player taps his/her button and it's not Kray Kray, points will be deducted from the player. Player with the highest score wins the game.");
                }
                else if (s.getSelectedItemPosition()==2){
                    logo.setImageResource(R.drawable.spotthebear1);
                    text.setText("Bears will randomly appear for a given amount of time. Player must catch their own bear by tapping it to gain points. If the wrong bear is caught, points will be deducted from the player. Player who spotted more bears wins the game.");
                }
                else{
                    logo.setImageResource(R.drawable.bingobear1);
                    text.setText("Players will take turns in selecting the column where they want to place their bears by clicking the icons at the first row. The goal is to have 4 bears forming a straight line. It can be horizontal, vertical, or diagonal. First player to accomplish the line wins the game.");
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parentView) {
                // your code here
            }

        });
    }

    public void didTapButtonSelect(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.selectGame);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        Intent i = new Intent (this, Countdown.class);
        i.putExtra("game", Integer.toString(s.getSelectedItemPosition()));
        i.putExtra("user1", user1);
        i.putExtra("user2", user2);
        startActivity(i);
    }
}
