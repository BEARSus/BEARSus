package com.example.einrick.layout;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;

/**
 * Created by Lenovo on 03/04/2018.
 */

public class DBHandler extends SQLiteOpenHelper {
    //database version
    private static final int DATABASE_VERSION = 1;
    //database name
    private static final String DATABASE_NAME = "users";
    //shops table name
    private static final String TABLE_USERS  = "users";
    private static final String TABLE_UvsU  = "usersVersusUsers";
    //table columns
    private static final String KEY_ID = "userid";
    private static final String KEY_NAME = "username";
    private static final String KEY_ICON = "icon";
    private static final String KEY_STATUS = "status";
    private static final String KEY_UVU_ID = "uvuID";
    private static final String KEY_WINNER = "winner";
    private static final String KEY_WINNER_SCORE = "winnerScore";
    private static final String KEY_LOSER = "loser";
    private static final String KEY_LOSER_SCORE = "loserScore";
    private static final String KEY_GAME = "game";

    public DBHandler(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATE_USERS_TABLE = "CREATE TABLE " + TABLE_USERS + "(" + KEY_ID + " INTEGER PRIMARY KEY," +
                KEY_NAME +  " TEXT," + KEY_ICON + " TEXT," + KEY_STATUS + " INTEGER" + ")";
        db.execSQL(CREATE_USERS_TABLE);
        Log.d("INSERTING: ", "Inserting..." + CREATE_USERS_TABLE);
        /*"CREATE TABLE "+employeeTable+"
        ("+colID+" INTEGER PRIMARY KEY AUTOINCREMENT, "+
        colName+" TEXT, "+colAge+" Integer, "+colDept+"
        INTEGER NOT NULL ,FOREIGN KEY ("+colDept+") REFERENCES
        "+deptTable+" ("+colDeptID+"));"*/
        String CREATE_UVU_TABLE = "CREATE TABLE " + TABLE_UvsU + "(" + KEY_UVU_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                KEY_WINNER +  " INTEGER," + KEY_WINNER_SCORE +  " INTEGER," + KEY_LOSER +  " INTEGER," +
                KEY_LOSER_SCORE + " INTEGER," + KEY_GAME + " TEXT, FOREIGN KEY (" + KEY_WINNER + ") REFERENCES " + TABLE_USERS +
                " (" + KEY_ID + ")" + ")";
        db.execSQL(CREATE_UVU_TABLE);
        //db.execSQL("INSERT INTO " + TABLE_USERS + " VALUES(1,'Tony','grizzicon', 1)");
        String insert = "INSERT INTO " + TABLE_UvsU + " VALUES(1, 1, 2, 1, 2, 'patterngame')";
        db.execSQL(insert);
        /*
        String insert = "INSERT INTO " + TABLE_USERS + " VALUES(1,'Tony','grizzicon', 1)";
        db.execSQL(insert);
        insert = "INSERT INTO " + TABLE_USERS + " VALUES(2,'Steve','pandaicon', 1)";
        db.execSQL(insert);
        insert = "INSERT INTO " + TABLE_USERS + " VALUES(3,'Bucky','icebearicon', 1)";
        db.execSQL(insert);
        insert = "INSERT INTO " + TABLE_UvsU + " VALUES(1, 1, 2, 1, 2, 'patterngame')";
        db.execSQL(insert);
        insert = "INSERT INTO " + TABLE_UvsU + " VALUES(2, 1, 3, 2, 1, 'patterngame')";
        db.execSQL(insert);
        insert = "INSERT INTO " + TABLE_UvsU + " VALUES(3, 2, 10, 1, 2, 'patterngame')";
        db.execSQL(insert);
        insert = "INSERT INTO " + TABLE_UvsU + " VALUES(4, 1, 2, 2, 1, 'patterngame')";
        db.execSQL(insert);
        */
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVer, int newVer) {
        //Drop older table if existed
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USERS);
        //create tables again
        onCreate(db);
    }

    //insert user
    public void insertUser(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername());
        values.put(KEY_ICON, user.getIcon());
        values.put(KEY_STATUS, user.getStatus());
        db.insert(TABLE_USERS, null, values);
        db.close(); //closing db connection
    }

    //insert match
    public void insertMatch(UserVersusUser uvu){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_UVU_ID, uvu.getId());
        values.put(KEY_WINNER, uvu.getWinner().getId());
        values.put(KEY_WINNER_SCORE, uvu.getScoreWin());
        values.put(KEY_LOSER, uvu.getLoser().getId());
        values.put(KEY_LOSER_SCORE, uvu.getScoreLose());
        values.put(KEY_GAME, uvu.getGame());
        db.insert(TABLE_UvsU, null, values);
        db.close(); //closing db connection
    }

    //get all users
    public ArrayList<User> getAllUsers(){
        ArrayList<User> usersList = new ArrayList<User>();

        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_STATUS + " = 1 ORDER BY " + KEY_NAME + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            do{
                User u = new User(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2), Integer.parseInt(c.getString(3)));
                usersList.add(u);
            } while (c.moveToNext());
        }
        return usersList;
    }

    //get all users (active and inactive)
    public ArrayList<User> getAllUsersIncInactive(){
        ArrayList<User> usersList = new ArrayList<User>();

        String query = "SELECT * FROM " + TABLE_USERS + " ORDER BY " + KEY_NAME + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            do{
                User u = new User(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2), Integer.parseInt(c.getString(3)));
                usersList.add(u);
            } while (c.moveToNext());
        }
        return usersList;
    }

    //getting one user (select)
    public User getUser(int id){
        SQLiteDatabase db = this.getReadableDatabase();
        //Cursor cursor = db.query(TABLE_USERS, new String[] { KEY_ID, KEY_NAME, KEY_ICON, KEY_STATUS }, KEY_ID + "=?",
                //new String[] { String.valueOf(id) }, null, null, null, null);
        String query = "SELECT * FROM " + TABLE_USERS + " WHERE " + KEY_ID + " = " + id;
        Cursor cursor = db.rawQuery(query, null);
        if  (cursor!=null) {
            cursor.moveToFirst();
        }
        User contact = new User(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2), Integer.parseInt(cursor.getString(3)));
        return contact;
    }

    //get all users
    public ArrayList<UserVersusUser> getAllMatches(){
        ArrayList<UserVersusUser> list = new ArrayList<UserVersusUser>();

        String query = "SELECT * FROM " + TABLE_UvsU + " ORDER BY " + KEY_UVU_ID + " ASC";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            do{
                //User u = new User(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2));
                int id = Integer.parseInt(c.getString(0));
                User winUser = getUser(Integer.parseInt(c.getString(1)));
                int scoreW = Integer.parseInt(c.getString(2));
                User loseUser = getUser(Integer.parseInt(c.getString(3)));
                int scoreL = Integer.parseInt(c.getString(4));
                String matchGame = c.getString(5);
                UserVersusUser uvu = new UserVersusUser(id, winUser, scoreW, loseUser, scoreL, matchGame);
                list.add(uvu);
            } while (c.moveToNext());
        }
        return list;
    }

    //get max id
    public int getMaxID(){
        int id;
        String query = "SELECT MAX("+ KEY_ID +") FROM " + TABLE_USERS;
        SQLiteDatabase db = this    .getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor!=null){
            cursor.moveToFirst();
            id = cursor.getInt(0);
        }
        else{
            id = -1;
        }
        return id;
    }

    //get max id
    public int getMaxUVUID(){
        int id;
        String query = "SELECT MAX("+ KEY_UVU_ID +") FROM " + TABLE_UvsU;
        SQLiteDatabase db = this    .getReadableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        if (cursor!=null){
            cursor.moveToFirst();
            id = cursor.getInt(0);
        }
        else{
            id = -1;
        }
        return id;
    }

    //get all high scores
    public ArrayList<UserVersusUser> getHighScores(String game){
        ArrayList<UserVersusUser> list = new ArrayList<UserVersusUser>();
        String query = "SELECT * FROM " + TABLE_UvsU + " U1 JOIN (SELECT DISTINCT " + KEY_WINNER_SCORE + " FROM " +
                TABLE_UvsU + " WHERE " + KEY_GAME + " = '" + game + "' ORDER BY " + KEY_WINNER_SCORE + " DESC LIMIT 5) U2 ON U1." +
                KEY_WINNER_SCORE + " = U2." + KEY_WINNER_SCORE + " WHERE U1." + KEY_WINNER_SCORE + " != U1." + KEY_LOSER_SCORE +" GROUP BY U1." +
                KEY_UVU_ID + " ORDER BY U2." + KEY_WINNER_SCORE + " DESC";
        Log.d("Reading: ", query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            do{
                //User u = new User(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2));
                int id = Integer.parseInt(c.getString(0));
                User winUser = getUser(Integer.parseInt(c.getString(1)));
                int scoreW = Integer.parseInt(c.getString(2));
                User loseUser = getUser(Integer.parseInt(c.getString(3)));
                int scoreL = Integer.parseInt(c.getString(4));
                UserVersusUser uvu = new UserVersusUser(id, winUser, scoreW, loseUser, scoreL, game);
                list.add(uvu);
            } while (c.moveToNext());
        }
        return list;
    }

    //get all high scores
    public ArrayList<UserVersusUser> getHighScoresBB(){
        ArrayList<UserVersusUser> list = new ArrayList<UserVersusUser>();
        /*
        String query = "SELECT * FROM " + TABLE_UvsU + " U1 JOIN (SELECT DISTINCT " + KEY_WINNER_SCORE + " FROM " +
                TABLE_UvsU + " WHERE " + KEY_GAME + " = '" + game + "' ORDER BY " + KEY_WINNER_SCORE + " DESC LIMIT 5) U2 ON U1." +
                KEY_WINNER_SCORE + " = U2." + KEY_WINNER_SCORE + " GROUP BY U1." +
                KEY_UVU_ID + " ORDER BY U2." + KEY_WINNER_SCORE + " DESC";
                */
        String query = "SELECT UV1." + KEY_UVU_ID + ", UV1." + KEY_WINNER + ", UV2.SCORE, UV1." + KEY_LOSER + ", UV1." + KEY_LOSER_SCORE + " FROM " + TABLE_UvsU + " UV1 JOIN (SELECT " + KEY_UVU_ID +
                ", " + KEY_WINNER + ", SUM(" + KEY_WINNER_SCORE + ") AS 'SCORE' FROM " + TABLE_UvsU + " WHERE " + KEY_GAME + "='bingobear' GROUP BY "+ KEY_WINNER +" ORDER BY SCORE DESC LIMIT 5)UV2 ON UV1." + KEY_UVU_ID +
                "=UV2." + KEY_UVU_ID + " WHERE UV1." + KEY_UVU_ID + "=UV2." + KEY_UVU_ID;
        Log.d("Reading: ", query);
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor c = db.rawQuery(query, null);
        if (c.moveToFirst()){
            do{
                //User u = new User(Integer.parseInt(c.getString(0)), c.getString(1), c.getString(2));
                int id = Integer.parseInt(c.getString(0));
                User winUser = getUser(Integer.parseInt(c.getString(1)));
                int scoreW = Integer.parseInt(c.getString(2));
                User loseUser = getUser(Integer.parseInt(c.getString(3)));
                int scoreL = Integer.parseInt(c.getString(4));
                UserVersusUser uvu = new UserVersusUser(id, winUser, scoreW, loseUser, scoreL, "bingobear");
                list.add(uvu);
            } while (c.moveToNext());
        }
        return list;
    }

    //update
    public int updateUserIcon(User user){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_NAME, user.getUsername());
        values.put(KEY_ICON, user.getIcon());

        //updating row
        return db.update(TABLE_USERS, values, KEY_ID + "=?", new String[]{String.valueOf(user.getId())});
    }

    //delete
    public int archiveUserIcon(int id){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_STATUS, 0);

        //updating row
        return db.update(TABLE_USERS, values, KEY_ID + "=?", new String[]{String.valueOf(id)});
    }


}
