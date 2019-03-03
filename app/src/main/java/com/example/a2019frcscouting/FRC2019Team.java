package com.example.a2019frcscouting;

import android.database.sqlite.SQLiteCursor;
import android.util.Log;

import java.util.Iterator;
import java.util.Set;

public class FRC2019Team {
    public int teamNum;
    //public boolean climb;
    public int autoPoints;
    public boolean autoRun;
    public int teleopPoints;
    public float autoRunPerc;
    public int hatchPoints;
    public int cargoPoints;
    public int matchesPlayed = 0;

    public FRC2019Team(int t, int ap, int tp, int hp, int cp) {
        teamNum = t;
        autoPoints = ap;
        teleopPoints = tp;
        hatchPoints = hp;
        cargoPoints = cp;
    }

    public FRC2019Team(SQLiteCursor cursor) {

    }


    public static FRC2019Team buildTeam(Set entry) {

        //boolean tempClimb = false;
        int tempAP = 999;
        boolean tempAR = false;
        int tempTeamNum = 0;
        int tempTP = 999;
        int tempHP = 999;
        int tempCP = 999;

        String currItem;

        Iterator it = entry.iterator();

        while (it.hasNext()) {
            currItem = it.next().toString();
            if (currItem.contains("cargoPoints")) {
                tempCP = Integer.parseInt(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
                //Log.v("minto", Boolean.toString(tempClimb));
            } else if (currItem.contains("autoPoints")) {
                tempAP = Integer.parseInt(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
            } else if (currItem.contains("teamNumber")) {
                tempTeamNum = Integer.parseInt(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
                //Log.v("minto", Integer.toString(tempTeamNum));
            } else if (currItem.contains("teleopPoints")) {
                tempTP = Integer.parseInt(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
            } else if (currItem.contains("hatchPanelPoints")) {
                tempHP = Integer.parseInt(currItem.substring(currItem.indexOf('=') + 1, currItem.length()));
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
