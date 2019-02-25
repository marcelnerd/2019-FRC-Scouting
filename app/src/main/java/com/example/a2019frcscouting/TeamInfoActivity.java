package com.example.a2019frcscouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TeamInfoActivity extends AppCompatActivity { //TODO START // Team info activity works; figure out possible drop-down for team info, figure out sorting by different values

    TextView text;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
        Intent intent = getIntent();

        text = findViewById(R.id.numTextView);

        text.setText(intent.getStringExtra("teamNum"));
    }
}
