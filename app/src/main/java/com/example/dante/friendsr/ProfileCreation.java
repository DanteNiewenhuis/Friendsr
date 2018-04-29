package com.example.dante.friendsr;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

public class ProfileCreation extends AppCompatActivity {
    private Friend retrieved_friend;
    private String mode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_creation);

        // get the spinner
        Spinner spinner = findViewById(R.id.picture_picker);

        // create the adapter for the spinner
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.name_arrays, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);

        // set adapter and the onItemSelectedListener for the spinner
        spinner.setAdapter(adapter);
        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        // get the intent and get the mode
        Intent intent = getIntent();
        mode = (String) intent.getSerializableExtra("mode");

        // set the default values of the input boxes to the value of the profile if
        // the mode is edit.
        if (mode.equals("edit")) {
            retrieved_friend = (Friend) intent.getSerializableExtra("old_friend");

            TextView name_input = findViewById(R.id.name_input);
            TextView bio_input = findViewById(R.id.bio_input);
            spinner = findViewById(R.id.picture_picker);

            name_input.setText(retrieved_friend.getName());
            bio_input.setText(retrieved_friend.getBio());
            spinner.setSelection(adapter.getPosition(retrieved_friend.getName()));
        }
    }

    public void submit (View v) {
        // get the Views
        TextView name_input = findViewById(R.id.name_input);
        TextView bio_input = findViewById(R.id.bio_input);
        Spinner spinner = findViewById(R.id.picture_picker);

        // get the inputs
        String name = name_input.getText().toString();
        String bio = bio_input.getText().toString();
        String pic = spinner.getSelectedItem().toString();
        int drawID = getResources().getIdentifier(pic, "drawable", getPackageName());


        // make a new friend
        Friend friend = new Friend(name, bio, drawID);

        // make an intent with the new friend as extra
        Intent intent = new Intent();
        intent.putExtra("friend", friend);

        // add the olf friend if the mode is edit.
        if (mode.equals("edit")) {
            intent.putExtra("old_friend", retrieved_friend);
        }

        // set the result to the intent
        setResult(2, intent);
        finish();
    }

    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

        // set the example pic to the selection in the spinner
        public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
            String pic = parent.getItemAtPosition(pos).toString();
            int drawID = getResources().getIdentifier(pic, "drawable", getPackageName());
            ImageView picture_example = findViewById(R.id.picture_example);
            picture_example.setImageResource(drawID);
        }

        @Override
        public void onNothingSelected(AdapterView<?> parent) {
        }
    }
}