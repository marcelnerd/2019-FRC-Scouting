package com.example.a2019frcscouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TeamInfoActivity extends AppCompatActivity {

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
