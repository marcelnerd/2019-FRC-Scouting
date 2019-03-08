package com.example.a2019frcscouting;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import static com.example.a2019frcscouting.TBAHandler.helper;

public class TeamInfoActivity extends AppCompatActivity {

    TextView teamText, teleopText, autoText, hatchText, cargoText, winText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_team_info);
        Intent intent = getIntent();
        helper.doStatsStuff("yeet");


        teamText = findViewById(R.id.numTextView);
        teleopText = findViewById(R.id.teleopText);
        autoText = findViewById(R.id.autoText);
        hatchText = findViewById(R.id.hatchText);
        cargoText = findViewById(R.id.cargoText);
        winText = findViewById(R.id.winText);

       // teamText.append(intent.getStringExtra("teamNum") + "; Signifgance: " + MainActivity.handler.helper.getSig(Float.parseFloat(intent.getStringExtra("teamNum")), "_id"));

        teamText.append(intent.getStringExtra("teamNum"));
        teleopText.append(intent.getStringExtra("teleop") + "; Significance: " + MainActivity.handler.helper.getSig(Float.parseFloat(intent.getStringExtra("teleop")), "teleopPoints"));
        autoText.append(intent.getStringExtra("autoPoints") + "; Significance: " + MainActivity.handler.helper.getSig(Float.parseFloat(intent.getStringExtra("autoPoints")), "autoPoints"));
        hatchText.append(intent.getStringExtra("hatchPoints") + "; Significance: " + MainActivity.handler.helper.getSig(Float.parseFloat(intent.getStringExtra("hatchPoints")), "hatchPoints"));
        cargoText.append(intent.getStringExtra("cargoPoints") + "; Significance: " + MainActivity.handler.helper.getSig(Float.parseFloat(intent.getStringExtra("cargoPoints")), "cargoPoints"));
        winText.append(intent.getStringExtra("win") + "; Significance: " + MainActivity.handler.helper.getSig(Float.parseFloat(intent.getStringExtra("win")), "winRate"));
    }
}
