package com.example.einrick.layout;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class select_bear_2 extends AppCompatActivity {
    private ArrayList<User> usersList;
    private ArrayList<String> usernamesList;
    private String icon;
    private Spinner spinner;
    private ImageView bearIcon;
    private User user;
    private User user1;
    private EditText addName;
    private DBHandler db;
    private ArrayAdapter<String> adapter;
    private boolean flag = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_select_bear_2);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LOCKED);

        addName = findViewById(R.id.customName2);

        //create database
        db = new DBHandler(this);

        Intent intent = getIntent();
        user1 = intent.getExtras().getParcelable("user1");
        String iconDisable = user1.getIcon();
        ImageButton imgbtn;
        int id;
        if (iconDisable.equals("grizzicon"))
            id = getResources().getIdentifier("grizzIconbtn2", "id", getPackageName());
        else if (iconDisable.equals("pandaicon"))
            id = getResources().getIdentifier("pandaIconbtn2", "id", getPackageName());
        else
            id = getResources().getIdentifier("icebearIconbtn2", "id", getPackageName());
        imgbtn = findViewById(id);
        imgbtn.setVisibility(View.INVISIBLE);

        ImageView imgview = findViewById(R.id.bearIcon2);
        imgview.setImageDrawable(null);
        imgview.setTag(10);

        TextView tx = findViewById(R.id.eliName2);
        Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
        tx.setTypeface(custom_font);

        tx = findViewById(R.id.einrickName2);
        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
        tx.setTypeface(custom_font);

        tx = findViewById(R.id.psyName2);
        custom_font = Typeface.createFromAsset(getAssets(),  "fonts/DK Frozen Memory.otf");
        tx.setTypeface(custom_font);

        //get all users
        Log.d("Reading: ", "Reading...");
        usersList = db.getAllUsers();
        for (int i=0; i<usersList.size(); i++){
            if (user1.getId() == usersList.get(i).getId())
                usersList.remove(i);
        }
        for (int i=0; i<usersList.size(); i++){
            String log = usersList.get(i).getId() + "//" + usersList.get(i).getUsername() + "//" + usersList.get(i).getStatus();
            Log.d("User: ", log);
        }



        //Intent i = getIntent();
        //usersList = i.getParcelableArrayListExtra("usersList");
        spinner = (Spinner) findViewById(R.id.spinner2);
        bearIcon = findViewById(R.id.bearIcon2);

        usernamesList = new ArrayList<String>();
        usernamesList.add("SELECT USER");
        adapter = new ArrayAdapter<String>(this,
                android.R.layout.simple_spinner_item, usernamesList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        setSpinnerContent();

        icon = "blank";
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
                    TextView eliName = findViewById(R.id.eliName2);
                    TextView psyName = findViewById(R.id.psyName2);
                    TextView einrickName = findViewById(R.id.einrickName2);

                    String iconUser1 = user1.getIcon();


                    if (icon.equals("grizzicon") && !icon.equals(iconUser1)){
                        id = getResources().getIdentifier("grizzIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        id = getResources().getIdentifier("pandaIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        id = getResources().getIdentifier("icebearIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        eliName.setVisibility(View.INVISIBLE);
                        psyName.setVisibility(View.VISIBLE);
                        einrickName.setVisibility(View.INVISIBLE);
                        bearIcon.setImageResource(R.drawable.grizzselect);
                    }
                    else if(icon.equals("pandaicon")  && !icon.equals(iconUser1)){
                        id = getResources().getIdentifier("grizzIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        id = getResources().getIdentifier("pandaIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        id = getResources().getIdentifier("icebearIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        eliName.setVisibility(View.INVISIBLE);
                        psyName.setVisibility(View.INVISIBLE);
                        einrickName.setVisibility(View.VISIBLE);
                        bearIcon.setImageResource(R.drawable.pandaselect);
                    }
                    else if(icon.equals("icebearicon")  && !icon.equals(iconUser1)){
                        id = getResources().getIdentifier("grizzIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        id = getResources().getIdentifier("pandaIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(.5f);
                        id = getResources().getIdentifier("icebearIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        eliName.setVisibility(View.VISIBLE);
                        psyName.setVisibility(View.INVISIBLE);
                        einrickName.setVisibility(View.INVISIBLE);
                        bearIcon.setImageResource(R.drawable.iceselect);
                    }
                    else{
                        id = getResources().getIdentifier("grizzIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        id = getResources().getIdentifier("pandaIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        id = getResources().getIdentifier("icebearIconbtn2", "id", getPackageName());
                        iconBtn = findViewById(id);
                        iconBtn.setAlpha(1f);
                        eliName.setVisibility(View.INVISIBLE);
                        psyName.setVisibility(View.INVISIBLE);
                        einrickName.setVisibility(View.INVISIBLE);
                        bearIcon.setImageDrawable(null);
                        icon = "blank";
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

    public void didTapButton2(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.selectBearbtn2);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        ImageView iconIV = findViewById(R.id.bearIcon2);

        //to check if the user is an existing user or a new user
        if (spinner.getSelectedItemPosition()==0){   //if user is new
            //to check if user inputted a new username
            //if user is new, add to database
            if (addName.getText().length()>0 && !addName.getText().equals(" ") && !user1.getUsername().equals(addName.getText().toString())){
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
                    if (usersList.size()!=0){
                        userID = usersList.get(usersList.size()-1).getId() + 1;
                    }
                    else{
                        userID = 1;
                    }
                    user = new User(userID, username, icon, 1);
                    db.insertUser(user);
                    usersList = db.getAllUsers();
                    Intent i = new Intent(select_bear_2.this, GameOptions.class);
                    i.putExtra("user1", user1);
                    i.putExtra("user2", user);
                    i.putExtra("previousActivity", "select_bear_2");
                    startActivity(i);
                    finish();
                }
            }
            //user did not input a new username
            else{
                if (user1.getUsername().equals(addName.getText().toString())){
                    Toast.makeText(this, "User already taken by player 1.", Toast.LENGTH_LONG).show();
                }else {
                    Toast.makeText(this, "Please input a NEW USERNAME.", Toast.LENGTH_LONG).show();
                }
            }
        }
        else if (icon.equals("blank")) {
            Toast.makeText(this, "Please select a new ICON.", Toast.LENGTH_LONG).show();
        }
        else {   //if user is old, update database
            user.setIcon(icon);
            db.updateUserIcon(new User(user.getId(), user.getUsername(), icon, 1));
            usersList = db.getAllUsers();
            Intent i = new Intent(select_bear_2.this, GameOptions.class);
            i.putExtra("user1", user1);
            i.putExtra("user2", user);
            i.putExtra("previousActivity", "select_bear_2");
            startActivity(i);
        }
    }

    public void didTapButtonPlus2(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.addNamebtn2);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        addName.setVisibility(View.VISIBLE);
        spinner.setSelection(0);
    }

    public void didTapButtonIceBear2(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.icebearIconbtn2);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        int id;
        ImageButton iconBtn;
        id = getResources().getIdentifier("grizzIconbtn2", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);
        id = getResources().getIdentifier("pandaIconbtn2", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);
        id = getResources().getIdentifier("icebearIconbtn2", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(1f);

        TextView eliName = findViewById(R.id.eliName2);
        TextView psyName = findViewById(R.id.psyName2);
        TextView einrickName = findViewById(R.id.einrickName2);
        eliName.setVisibility(View.VISIBLE);
        psyName.setVisibility(View.INVISIBLE);
        einrickName.setVisibility(View.INVISIBLE);

        bearIcon.setImageResource(R.drawable.iceselect);

        icon = "icebearicon";
    }

    public void didTapButtonGrizzly2(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.grizzIconbtn2);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        int id;
        ImageButton iconBtn;
        id = getResources().getIdentifier("grizzIconbtn2", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(1f);
        id = getResources().getIdentifier("pandaIconbtn2", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);
        id = getResources().getIdentifier("icebearIconbtn2", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);

        TextView eliName = findViewById(R.id.eliName2);
        TextView psyName = findViewById(R.id.psyName2);
        TextView einrickName = findViewById(R.id.einrickName2);
        eliName.setVisibility(View.INVISIBLE);
        psyName.setVisibility(View.VISIBLE);
        einrickName.setVisibility(View.INVISIBLE);

        bearIcon.setImageResource(R.drawable.grizzselect);

        icon="grizzicon";
    }

    public void didTapButtonPanda2(View view) {
        ImageButton button = (ImageButton)findViewById(R.id.pandaIconbtn2);
        final Animation myAnim = AnimationUtils.loadAnimation(this, R.anim.bounce);

        // Use bounce interpolator with amplitude 0.2 and frequency 20
        MyBounceInterpolator interpolator = new MyBounceInterpolator(0.2, 20);
        myAnim.setInterpolator(interpolator);

        button.startAnimation(myAnim);

        int id;
        ImageButton iconBtn;
        id = getResources().getIdentifier("grizzIconbtn2", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);
        id = getResources().getIdentifier("pandaIconbtn2", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(1f);
        id = getResources().getIdentifier("icebearIconbtn2", "id", getPackageName());
        iconBtn = findViewById(id);
        iconBtn.setAlpha(.5f);

        TextView eliName = findViewById(R.id.eliName2);
        TextView psyName = findViewById(R.id.psyName2);
        TextView einrickName = findViewById(R.id.einrickName2);
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
