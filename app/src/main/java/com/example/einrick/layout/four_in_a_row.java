package com.example.einrick.layout;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.SystemClock;
import android.os.Vibrator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
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

public class four_in_a_row extends AppCompatActivity implements Animation.AnimationListener {
    private String icon1, icon2;
    private int activePlayer;
    private ArrayList<String> answerArr = new ArrayList<String>();
    private User user1, user2;
    private DBHandler db;
    private int gameCtr;
    Animation animationSlideDown;
    private Intent svc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_four_in_a_row);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        Intent i = getIntent();
        user1 = i.getExtras().getParcelable("user1");
        user2 = i.getExtras().getParcelable("user2");

        db = new DBHandler(this);

        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
        int iconid;

        TextView tx1 = findViewById(R.id.p1name);
        tx1.setText(user1.getUsername());
        tx1.setTypeface(custom_font);

        iconid = getResources().getIdentifier(user1.getIcon(), "drawable", getPackageName());
        ImageView iv1 = findViewById(R.id.p1icon);
        iv1.setImageResource(iconid);

        TextView tx2 = findViewById(R.id.p2name);
        tx2.setText(user2.getUsername());
        tx2.setTypeface(custom_font);

        iconid = getResources().getIdentifier(user2.getIcon(), "drawable", getPackageName());
        ImageView iv2 = findViewById(R.id.p2icon);
        iv2.setImageResource(iconid);

        icon1 = user1.getIcon();
        icon2 = user2.getIcon();

        activePlayer = 1;

        int x,y, idInt;
        String idStr;
        ImageView idIV;
        for (x=0; x<=6; x++){
            for (y=0; y<=6; y++){
                idStr = "iv" + x + "x" + y;
                idInt = getResources().getIdentifier(idStr, "id", getPackageName());
                idIV = findViewById(idInt);
                idIV.setTag(0);
            }
        }

        ImageButton btn;
        int ctr, id1, idIcon;
        for (ctr=0; ctr<=6; ctr++){
            id1 = getResources().getIdentifier("btn" + ctr, "id", getPackageName());
            btn = findViewById(id1);
            idIcon = getResources().getIdentifier(icon1, "drawable", getPackageName());
            btn.setImageResource(idIcon);
        }
        ImageView ivIconActive = findViewById(R.id.p1icon);
        ivIconActive.setAlpha(1f);
        ImageView ivIconInactive = findViewById(R.id.p2icon);
        ivIconInactive.setAlpha(.5f);

        animationSlideDown = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.slide_down);
        animationSlideDown.setAnimationListener(this);

        svc=new Intent(this, BackgroundSoundService.class);
        svc.putExtra("media", String.valueOf(R.raw.bgm_bingo));
    }

    public void didTapButtonBtn0(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.btn0);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        imageChange(0);
    }

    public void didTapButtonBtn1(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.btn1);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        imageChange(1);
    }

    public void didTapButtonBtn2(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.btn2);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        imageChange(2);
    }

    public void didTapButtonBtn3(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.btn3);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        imageChange(3);
    }

    public void didTapButtonBtn4(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.btn4);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        imageChange(4);
    }

    public void didTapButtonBtn5(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.btn5);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        imageChange(5);
    }

    public void didTapButtonBtn6(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.btn6);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        imageChange(6);
    }

    private void imageChange(int col){
        //code imageChange: to change the imageview to the icon of the player
        String y = "btn" + col;
        int btnID = getResources().getIdentifier(y, "id", getPackageName());
        ImageButton button = findViewById(btnID);
        ImageView iv=null;
        ImageButton btn;
        Boolean top = false;
        int i =6;
        String id;
        int ivID, x;
        id = "";
        if (activePlayer==1){
            ImageView ivIconActive = findViewById(R.id.p1icon);
            ivIconActive.setAlpha(.5f);
            ImageView ivIconInactive = findViewById(R.id.p2icon);
            ivIconInactive.setAlpha(1f);

            while (top == false && i>=0){
                id = "iv" + i + "x" + col;  //id of cell
                ivID = getResources().getIdentifier(id, "id", getPackageName());
                iv = findViewById(ivID);

                if (iv.getDrawable()==null){
                    x = getResources().getIdentifier(icon1, "drawable", getPackageName());
                    iv.setImageResource(x);
                    top = true;
                }
                if (i==0){
                    button.setImageResource(R.drawable.logo);
                    button.setEnabled(false);
                    gameCtr++;
                    if (filledUpAllCells()==true){
                        // Get instance of Vibrator from current Context
                        Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                        // Vibrate for 1000 milliseconds
                        v.vibrate(1000);

                        Intent intent = new Intent(four_in_a_row.this, winner.class);
                        UserVersusUser uvu;
                        int uvuID = db.getMaxUVUID();
                        if (uvuID == -1)
                            uvuID = 0;
                        else
                            uvuID++;

                        uvu = new UserVersusUser(uvuID, user1, 0, user2, 0, "bingobear");
                        intent.putExtra("winner", uvu);

                        db.insertMatch(uvu);
                        intent.putExtra("user1", user1);
                        intent.putExtra("user2", user2);
                        intent.putExtra("winnerscore", 0);
                        finish();
                        startActivity(intent);
                    }
                }
                i--;
            }
            iv.setTag(1);

            if (checkLine(id)==true){
                // Get instance of Vibrator from current Context
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // Vibrate for 1000 milliseconds
                v.vibrate(1000);

                answerArr.add(id);
                int idCellInt;
                ImageView ivCell;
                for (int ctr=0; ctr<answerArr.size(); ctr++){
                    idCellInt = getResources().getIdentifier(answerArr.get(ctr), "id", getPackageName());
                    ivCell = findViewById(idCellInt);
                    ivCell.setImageResource(R.drawable.logo);
                }
                disableButtons();
                new CountDownTimer(1500, 500) {
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        Intent i = new Intent(four_in_a_row.this, winner.class);
                        UserVersusUser uvu;
                        int uvuID = db.getMaxUVUID();
                        if (uvuID == -1)
                            uvuID = 0;
                        else
                            uvuID++;

                        uvu = new UserVersusUser(uvuID, user1, 1, user2, 0, "bingobear");
                        i.putExtra("winner", uvu);

                        db.insertMatch(uvu);
                        i.putExtra("user1", user1);
                        i.putExtra("user2", user2);
                        i.putExtra("winnerscore", 1);
                        finish();
                        startActivity(i);
                    }
                }.start();
            }
            else{
                ImageView iv2;
                for (i=0; i<=6; i++){
                    String z = "iv0x" + i; //id of topmost cell;
                    int a = getResources().getIdentifier(z, "id", getPackageName());
                    iv2 = findViewById(a);
                    if (iv2.getDrawable() == null) {
                        id = "btn" + i;
                        ivID = getResources().getIdentifier(id, "id", getPackageName());
                        x = getResources().getIdentifier(icon2, "drawable", getPackageName());
                        btn = findViewById(ivID);
                        btn.setImageResource(x);
                    }
                }

                activePlayer=2;
            }
        }
        //if active player is 2
        else{
            ImageView ivIconActive = findViewById(R.id.p1icon);
            ivIconActive.setAlpha(1f);
            ImageView ivIconInactive = findViewById(R.id.p2icon);
            ivIconInactive.setAlpha(.5f);

            while (top == false && i>=0){
                id = "iv" + i + "x" + col;  //id of cell
                ivID = getResources().getIdentifier(id, "id", getPackageName());
                iv = findViewById(ivID);

                if (iv.getDrawable()==null){
                    x = getResources().getIdentifier(icon2, "drawable", getPackageName());
                    iv.setImageResource(x);
                    top = true;
                }
                if (i==0){
                    button.setImageResource(R.drawable.logo);
                    button.setEnabled(false);
                    gameCtr++;
                    if (filledUpAllCells()==true){
                        Intent intent = new Intent(four_in_a_row.this, winner.class);
                        UserVersusUser uvu;
                        int uvuID = db.getMaxUVUID();
                        if (uvuID == -1)
                            uvuID = 0;
                        else
                            uvuID++;

                        uvu = new UserVersusUser(uvuID, user1, 0, user2, 0, "bingobear");
                        intent.putExtra("winner", uvu);

                        db.insertMatch(uvu);
                        intent.putExtra("user1", user1);
                        intent.putExtra("user2", user2);
                        intent.putExtra("winnerscore", 0);
                        finish();
                        startActivity(intent);
                    }
                }
                i--;
            }

            iv.setTag(2);
            if (checkLine(id)==true){
                // Get instance of Vibrator from current Context
                Vibrator v = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);

                // Vibrate for 1000 milliseconds
                v.vibrate(1000);

                answerArr.add(id);
                int idCellInt;
                ImageView ivCell;
                for (int ctr=0; ctr<answerArr.size(); ctr++){
                    idCellInt = getResources().getIdentifier(answerArr.get(ctr), "id", getPackageName());
                    ivCell = findViewById(idCellInt);
                    ivCell.setImageResource(R.drawable.logo);
                }
                disableButtons();
                new CountDownTimer(1500, 500) {
                    public void onTick(long millisUntilFinished) {

                    }

                    public void onFinish() {
                        Intent i = new Intent(four_in_a_row.this, winner.class);
                        UserVersusUser uvu;
                        int uvuID = db.getMaxUVUID();
                        if (uvuID == -1)
                            uvuID = 0;
                        else
                            uvuID++;

                        uvu = new UserVersusUser(uvuID, user2, 1, user1, 0, "bingobear");
                        i.putExtra("winner", uvu);

                        db.insertMatch(uvu);
                        i.putExtra("user1", user1);
                        i.putExtra("user2", user2);
                        i.putExtra("winnerscore", 1);
                        stopService(svc);

                        finish();
                        startActivity(i);
                    }
                }.start();
            }
            else {
                for (i = 0; i <= 6; i++) {
                    String z = "iv0x" + i; //id of topmost cell
                    int a = getResources().getIdentifier(z, "id", getPackageName());
                    iv = findViewById(a);
                    if (iv.getDrawable() == null) {
                        id = "btn" + i;
                        ivID = getResources().getIdentifier(id, "id", getPackageName());
                        x = getResources().getIdentifier(icon1, "drawable", getPackageName());
                        btn = findViewById(ivID);
                        btn.setImageResource(x);
                    }
                }

                activePlayer = 1;
            }
        }
        //code imageChange ends here
    }

    private boolean filledUpAllCells(){

        if (gameCtr == 7)
            return true;
        else
            return false;
    }

    private void disableButtons(){
        for (int i = 0; i <= 6; i++) {
            String id;
            int ivID;
            ImageButton btn;
            id = "btn" + i;
            ivID = getResources().getIdentifier(id, "id", getPackageName());
            btn = findViewById(ivID);
            btn.setImageResource(R.drawable.logo);
            btn.setEnabled(false);

        }
    }

    //function checks if player formed line of 4 icons
    //parameter is the id of the cell e.g. iv0x0
    private boolean checkLine(String cell){
        //cell is the clicked cell
        int lineCtr=1;
        int ivID = getResources().getIdentifier(cell, "id", getPackageName());
        ImageView iv = findViewById(ivID);
        int row, col;
        row = Integer.valueOf(cell.substring(2,3));
        col = Integer.valueOf(cell.substring(4));

        lineCtr = getDownRowNum(iv, row, col);
        if (lineCtr >= 4)
            return true;
        answerArr.clear();
        lineCtr = getLeftColNum(iv, row, col) + getRightColNum(iv, row, col) -1;
        if (lineCtr >= 4)
            return true;
        answerArr.clear();
        lineCtr = getLeftUpperDiagonal(iv, row, col) + getLeftDownDiagonal(iv, row, col) - 1;
        if (lineCtr >= 4)
            return true;
        answerArr.clear();
        lineCtr = getRightUpperDiagonal(iv, row, col) + getRightDownDiagonal(iv, row, col) -1;
        if (lineCtr >= 4)
            return true;
        answerArr.clear();
        return false;
    }

    private int getDownRowNum(ImageView iv, int row, int col){
        int downRow, id, lineCtr = 1;
        String idAdj;
        ImageView ivAdj;
        downRow = row + 1;
        while (downRow<=6){
            idAdj = "iv" + downRow + "x" + col;
            id = getResources().getIdentifier(idAdj, "id", getPackageName());
            ivAdj = findViewById(id);
            if (ivAdj.getTag() == iv.getTag()){
                lineCtr++;
                answerArr.add(idAdj);
            }
            else{
                return lineCtr;
            }
            downRow++;
        }
        return lineCtr;
    }

    private int getLeftColNum(ImageView iv, int row, int col){
        int leftCol, id, lineCtr = 1;
        String idAdj;
        ImageView ivAdj;
        leftCol = col - 1;
        while (leftCol>=0){
            idAdj = "iv" + row + "x" + leftCol;
            id = getResources().getIdentifier(idAdj, "id", getPackageName());
            ivAdj = findViewById(id);
            if (ivAdj.getTag() == iv.getTag()){
                lineCtr++;
                answerArr.add(idAdj);
            }
            else{
                return lineCtr;
            }
            leftCol--;
        }
        return lineCtr;
    }

    private int getRightColNum(ImageView iv, int row, int col){
        int rightCol, id, lineCtr = 1;
        String idAdj;
        ImageView ivAdj;
        rightCol = col + 1;
        while (rightCol<=6){
            idAdj = "iv" + row + "x" + rightCol;
            id = getResources().getIdentifier(idAdj, "id", getPackageName());
            ivAdj = findViewById(id);
            if (ivAdj.getTag() == iv.getTag()){
                lineCtr++;
                answerArr.add(idAdj);
            }
            else{
                return lineCtr;
            }
            rightCol++;
        }
        return lineCtr;
    }

    private int getLeftUpperDiagonal(ImageView iv, int row, int col){
        int lineCtr = 1;
        int gludRow, gludCol, id;
        String idAdj="";
        ImageView ivAdj;
        gludRow = row - 1;
        gludCol = col - 1;
        while (gludRow >=0 && gludRow<=6 && gludCol>=0 && gludCol <=6){
            idAdj = "iv" + gludRow + "x" + gludCol;
            id = getResources().getIdentifier(idAdj, "id", getPackageName());
            ivAdj = findViewById(id);
            if (ivAdj.getTag() == iv.getTag()){
                lineCtr++;
                answerArr.add(idAdj);
            }
            else{
                return lineCtr;
            }
            gludCol--;
            gludRow--;
        }
        return lineCtr;
    }

    private int getLeftDownDiagonal(ImageView iv, int row, int col){
        int lineCtr = 1;
        int glddRow, glddCol, id;
        String idAdj="";
        ImageView ivAdj;
        glddRow = row + 1;
        glddCol = col + 1;
        while (glddRow >=0 && glddRow<=6 && glddCol>=0 && glddCol <=6){
            idAdj = "iv" + glddRow + "x" + glddCol;
            id = getResources().getIdentifier(idAdj, "id", getPackageName());
            ivAdj = findViewById(id);
            if (ivAdj.getTag() == iv.getTag()){
                lineCtr++;
                answerArr.add(idAdj);
            }
            else{
                return lineCtr;
            }
            glddCol++;
            glddRow++;
        }
        return lineCtr;
    }

    private int getRightUpperDiagonal(ImageView iv, int row, int col){
        int lineCtr = 1;
        int grudRow, grudCol, id;
        String idAdj="";
        ImageView ivAdj;
        grudRow = row - 1;
        grudCol = col + 1;
        while (grudRow >=0 && grudRow<=6 && grudCol>=0 && grudCol <=6){
            idAdj = "iv" + grudRow + "x" + grudCol;
            id = getResources().getIdentifier(idAdj, "id", getPackageName());
            ivAdj = findViewById(id);
            if (ivAdj.getTag() == iv.getTag()){
                lineCtr++;
                answerArr.add(idAdj);
            }
            else{
                return lineCtr;
            }
            grudCol++;
            grudRow--;
        }
        return lineCtr;
    }

    private int getRightDownDiagonal(ImageView iv, int row, int col){
        int lineCtr = 1;
        int grddRow, grddCol, id;
        String idAdj="";
        ImageView ivAdj;
        grddRow = row + 1;
        grddCol = col - 1;
        while (grddRow >=0 && grddRow<=6 && grddCol>=0 && grddCol <=6){
            idAdj = "iv" + grddRow + "x" + grddCol;
            id = getResources().getIdentifier(idAdj, "id", getPackageName());
            ivAdj = findViewById(id);
            if (ivAdj.getTag() == iv.getTag()){
                lineCtr++;
                answerArr.add(idAdj);
            }
            else{
                return lineCtr;
            }
            grddCol--;
            grddRow++;
        }
        return lineCtr;
    }

    @Override
    public void onBackPressed(){

    }

    @Override
    public void onAnimationStart(Animation animation) {

    }

    @Override
    public void onAnimationEnd(Animation animation) {

    }

    @Override
    public void onAnimationRepeat(Animation animation) {

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
