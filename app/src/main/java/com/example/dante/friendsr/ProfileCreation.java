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

        Spinner spinner = findViewById(R.id.picture_picker);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.name_arrays, R.layout.support_simple_spinner_dropdown_item);
        adapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new CustomOnItemSelectedListener());

        Intent intent = getIntent();
        mode = (String) intent.getSerializableExtra("mode");

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
        TextView name_input = findViewById(R.id.name_input);
        TextView bio_input = findViewById(R.id.bio_input);
        Spinner spinner = findViewById(R.id.picture_picker);

        String name = name_input.getText().toString();
        String bio = bio_input.getText().toString();
        String pic = spinner.getSelectedItem().toString();

        int drawID = getResources().getIdentifier(pic, "drawable", getPackageName());

        Friend friend = new Friend(name, bio, drawID);

        Intent intent = new Intent();
        intent.putExtra("friend", friend);

        if (mode.equals("edit")) {
            Log.d("submit_c", "add retrieved");
            intent.putExtra("old_friend", retrieved_friend);
        }
        setResult(2, intent);
        finish();
    }

    private class CustomOnItemSelectedListener implements AdapterView.OnItemSelectedListener {

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