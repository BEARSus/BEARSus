package com.example.einrick.layout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;

public class help extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_help);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);
    }

    public void didTapButtonPG(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.patterngamebtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("https://youtu.be/Fcw9wwjyjEw")));
        Log.i("Video", "Video Playing....");
    }

    public void didTapButtonSTB(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.spotthebearbtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://youtu.be/BGDAH5lTYBg")));
        Log.i("Video", "Video Playing....");
    }

    public void didTapButtonBB(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.bingobearbtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://youtu.be/6AOMv-CMSKc")));
        Log.i("Video", "Video Playing....");
    }

    public void didTapButtonTC(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.tapchloebtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        startActivity(new Intent(Intent.ACTION_VIEW,Uri.parse("https://youtu.be/K0Q5U2dlE58")));
        Log.i("Video", "Video Playing....");
    }

    public void onBackPressed(){
        /*ImageButton button = (ImageButton) findViewById(R.id.helpbackbutton);
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
