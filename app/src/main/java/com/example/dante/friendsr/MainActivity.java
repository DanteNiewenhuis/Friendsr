package com.example.dante.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Friend> friends;
    private FriendsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {
            friends = (ArrayList<Friend>) savedInstanceState.getSerializable("friends");
        }
        else {
            friends = new ArrayList<>();

            String[] name_list = {"arya", "cersei", "daenerys", "jon", "jorah", "margaery",
                                  "melisandre", "sansa", "tyrion"};
            for (String name : name_list) {
                String bio = "This is a placeholder for the bio off " + name +
                             ": Lorem ipsum dolor sit amet, consectetur adipiscing elit. In in neque " +
                             "ac arcu venenatis ornare ut sit amet dolor. Pellentesque quis tellus " +
                             "in ante placerat aliquet. Quisque eu risus eget metus ornare dignissim " +
                             "ac vitae leo. Pellentesque sed sagittis nisl. Donec quis elementum " +
                             "velit.";
                int drawID = getResources().getIdentifier(name, "drawable", getPackageName());
                Friend friend = new Friend(name, bio, drawID);
                friends.add(friend);
            }
        }


        update_UI();
    }

    private void update_UI() {
        GridView friend_grid = findViewById(R.id.friends_grid);
        adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
        friend_grid.invalidateViews();
        friend_grid.setOnItemClickListener(new GridItemClickListener());
        friend_grid.setAdapter(adapter);
    }

    public void new_profile(View v) {
        Intent intent = new Intent(MainActivity.this, ProfileCreation.class);
        intent.putExtra("friend_list", friends);
        startActivityForResult(intent, 2);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == 2) {
            if (data != null) {
                friends = (ArrayList<Friend>) data.getSerializableExtra("friends_list");
                update_UI();
            }
        }
    }

    private class GridItemClickListener implements AdapterView.OnItemClickListener {

        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            Friend clickedFriend = (Friend) parent.getItemAtPosition(position);
            Log.d("click_tester", "test test " + clickedFriend.getName());

            Intent intent = new Intent(MainActivity.this, ProfileActivity.class);
            intent.putExtra("clicked_friend", clickedFriend);
            startActivity(intent);
        }
    }

    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        outState.putSerializable("friends", friends);
    }
}
