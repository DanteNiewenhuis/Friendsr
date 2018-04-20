package com.example.dante.friendsr;

import java.io.Serializable;

public class Friend implements Serializable {
    private String name, bio;
    private int drawID;
    private float rating;

    public Friend(String name, String bio, int drawID) {
        this.name = name;
        this.bio = bio;
        this.drawID = drawID;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getName() {
        return name;
    }

    public String getBio() {
        return bio;
    }

    public int getDrawableId() {
        return drawID;
    }

    public float getRating() {
        return rating;
    }
}
