package com.example.einrick.layout;

/**
 * Created by Lenovo on 09/04/2018.
 */

public class UserItem {
    private int iconId;
    private String username;
    private int userId;

    public UserItem(int id, String name, int userid){
        this.setIconId(id);
        this.setUsername(name);
        this.setUserId(userid);
    }

    public int getIconId() {
        return iconId;
    }

    public void setIconId(int iconId) {
        this.iconId = iconId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
