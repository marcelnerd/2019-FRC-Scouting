package com.example.a2019frcscouting;

import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import java.util.Iterator;
import java.util.Set;

public class FRC2019Team {
    public int teamNum;
    //public boolean climb;
    public float autoPoints;
    public boolean autoRun;
    public float teleopPoints;
    public float autoRunPerc;
    public float hatchPoints;
    public float cargoPoints;
    public int matchesPlayed = 1;
    public float[] scores;
    public String[] scoreKeys = {"_id", "teleopPoints", "autoPoints", "cargoPoints", "hatchPoints", "matchesPlayed"};


    public FRC2019Team(int t, float ap, float tp, float hp, float cp) {
        teamNum = t;
        autoPoints = ap;
        teleopPoints = tp;
        hatchPoints = hp;
        cargoPoints = cp;
        scores = new float[] {teamNum, teleopPoints, autoPoints, cargoPoints, hatchPoints};
    }

    public FRC2019Team(SQLiteCursor cursor) {

    }

    public static FRC2019Team buildTeam(Set entry) { //

        //boolean tempClimb = false;
        float tempAP = 999;
        boolean tempAR = false;
        int tempTeamNum = 0;
        float tempTP = 999;
        float tempHP = 999;
        float tempCP = 999;

        String currItem;

        Iterator it = entry.iterator();

        while (it.hasNext()) {
            currItem = it.next().toString();
            if (currItem.contains("cargoPoints")) {
                tempCP = Float.parseFloat(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
                //Log.v("minto", Boolean.toString(tempClimb));
            } else if (currItem.contains("autoPoints")) {
                tempAP = Float.parseFloat(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
            } else if (currItem.contains("teamNumber")) {
                tempTeamNum = Integer.parseInt(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
                //Log.v("minto", Integer.toString(tempTeamNum));
            } else if (currItem.contains("teleopPoints")) {
                tempTP = Float.parseFloat(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
            } else if (currItem.contains("hatchPanelPoints")) {
                tempHP = Float.parseFloat(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
            }

            //Log.d("minto", "Current entry item: " + currItem);
        }

        return new FRC2019Team(tempTeamNum, tempAP, tempTP, tempHP, tempCP);
    }

    public void setMatchesPlayed(int i) {
        matchesPlayed = i;
    }

    public int getMatchesPlayed() {
        return matchesPlayed;
    }
}
