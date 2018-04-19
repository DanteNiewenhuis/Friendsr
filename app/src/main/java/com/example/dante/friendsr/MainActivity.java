package com.example.dante.friendsr;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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
            String[] name_list = {"arya", "cersei", "deanerys", "jon", "jorah", "margaery",
                                  "melisandre", "sansa", "tyrion"};
            for (String name : name_list) {
                String bio = "This is a placeholder for the bio off " + name;

                int drawID = getResources().getIdentifier(name, "id", getPackageName());
                Friend friend = new Friend(name, bio, drawID);
                friends.add(friend);
            }

        }
    }
}
