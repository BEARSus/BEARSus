package com.example.einrick.layout;

/**
 * Created by Lenovo on 09/04/2018.
 */

public class HighScoreItem {
    private int icon;
    private String name;
    private int score;

    public HighScoreItem(int icon, String name, int score){
        this.icon = icon;
        this.name = name;
        this.score = score;
    }

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
        this.icon = icon;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
