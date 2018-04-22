package com.example.dante.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;

public class ProfileCreation extends AppCompatActivity {
    ArrayList<Friend> friends;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        Intent intent = getIntent();
        friends = (ArrayList<Friend>) intent.getSerializableExtra("friend_list");
    }

    public void submit (View v) {
        Friend friend = new Friend("test", "dit is een bio", R.drawable.arya);
        friends.add(friend);

        Intent intent = new Intent();
        intent.putExtra("friends_list", friends);

        setResult(2, intent);
        finish();
    }
}
