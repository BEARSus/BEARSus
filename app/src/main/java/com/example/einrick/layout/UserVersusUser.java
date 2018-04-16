package com.example.einrick.layout;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lenovo on 05/04/2018.
 */

public class UserVersusUser implements Parcelable {
    private int id;
    private User winner;
    private User loser;
    private int scoreWin;
    private int scoreLose;
    private String game;

    public UserVersusUser(int id, User w, int scoreW, User l, int scoreL, String game){
        this.setId(id);
        this.setWinner(w);
        this.setScoreWin(scoreW);
        this.setLoser(l);
        this.setScoreLose(scoreL);
        this.setGame(game);
    }

    protected UserVersusUser(Parcel in) {
        id = in.readInt();
        winner = in.readParcelable(User.class.getClassLoader());
        loser = in.readParcelable(User.class.getClassLoader());
        scoreWin = in.readInt();
        scoreLose = in.readInt();
        game = in.readString();
    }

    public static final Creator<UserVersusUser> CREATOR = new Creator<UserVersusUser>() {
        @Override
        public UserVersusUser createFromParcel(Parcel in) {
            return new UserVersusUser(in);
        }

        @Override
        public UserVersusUser[] newArray(int size) {
            return new UserVersusUser[size];
        }
    };

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public User getWinner() {
        return winner;
    }

    public void setWinner(User winner) {
        this.winner = winner;
    }

    public User getLoser() {
        return loser;
    }

    public void setLoser(User loser) {
        this.loser = loser;
    }

    public int getScoreWin() {
        return scoreWin;
    }

    public void setScoreWin(int scoreWin) {
        this.scoreWin = scoreWin;
    }

    public int getScoreLose() {
        return scoreLose;
    }

    public void setScoreLose(int scoreLose) {
        this.scoreLose = scoreLose;
    }

    public String getGame() {
        return game;
    }

    public void setGame(String game) {
        this.game = game;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeParcelable(winner, i);
        parcel.writeParcelable(loser, i);
        parcel.writeInt(scoreWin);
        parcel.writeInt(scoreLose);
        parcel.writeString(game);
    }
}
