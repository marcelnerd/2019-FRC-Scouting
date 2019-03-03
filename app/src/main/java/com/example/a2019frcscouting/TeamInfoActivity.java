package com.example.a2019frcscouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class TeamInfoActivity extends AppCompatActivity {

    TextView teamText, teleopText, autoText, hatchText, cargoText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
        Intent intent = getIntent();

        teamText = findViewById(R.id.numTextView);
        teleopText = findViewById(R.id.teleopText);
        autoText = findViewById(R.id.autoText);
        hatchText = findViewById(R.id.hatchText);
        cargoText = findViewById(R.id.cargoText);

        teamText.append(intent.getStringExtra("teamNum"));
        teleopText.append(intent.getStringExtra("teleop"));
        autoText.append(intent.getStringExtra("autoPoints"));
        hatchText.append(intent.getStringExtra("hatchPoints"));
        cargoText.append(intent.getStringExtra("cargoPoints"));

    }
}
