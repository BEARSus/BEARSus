package com.example.einrick.layout;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.CountDownTimer;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.andrognito.patternlockview.PatternLockView;
import com.andrognito.patternlockview.listener.PatternLockViewListener;
import com.andrognito.patternlockview.utils.PatternLockUtils;
import com.andrognito.patternlockview.utils.ResourceUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class pattern_game extends AppCompatActivity {
    public static final String EXTRA_SCORE1 = "com.einrick.layout.MESSAGE";
    public static final String EXTRA2_SCORE1 = "com.einrick.layout.MESSAGE";
    private ArrayList<Integer> ctrList;
    private ArrayList<String> easyList;
    private ArrayList<String> easyListAnswers;
    private int ctr;
    private int ctr2;
    private int score1;
    private int score2;
    PatternLockView mPatternLockView;
    PatternLockView mPatternLockView2;
    String final_pattern="";
    private String answer;
    private String answer2;
    private User user1, user2;
    private DBHandler db;
    private Intent svc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_pattern_game);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        db = new DBHandler(this);

        Intent i = getIntent();
        user1 = i.getExtras().getParcelable("user1");
        user2 = i.getExtras().getParcelable("user2");

        TextView tx = (TextView)findViewById(R.id.p1score);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.p1name);
        tx.setTypeface(custom_font);
        tx.setText(user1.getUsername());

        tx = (TextView)findViewById(R.id.p1round);
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.p1timer);
        tx.setTypeface(custom_font);

        TextView tx2 = (TextView)findViewById(R.id.p2name);
        tx2.setTypeface(custom_font);
        tx2.setText(user2.getUsername());

        tx = (TextView)findViewById(R.id.p2round);
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.p2timer);
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.p1score);
        tx.setTypeface(custom_font);

        tx = (TextView)findViewById(R.id.p2score);
        tx.setTypeface(custom_font);

        ImageView iv = findViewById(R.id.p1icon);
        int idIcon = getResources().getIdentifier(user1.getIcon(), "drawable", getPackageName());
        iv.setImageResource(idIcon);

        ImageView iv2 = findViewById(R.id.p2icon);
        idIcon = getResources().getIdentifier(user2.getIcon(), "drawable", getPackageName());
        iv2.setImageResource(idIcon);

        ctrList = new ArrayList<Integer>();
        easyList = new ArrayList<String>();
        easyListAnswers = new ArrayList<String>();


        patterns();
        this.ctrList = randomize();
        countdown();
        patternAnswer();
        //patternAnswer2();

        svc=new Intent(this, BackgroundSoundService.class);
        svc.putExtra("media", String.valueOf(R.raw.bgm_pattern));

    }

    private void countdown(){
        new CountDownTimer(50000, 1000) {
            public void onTick(long millisUntilFinished) {
                TextView text = findViewById(R.id.p1timer);
                text.setText("Time: " + millisUntilFinished / 1000);

                text = findViewById(R.id.p2timer);
                text.setText("Time: " + millisUntilFinished / 1000);
            }

            public void onFinish() {
                // Get instance of Vibrator from current Context
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // Vibrate for 600 milliseconds
                v.vibrate(1000);

                ImageView pattern = findViewById(R.id.pattern1);
                pattern.setImageResource(R.drawable.logo);

                pattern = findViewById(R.id.pattern2);
                pattern.setImageResource(R.drawable.logo);

                Intent i = new Intent(pattern_game.this, winner.class);
                UserVersusUser uvu;
                int uvuID = db.getMaxUVUID();
                if (uvuID == -1)
                    uvuID = 0;
                else
                    uvuID++;
                if (score1 > score2){
                    uvu = new UserVersusUser(uvuID, user1, score1, user2, score2, "patterngame");
                    i.putExtra("winner", uvu);
                    i.putExtra("winnerscore", score1);
                }
                else{
                    uvu = new UserVersusUser(uvuID, user2, score2, user1, score1, "patterngame");
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

    private void patterns(){
        //round 1 arraylist
        easyList.add("e1");
        easyListAnswers.add("34517");
        easyList.add("e2");
        easyListAnswers.add("7410358");
        easyList.add("e6");
        easyListAnswers.add("1304857");
        easyList.add("e8");
        easyListAnswers.add("13754");
        easyList.add("e12");
        easyListAnswers.add("412635");
        easyList.add("e13");
        easyListAnswers.add("6104872");
        easyList.add("e14");
        easyListAnswers.add("14208");
        easyList.add("e16");
        easyListAnswers.add("014526");
        easyList.add("e19");
        easyListAnswers.add("840126");
        easyList.add("e21");
        easyListAnswers.add("814762");
        easyList.add("e24");
        easyListAnswers.add("5673104");
        easyList.add("e28");
        easyListAnswers.add("634785");
        easyList.add("e31");
        easyListAnswers.add("031425876");
        easyList.add("e32");
        easyListAnswers.add("31402");
        easyList.add("e33");
        easyListAnswers.add("0124678");
        easyList.add("e36");
        easyListAnswers.add("875241036");
        easyList.add("e45");
        easyListAnswers.add("731248");

        //round 2 arraylist
        easyList.add("m7");
        easyListAnswers.add("013476852");
        easyList.add("m9");
        easyListAnswers.add("014528763");
        easyList.add("m10");
        easyListAnswers.add("631478520");
        easyList.add("m15");
        easyListAnswers.add("76425308");
        easyList.add("m17");
        easyListAnswers.add("01376485");
        easyList.add("m18");
        easyListAnswers.add("640871");
        easyList.add("m20");
        easyListAnswers.add("361472508");
        easyList.add("m22");
        easyListAnswers.add("7412086");
        easyList.add("m26");
        easyListAnswers.add("784036215");
        easyList.add("m34");
        easyListAnswers.add("210367854");
        easyList.add("m37");
        easyListAnswers.add("674251308");
        easyList.add("m38");
        easyListAnswers.add("674213085");
        easyList.add("m40");
        easyListAnswers.add("8403526");
        easyList.add("m42");
        easyListAnswers.add("46780");
        easyList.add("m44");
        easyListAnswers.add("7643512");

        //round 3 arraylist
        easyList.add("h3");
        easyListAnswers.add("421753");
        easyList.add("h4");
        easyListAnswers.add("147362580");
        easyList.add("h5");
        easyListAnswers.add("510463728");
        easyList.add("h23");
        easyListAnswers.add("143762580");
        easyList.add("h25");
        easyListAnswers.add("341762580");
        easyList.add("h29");
        easyListAnswers.add("14532");
        easyList.add("h30");
        easyListAnswers.add("043652871");
        easyList.add("h39");
        easyListAnswers.add("42530871");
        easyList.add("h41");
        easyListAnswers.add("014263587");
        easyList.add("h46");
        easyListAnswers.add("147635820");
        easyList.add("h47");
        easyListAnswers.add("417350268");
        easyList.add("h48");
        easyListAnswers.add("134527680");
        easyList.add("h49");
        easyListAnswers.add("473562801");
        easyList.add("h50");
        easyListAnswers.add("431570862");
        easyList.add("h51");
        easyListAnswers.add("156430827");
        easyList.add("h52");
        easyListAnswers.add("043176258");
        easyList.add("h53");
        easyListAnswers.add("437268015");
        easyList.add("h54");
        easyListAnswers.add("674135082");

    }

    private void patternAnswer(){
        ctr = 0;
        ctr2 = 0;
        setImage1(ctrList.get(ctr), easyList, easyListAnswers);
        setImage2(ctrList.get(ctr), easyList, easyListAnswers);

        mPatternLockView = (PatternLockView)findViewById(R.id.pattern_lock_view1);
        mPatternLockView.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                final_pattern = PatternLockUtils.patternToString(mPatternLockView, pattern);
                if (final_pattern.equals(answer)) {
                    TextView sc = findViewById(R.id.p1score);
                    score1 = score1 + 1;
                    sc.setText("Score: " + score1);
                    ctr++;
                    setImage1(ctrList.get(ctr), easyList, easyListAnswers);
                    mPatternLockView.setViewMode(PatternLockView.PatternViewMode.CORRECT);
                }
                else{
                    mPatternLockView.setViewMode(PatternLockView.PatternViewMode.WRONG);
                }
                mPatternLockView.clearPattern();

                /*final_pattern = PatternLockUtils.patternToString(mPatternLockView, pattern);
                if (final_pattern.equals(answer)) {
                    TextView sc = findViewById(R.id.p1score);
                    score1 = score1 + 1;
                    sc.setText("Score: " + score1);
                    ctr++;
                    setImage1(ctrList.get(ctr), easyList, easyListAnswers);
                }*/
            }

            @Override
            public void onCleared() {
            }
        });
        mPatternLockView2 = (PatternLockView)findViewById(R.id.pattern_lock_view2);
        mPatternLockView2.addPatternLockListener(new PatternLockViewListener() {
            @Override
            public void onStarted() {

            }

            @Override
            public void onProgress(List<PatternLockView.Dot> progressPattern) {

            }

            @Override
            public void onComplete(List<PatternLockView.Dot> pattern) {
                final_pattern = PatternLockUtils.patternToString(mPatternLockView2, pattern);
                if (final_pattern.equals(answer2)) {
                    TextView sc = findViewById(R.id.p2score);
                    score2 = score2 + 1;
                    sc.setText("Score: " + score2);
                    ctr2++;
                    setImage2(ctrList.get(ctr2), easyList, easyListAnswers);
                    mPatternLockView2.setViewMode(PatternLockView.PatternViewMode.CORRECT);
                }
                else{
                    mPatternLockView2.setViewMode(PatternLockView.PatternViewMode.WRONG);
                }
                mPatternLockView2.clearPattern();
                /*if (final_pattern.equals(answer)) {
                    TextView sc = findViewById(R.id.p2score);
                    score2 = score2 + 1;
                    sc.setText("Score: " + score2);
                    ctr2++;
                    setImage2(ctrList.get(ctr2), easyList, easyListAnswers);
                }*/
            }

            @Override
            public void onCleared() {
            }
        });


    }

    public ArrayList<Integer> randomize(){
        Random r = new Random();
        int x = r.nextInt(easyList.size());
        while (ctrList.size() != easyList.size()){
            if (ctrList.contains(x)==false){
                ctrList.add(x);
            }
            x = r.nextInt(easyList.size());
        }
        return ctrList;
    }

    public void setImage1(int ctr, ArrayList<String> arr, ArrayList<String> answers){
        ImageView pattern = findViewById(R.id.pattern1);
        int x = getResources().getIdentifier(arr.get(ctr), "drawable", getPackageName());
        pattern.setImageResource(x);
        answer = answers.get(ctr);
    }

    public void setImage2(int ctr, ArrayList<String> arr, ArrayList<String> answers){
        ImageView pattern2 = findViewById(R.id.pattern2);
        int x = getResources().getIdentifier(arr.get(ctr), "drawable", getPackageName());
        pattern2.setImageResource(x);
        answer2 = answers.get(ctr);
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
