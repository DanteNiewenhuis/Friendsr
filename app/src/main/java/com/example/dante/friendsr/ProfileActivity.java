package com.example.dante.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        Friend retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        TextView Name = findViewById(R.id.Name);
        Name.setText(retrievedFriend.getName());

        TextView Bio = findViewById(R.id.Bio);
        Bio.setText(retrievedFriend.getBio());

        ImageView img = findViewById(R.id.Picture);
        img.setImageResource(retrievedFriend.getDrawableId());

    }
}
