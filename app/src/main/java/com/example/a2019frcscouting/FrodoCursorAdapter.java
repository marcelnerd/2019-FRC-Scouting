package com.example.a2019frcscouting;

import android.app.FragmentTransaction;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CursorAdapter;
import android.widget.TextView;

public class FrodoCursorAdapter extends CursorAdapter {

    String sortOption;

    public FrodoCursorAdapter(Context context, Cursor cursor, String s) {
        super(context, cursor, 0);
        sortOption = s;
    }

    // The newView method is used to inflate a new view and return it,
    // you don't bind any data to the view at this point.
    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        return LayoutInflater.from(context).inflate(R.layout.team_entry_frodo, parent, false);
    }

    // The bindView method is used to bind all data to a given view
    // such as setting the text on a TextView.
    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        // Find fields to populate in inflated template
        TextView teamNumText = view.findViewById(R.id.TeamNumText);
        TextView statText = view.findViewById(R.id.statText);

        if(cursor.getInt(0) == -1) {
            teamNumText.setText("Mean of Stats");
        }
        else if(cursor.getInt(0) == -2) {
            teamNumText.setText("Standard Deviation");
        }
        else {
            teamNumText.setText(Integer.toString(cursor.getInt(0)));

            switch (sortOption) {
                case "teamNum":
                    statText.setText(Integer.toString(cursor.getInt(0)));

                case "teleop":
                    statText.setText(Float.toString(cursor.getFloat(cursor.getColumnIndex("teleopPoints"))));
                    break;
                case "auto":
                    statText.setText(Float.toString(cursor.getFloat(cursor.getColumnIndex("autoPoints"))));
                    break;
                case "hatch":
                    statText.setText(Float.toString(cursor.getFloat(cursor.getColumnIndex("hatchPoints"))));
                    break;
                case "cargo":
                    statText.setText(Float.toString(cursor.getFloat(cursor.getColumnIndex("cargoPoints"))));
                    break;
                case "win":
                    statText.setText(Float.toString(cursor.getFloat(cursor.getColumnIndex("winRate"))));
            }
        }
    }
}
