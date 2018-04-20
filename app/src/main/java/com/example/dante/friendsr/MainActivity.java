package com.example.dante.friendsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.GridView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    private ArrayList<Friend> friends = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState != null) {

        }
        else {
            String[] name_list = {"arya", "cersei", "daenerys", "jon", "jorah", "margaery",
                                  "melisandre", "sansa", "tyrion"};
            for (String name : name_list) {
                String bio = "This is a placeholder for the bio off " + name +
                             "Lorem ipsum dolor sit amet, consectetur adipiscing elit. In in neque " +
                             "ac arcu venenatis ornare ut sit amet dolor. Pellentesque quis tellus " +
                             "in ante placerat aliquet. Quisque eu risus eget metus ornare dignissim " +
                             "ac vitae leo. Pellentesque sed sagittis nisl. Donec quis elementum " +
                             "velit.";
                int drawID = getResources().getIdentifier(name, "drawable", getPackageName());
                Friend friend = new Friend(name, bio, drawID);
                friends.add(friend);
            }

            FriendsAdapter adapter = new FriendsAdapter(this, R.layout.grid_item, friends);
            GridView friend_grid = findViewById(R.id.friends_grid);
            friend_grid.setAdapter(adapter);

        }
    }
}
