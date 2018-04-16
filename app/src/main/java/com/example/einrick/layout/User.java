package com.example.einrick.layout;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by Lenovo on 03/04/2018.
 */

public class User implements Parcelable {
    private int id;
    private String username;
    private String icon;
    private int status;

    public User(int id, String name, String pic, int status){
        this.setId(id);
        this.username = name;
        this.icon = pic;
        this.setStatus(status);
    }


    protected User(Parcel in) {
        id = in.readInt();
        username = in.readString();
        icon = in.readString();
        status = in.readInt();
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeInt(id);
        parcel.writeString(username);
        parcel.writeString(icon);
        parcel.writeInt(status);
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
