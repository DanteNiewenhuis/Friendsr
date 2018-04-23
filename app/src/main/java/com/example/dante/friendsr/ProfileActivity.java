package com.example.dante.friendsr;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

public class ProfileActivity extends AppCompatActivity {
    private Friend retrievedFriend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        TextView Name = findViewById(R.id.Name);
        String name_string = retrievedFriend.getName();
        Name.setText(name_string);

        TextView Bio = findViewById(R.id.Bio);
        Bio.setText(retrievedFriend.getBio());

        ImageView img = findViewById(R.id.Picture);
        img.setImageResource(retrievedFriend.getDrawableId());

        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        float rating = prefs.getFloat("rating_" + name_string, 0);

        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(rating);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putString("name", name_string);
        editor.apply();

        ratingBar.setOnRatingBarChangeListener(new RatingBarListener());
    }

    public void edit_profile(View v) {
        Intent intent = new Intent();
        intent.putExtra("old_friend", retrievedFriend);
        intent.putExtra("mode", "edit");

        setResult(3, intent);
        finish();
    }

    public void remove_profile(View v) {
        Intent intent = new Intent();
        intent.putExtra("old_friend", retrievedFriend);
        intent.putExtra("mode", "remove");

        setResult(3, intent);
        finish();
    }

    private class RatingBarListener implements RatingBar.OnRatingBarChangeListener {

        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
            String name = prefs.getString("name", null);

            if (name != null) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat("rating_" + name, rating);
                editor.apply();
            }
        }
    }
}
