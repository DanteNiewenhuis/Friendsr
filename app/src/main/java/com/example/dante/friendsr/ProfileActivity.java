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
    private String friend_name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent intent = getIntent();
        retrievedFriend = (Friend) intent.getSerializableExtra("clicked_friend");

        // set the name
        TextView Name = findViewById(R.id.Name);
        friend_name = retrievedFriend.getName();
        Name.setText(friend_name);

        // set the bio
        TextView Bio = findViewById(R.id.Bio);
        Bio.setText(retrievedFriend.getBio());

        // set the image
        ImageView img = findViewById(R.id.Picture);
        img.setImageResource(retrievedFriend.getDrawableId());

        // get the rating, if no rating has been given, the rating will be 0
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        float rating = prefs.getFloat("rating_" + friend_name, 0);

        // get the rating
        RatingBar ratingBar = findViewById(R.id.ratingBar);
        ratingBar.setRating(rating);

        // set the ratingbarlistener
        ratingBar.setOnRatingBarChangeListener(new RatingBarListener());
    }

    // set and intent with mode "edit" and the retrieved friend as result 3.
    public void edit_profile(View v) {
        Intent intent = new Intent();
        intent.putExtra("old_friend", retrievedFriend);
        intent.putExtra("mode", "edit");

        setResult(3, intent);
        finish();
    }

    // set and intent with mode "remove" and the retrieved friend as result 3.
    public void remove_profile(View v) {
        Intent intent = new Intent();
        intent.putExtra("old_friend", retrievedFriend);
        intent.putExtra("mode", "remove");

        setResult(3, intent);
        finish();
    }

    private class RatingBarListener implements RatingBar.OnRatingBarChangeListener {

        // changed the rating in the SharedPrefernces if the rating has been changed
        @Override
        public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
            SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);

            if (friend_name != null) {
                SharedPreferences.Editor editor = prefs.edit();
                editor.putFloat("rating_" + friend_name, rating);
                editor.apply();
            }
        }
    }
}
