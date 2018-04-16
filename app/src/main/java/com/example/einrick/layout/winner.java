package com.example.einrick.layout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class winner extends AppCompatActivity {
    private User user1, user2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_winner);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        Intent i = getIntent();
        user1 = i.getExtras().getParcelable("user1");
        user2 = i.getExtras().getParcelable("user2");
        Integer score =  i.getIntExtra("winnerscore",0);
        UserVersusUser match = i.getExtras().getParcelable("winner");
        User win;
        if (match.getScoreWin() != match.getScoreLose()){
            win = match.getWinner();

            Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");

            TextView winner = findViewById(R.id.playerWin);
            winner.setText(win.getUsername());
            winner.setTypeface(custom_font);

            TextView winnerscore = findViewById(R.id.playerWinScore);
            if (!match.getGame().equals("bingobear") && score!=0){
                winnerscore.setText("Score: " + score);
                winnerscore.setTypeface(custom_font);
            }else{
                winnerscore.setText("");
            }

            ImageView winnerIV = findViewById(R.id.imageViewWinner);
            if (win.getIcon().equals("grizzicon")){
                winnerIV.setImageResource(R.drawable.grizzwin);
            }
            else if (win.getIcon().equals("icebearicon")){
                winnerIV.setImageResource(R.drawable.icebearwin);
            }
            else if (win.getIcon().equals("pandaicon")){
                winnerIV.setImageResource(R.drawable.pandawin);
            }
        }
        else{
            TextView winner = findViewById(R.id.playerWin);
            winner.setText("Draw");

            TextView winnerscore = findViewById(R.id.playerWinScore);
            winnerscore.setText("");

            Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
            winner.setTypeface(custom_font);

            ImageView winnerIV = findViewById(R.id.imageViewWinner);
            String game = match.getGame();
            int id = getResources().getIdentifier(game, "drawable", getPackageName());
            winnerIV.setImageResource(id);
        }



        //User win = i.getExtras().getParcelable("winner");


    }

    public void didTapButtonNext(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.nextbtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        Intent intent = new Intent(winner.this, GameOptions.class);
        //intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("user1", user1);
        intent.putExtra("user2", user2);
        intent.putExtra("previousActivity", "winner");
        startActivity(intent);
        finish();
    }

    public void didTapButtonBack(View view) {
        ImageButton button = (ImageButton) findViewById(R.id.backBtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        Intent intent = new Intent(winner.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }

    @Override
    public void onBackPressed(){
        Intent intent = new Intent(winner.this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
        finish();
    }
}
