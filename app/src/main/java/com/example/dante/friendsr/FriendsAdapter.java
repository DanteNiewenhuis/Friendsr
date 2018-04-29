package com.example.dante.friendsr;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class FriendsAdapter extends ArrayAdapter<Friend> {
    private ArrayList<Friend> friends;

    public FriendsAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Friend> friends) {
        super(context, resource, friends);
        this.friends = friends;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.grid_item, parent, false);
        }

        // get the friend at the position
        Friend friend = friends.get(position);
        String name_string = friend.getName();
        int drawID = friend.getDrawableId();

        // put the name in the name TextView
        TextView Name = convertView.findViewById(R.id.Name);
        Name.setText(name_string);

        // set the image resource
        ImageView picture = convertView.findViewById(R.id.Picture);
        picture.setImageResource(drawID);
        return convertView;
    }
}
