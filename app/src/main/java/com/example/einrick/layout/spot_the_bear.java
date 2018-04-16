package com.example.einrick.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.res.Resources;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static java.lang.String.valueOf;

public class spot_the_bear extends AppCompatActivity {

    public static final String EXTRA_SCORE1 = "com.einrick.layout.MESSAGE";
    public static final String EXTRA2_SCORE1 = "com.einrick.layout.MESSAGE";
    private ArrayList<Integer> ctrList;
    private ArrayList<Integer> distractionCtr;
    private int score1, score2 = 0;
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
        setContentView(R.layout.activity_spot_the_bear);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        db = new DBHandler(this);

        Intent i = getIntent();
        user1 = i.getExtras().getParcelable("user1");
        user2 = i.getExtras().getParcelable("user2");

        icon1 = user1.getIcon();
        icon2 = user2.getIcon();

        this.init();
        this.countdown();

        svc=new Intent(this, BackgroundSoundService.class);
        svc.putExtra("media", String.valueOf(R.raw.bgm_tap));
    }

    public void init()
    {
        TextView tx;
        ImageView iv;
        int id;
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");

        tx = (TextView)findViewById(R.id.player1name);
        tx.setTypeface(custom_font);
        tx.setText(user1.getUsername());

        iv = findViewById(R.id.player1icon);
        id = getResources().getIdentifier(user1.getIcon(), "drawable", getPackageName());
        iv.setImageResource(id);

        tx = (TextView)findViewById(R.id.player1score);
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.player1timer);
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.player2name);
        tx.setTypeface(custom_font);
        tx.setText(user2.getUsername());

        iv = findViewById(R.id.player2icon);
        id = getResources().getIdentifier(user2.getIcon(), "drawable", getPackageName());
        iv.setImageResource(id);

        tx = (TextView)findViewById(R.id.player2score);
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.player2timer);
        tx.setTypeface(custom_font);

        ctrList = new ArrayList<>();
        distractionCtr = new ArrayList<>();
        //drawableCtr = new ArrayList<>();
        //addDrawable();
    }

    private void countdown(){

        new CountDownTimer(50000, 1000) {

            public void onTick(long millisUntilFinished) {

                Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");

                TextView text = findViewById(R.id.player1timer);
                text.setText("Time: " + millisUntilFinished / 1000);
                text.setTypeface(custom_font);

                text = findViewById(R.id.player2timer);
                text.setText("Time: " + millisUntilFinished / 1000);
                text.setTypeface(custom_font);

                bearAppear();
                bearAppear();

                if ((49 >= (millisUntilFinished / 1000) && 45<=(millisUntilFinished / 1000))
                        || (millisUntilFinished / 1000)% 5 == 0 ||
                        25 >= (millisUntilFinished / 1000))
                {
                    bearAppear();
                    bearDistractor();
                    bearAppear();
                }

                if (40 >= (millisUntilFinished / 1000))
                {
                    bearDistractor();
                    bearAppear();
                }

                if (distractionCtr.size()>0 && (millisUntilFinished / 1000)%5 == 5)
                {
                    int i= distractionCtr.get(0);
                    distractionCtr.remove(0);
                    bearDisappearAfter3seconds(i);
                }

                if(48 > (millisUntilFinished / 1000))
                {
                    int i= ctrList.get(0);
                    ctrList.remove(0);
                    bearDisappearAfter3seconds(i);
                    bearAppear();
                }
            }

            public void onFinish() // if timer is already finished
            {
                // Get instance of Vibrator from current Context
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // Vibrate for 1000 milliseconds
                v.vibrate(1000);

                Intent i = new Intent(spot_the_bear.this, winner.class);
                UserVersusUser uvu;
                int uvuID = db.getMaxUVUID();
                if (uvuID == -1)
                    uvuID = 0;
                else
                    uvuID++;
                if (score1 > score2){
                    uvu = new UserVersusUser(uvuID, user1, score1, user2, score2, "spotthebear");
                    i.putExtra("winner", uvu);
                    i.putExtra("winnerscore", score1);
                }
                else{
                    uvu = new UserVersusUser(uvuID, user2, score2, user1, score1, "spotthebear");
                    i.putExtra("winner", uvu);
                    i.putExtra("winnerscore", score2);
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

    public void bearDistractor()
    {
        int random = 0;
        int ivID, x;
        ImageView iv;
        String id;
        Random r = new Random ();
        random =  r.nextInt(15);

        distractionCtr.add(random); //kung saan magaapear si distraction

        //int drawablectr = r.nextInt(drawableCtr.size());

        //appear for player1
        id = "player" + 1 + "btn" + random;  //id of cell
        ivID = getResources().getIdentifier(id, "id", getPackageName());
        iv = findViewById(ivID);
        // drawable must not be the same as the player's icon (player 1 icon is pandaicon)
        // set the distractor to opponent's icon
        ivID = getResources().getIdentifier(icon2, "drawable", getPackageName());
        iv.setImageResource(ivID);
        iv.setTag(null); //this will be used later for deducting points for catching wrong bears
        /*
        if (drawableCtr.get(drawablectr) != R.drawable.pandaicon) {
            iv.setImageResource(drawableCtr.get(drawablectr));
        }
        else
        {
            while ((drawableCtr.get(drawablectr) == R.drawable.pandaicon))
            {
                drawablectr = r.nextInt(drawableCtr.size());
            }
            iv.setImageResource(drawableCtr.get(drawablectr));
        }
        */
        //appear for player2
        id = "player" + 2 + "btn" + random;  //id of cell
        ivID = getResources().getIdentifier(id, "id", getPackageName());
        iv = findViewById(ivID);
        // set the distractor the opponent's icon
        ivID = getResources().getIdentifier(icon1, "drawable", getPackageName());
        iv.setImageResource(ivID);
        iv.setTag(null);
    }

    public void bearAppear()
    {
        int random = 0;
        int ivID, x;
        ImageView iv;
        String id;
        Random r = new Random ();
        random =  r.nextInt(15);

        ctrList.add(random);

        //appear for player1
        id = "player" + 1 + "btn" + random;  //id of cell
        ivID = getResources().getIdentifier(id, "id", getPackageName());
        iv = findViewById(ivID);
        //set using the player's icon
        ivID = getResources().getIdentifier(icon1, "drawable", getPackageName());
        iv.setImageResource(ivID);
        //set the tag here
        iv.setTag(ivID);

        //appear for player2
        id = "player" + 2 + "btn" + random;  //id of cell
        ivID = getResources().getIdentifier(id, "id", getPackageName());
        iv = findViewById(ivID);
        //set using the player's icon
        ivID = getResources().getIdentifier(icon2, "drawable", getPackageName());
        iv.setImageResource(ivID);
        //set the tag here
        iv.setTag(ivID);
    }

    //bear must disappear when the user catches the bear (and player will score points)

    //extra:
    //bear will disappear after a 3 seconds of not catching it
    //another kind of bear will appear for distraction and should bot be caught by user else the user's bear will not appear

    public void bearDisappearAfter3seconds(int random)
    {
        int ivID;
        ImageView iv;
        String id;

        //disappear for player1
        id = "player" + 1 + "btn" + random;  //id of cell
        ivID = getResources().getIdentifier(id, "id", getPackageName());
        iv = findViewById(ivID);
        iv.setImageDrawable(null);
        iv.setTag(null);

        //disappear for player2
        id = "player" + 2 + "btn" + random;  //id of cell
        ivID = getResources().getIdentifier(id, "id", getPackageName());
        iv = findViewById(ivID);
        iv.setImageDrawable(null);
        iv.setTag(null);
    }

    public void player1Clicked (View view)
    {
        ImageView iv;
        TextView score;

        int btnid = view.getId(); // id of the button clicked
        iv = findViewById(btnid);

        //
        String x = "pandaicon";
        int id = getResources().getIdentifier(x, "drawable", getPackageName());
        Log.d("Player's Icon: ", "" + id);
        Log.d("Tag Number: ", "" + (Integer)iv.getTag());
        Log.d("Tag Number: ", "" + R.drawable.pandaicon);
        //
        //R.drawable will still depend on what icon the user will use

        int ivID;
        ivID = getResources().getIdentifier(icon1, "drawable", getPackageName());

        if (iv.getDrawable() != null && iv.getTag() != null && (Integer)iv.getTag() == ivID)
        // the imageview contains something and the tag is the icon of the player
        {
            //player 1
            score = findViewById(R.id.player1score);
            score1++;
            score.setText("Score: " + score1);

            iv.setImageDrawable(null);
        }

        if (iv.getDrawable() != null && iv.getTag() == null)
        //the image view contains something but the tag is it is a distractor
        {
            //player 1
            score = findViewById(R.id.player1score);
            TextView scorer1 = findViewById(R.id.player2score);
            score1-=3;
            score2++;
            score.setText("Score: " + score1);
            scorer1.setText("Score: " + score2);
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 300 milliseconds
            v.vibrate(300);

            shakeAnimation(R.id.player1layout);
        }

    }

    public void player2Clicked (View view)
    {
        ImageView iv;
        TextView score;

        int btnid = view.getId(); // numerical id of the button clicked
        Log.d("The button clicked is ", "buttonClicked: " + btnid);

        // check whether the button clicked has the bear icon on it if there is then score+=1
        iv = findViewById(btnid);

        int ivID = getResources().getIdentifier(icon2, "drawable", getPackageName());

        if (iv.getDrawable() != null && iv.getTag() != null && (Integer)iv.getTag() == ivID)
        {
            //player 2
            score = findViewById(R.id.player2score);
            score2++;
            score.setText("Score: " + score2);

            iv.setImageDrawable(null);
        }

        if (iv.getDrawable() != null && iv.getTag() == null)
        {
            //player 2
            score = findViewById(R.id.player2score);
            TextView scorer1 = findViewById(R.id.player1score);
            score2-=3;
            score1++;
            score.setText("Score: " + score2);
            scorer1.setText("Score: " + score1);
            // Get instance of Vibrator from current Context
            Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

            // Vibrate for 300 milliseconds
            v.vibrate(300);

            shakeAnimation(R.id.player2layout);
        }

    }

    @Override
    public void onBackPressed()
    {

    }

    public void shakeAnimation(int id){
        Animation shake = AnimationUtils.loadAnimation(this, R.anim.shake);
        findViewById(id).startAnimation(shake);
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
