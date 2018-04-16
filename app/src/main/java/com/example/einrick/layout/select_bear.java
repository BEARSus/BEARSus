package com.example.einrick.layout;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.graphics.Color;
import android.graphics.Typeface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class select_bear extends AppCompatActivity{
    private ArrayList<User> usersList;
    private ArrayList<String> usernamesList;
    private String icon;
    private Spinner spinner;
    private ImageView bearIcon;
    private User user;
    private EditText addName;
    private DBHandler db;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_bear);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        addName = findViewById(R.id.customName);

        icon = "blank";

        //create database
        db = new DBHandler(this);

        //get all users
        Log.d("Reading: ", "Reading...");
        usersList = db.getAllUsers();
        for (int i=0; i<usersList.size(); i++){
            String log = usersList.get(i).getId() + "//" + usersList.get(i).getUsername() + "//" + usersList.get(i).getIcon() + "//" + Integer.toString(usersList.get(i).getStatus());
            Log.d("User: ", log);
        }

        //Intent i = getIntent();
        //usersList = i.getParcelableArrayListExtra("usersList");
        spinner = (Spinner) findViewById(R.id.spinner);
        bearIcon = findViewById(R.id.bearIcon);

        usernamesList = new ArrayList<String>();
        usernamesList.add("SELECT USER");
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, usernamesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        setSpinnerContent();

        TextView tx = findViewById(R.id.eliName);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
        tx.setTypeface(custom_font);

        tx = findViewById(R.id.einrickName);
        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
        tx.setTypeface(custom_font);

        tx = findViewById(R.id.psyName);
        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
        tx.setTypeface(custom_font);
    }

    private void setSpinnerContent(){
        //load usernames in the spinner
        if (usersList.size()!=0){
            for (int ctr=0; ctr<usersList.size(); ctr++){
                usernamesList.add(usersList.get(ctr).getUsername());
            }
            adapter.notifyDataSetChanged();

            spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                @Override
                public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                    int index = spinner.getSelectedItemPosition();
                    if (index !=0){
                        index--;
                        icon = usersList.get(index).getIcon();
                        user = usersList.get(index);
                    }
                    else{
                        icon = "blank";
                    }

                    int id;
                    ImageButton iconBtn;
                    TextView eliName = findViewById(R.id.eliName);
                    TextView psyName = findViewById(R.id.psyName);
                    TextView einrickName = findViewById(R.id.einrickName);

                    if (icon.equals("grizzicon")){
                        id = getResources().getIdentifier("grizzIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        id = getResources().getIdentifier("pandaIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        id = getResources().getIdentifier("icebearIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        eliName.setVisibility(View.INVISIBLE);
                        psyName.setVisibility(View.VISIBLE);
                        einrickName.setVisibility(View.INVISIBLE);
                        bearIcon.setImageResource(R.drawable.grizzselect);
                    }
                    else if(icon.equals("pandaicon")){
                        id = getResources().getIdentifier("grizzIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        id = getResources().getIdentifier("pandaIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        id = getResources().getIdentifier("icebearIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        eliName.setVisibility(View.INVISIBLE);
                        psyName.setVisibility(View.INVISIBLE);
                        einrickName.setVisibility(View.VISIBLE);
                        bearIcon.setImageResource(R.drawable.pandaselect);
                    }
                    else if(icon.equals("icebearicon")){
                        id = getResources().getIdentifier("grizzIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        id = getResources().getIdentifier("pandaIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        id = getResources().getIdentifier("icebearIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        eliName.setVisibility(View.VISIBLE);
                        psyName.setVisibility(View.INVISIBLE);
                        einrickName.setVisibility(View.INVISIBLE);
                        bearIcon.setImageResource(R.drawable.iceselect);
                    }
                    else {
                        id = getResources().getIdentifier("grizzIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        id = getResources().getIdentifier("pandaIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        id = getResources().getIdentifier("icebearIconbtn", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        eliName.setVisibility(View.INVISIBLE);
                        psyName.setVisibility(View.INVISIBLE);
                        einrickName.setVisibility(View.INVISIBLE);
                        bearIcon.setImageDrawable(null);
                    }

                    if (spinner.getSelectedItemPosition()!=0)
                        addName.setVisibility(View.INVISIBLE);

                }

                @Override
                public void onNothingSelected(AdapterView<?> parentView) {
                    // your code here
                }
            });
        }
    }

    public void didTapButton(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.selectBearbtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        //to check if the user is an existing user or a new user
        if (spinner.getSelectedItemPosition()==0){   //if user is new
            //to check if user inputted a new username
            //if user is new, add to database
            if (addName.getText().length()>0 && !addName.getText().equals(" ")){
                String username = addName.getText().toString();
                //check if username already exists
                if (checkIfExists(username) == true) {
                    addName.setText("");
                    Toast.makeText(this, "User already EXISTS.", Toast.LENGTH_LONG).show();
                }
                else if (icon.equals("blank")){
                    Toast.makeText(this, "Please select ICON.", Toast.LENGTH_LONG).show();
                }
                else{
                    int userID;
                    userID = db.getMaxID();
                    if (userID == -1)
                        userID = 0;
                    user = new User(userID+1, username, icon, 1);
                    db.insertUser(user);
                    usersList = db.getAllUsers();
                    Intent i = new Intent(select_bear.this, select_bear_2.class);
                    i.putExtra("user1", user);
                    startActivity(i);
                    finish();
                }
            }
            //user did not input a new username
            else{
                Toast.makeText(this, "Please input a NEW USERNAME.", Toast.LENGTH_LONG).show();
            }
        }
        else{       //if user is old, update database
            user = new User(user.getId(), user.getUsername(), icon, 1);
            db.updateUserIcon(user);
            usersList = db.getAllUsers();
            Intent i = new Intent(select_bear.this, select_bear_2.class);
            i.putExtra("user1", user);
            startActivity(i);
        }
    }

    public void didTapButtonPlus(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.addNamebtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        addName.setVisibility(View.VISIBLE);
        spinner.setSelection(0);
    }

    public void didTapButtonIceBear(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.icebearIconbtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        int id;
        ImageButton iconBtn;
        id = getResources().getIdentifier("grizzIconbtn", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);
        id = getResources().getIdentifier("pandaIconbtn", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);
        id = getResources().getIdentifier("icebearIconbtn", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(1f);

        TextView eliName = findViewById(R.id.eliName);
        TextView psyName = findViewById(R.id.psyName);
        TextView einrickName = findViewById(R.id.einrickName);
        eliName.setVisibility(View.VISIBLE);
        psyName.setVisibility(View.INVISIBLE);
        einrickName.setVisibility(View.INVISIBLE);

        bearIcon.setImageResource(R.drawable.iceselect);

        icon = "icebearicon";
    }

    public void didTapButtonGrizzly(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.grizzIconbtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        int id;
        ImageButton iconBtn;
        id = getResources().getIdentifier("grizzIconbtn", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(1f);
        id = getResources().getIdentifier("pandaIconbtn", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);
        id = getResources().getIdentifier("icebearIconbtn", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);

        TextView eliName = findViewById(R.id.eliName);
        TextView psyName = findViewById(R.id.psyName);
        TextView einrickName = findViewById(R.id.einrickName);
        eliName.setVisibility(View.INVISIBLE);
        psyName.setVisibility(View.VISIBLE);
        einrickName.setVisibility(View.INVISIBLE);

        bearIcon.setImageResource(R.drawable.grizzselect);

        icon="grizzicon";
    }

    public void didTapButtonPanda(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.pandaIconbtn);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        int id;
        ImageButton iconBtn;
        id = getResources().getIdentifier("grizzIconbtn", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);
        id = getResources().getIdentifier("pandaIconbtn", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(1f);
        id = getResources().getIdentifier("icebearIconbtn", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);

        TextView eliName = findViewById(R.id.eliName);
        TextView psyName = findViewById(R.id.psyName);
        TextView einrickName = findViewById(R.id.einrickName);
        eliName.setVisibility(View.INVISIBLE);
        psyName.setVisibility(View.INVISIBLE);
        einrickName.setVisibility(View.VISIBLE);

        bearIcon.setImageResource(R.drawable.pandaselect);

        icon = "pandaicon";
    }

    private boolean checkIfExists(String name){
        for (int ctr=0; ctr<usernamesList.size(); ctr++){
            if (name.equals(usernamesList.get(ctr)))
                return true;
        }
        return false;
    }
}
