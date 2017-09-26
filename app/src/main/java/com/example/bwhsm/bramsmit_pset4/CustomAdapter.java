package com.example.bwhsm.bramsmit_pset4;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by bwhsm on 20-9-2017.
 */

public class CustomAdapter extends ArrayAdapter<Item> {

    public CustomAdapter(@NonNull Context context, ArrayList<Item> itemArray) {
        super(context, R.layout.custom_row,itemArray);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View customView = inflater.inflate(R.layout.custom_row, parent, false);

        Item item = getItem(position);

        CheckBox checkBox = (CheckBox) customView.findViewById(R.id.checkBox);
        TextView taskText = (TextView) customView.findViewById(R.id.taskText);

        taskText.setText(item.getTitle());
        checkBox.setChecked(item.getCompleted());
        return customView;
    }
}
