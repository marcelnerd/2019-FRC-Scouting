package com.example.a2019frcscouting;

import android.util.Log;
import android.widget.TextView;

import com.android.volley.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class TBAListener implements Response.Listener<JSONObject> {

    TextView textBox;
    JSONObject json = null;
    boolean hasJson = false;
    DBHelper helper;

    public TBAListener(DBHelper h) {
        //Log.d("minto", "CREATED LISTENER");
        helper = h;
    }


    @Override
    public void onResponse(JSONObject response) {
        HashMap[] map = null;
        FRC2019Team team = null;
        //Log.v("minto", response.toString());

        /*try {
            if (Integer.parseInt(response.getString("match_number")) < MainActivity.getCurrentMatch()) {
                return;
            }
        } catch(JSONException e) {
            e.printStackTrace();
        } */

        this.json = response;
        try {
            map = MatchUpdater.getMatchData(response);
        } catch (JSONException e) {
            e.printStackTrace();
            Log.v("minto", "you fucked up; its the getMatchData thing");

        }


        try {
        } catch(Exception e) {
            //fuck you
        }

        MainActivity.setCurrentMatch((MainActivity.getCurrentMatch() + 1));
        for (HashMap e : map) {
            //Log.v("minto", e.toString());
            team = FRC2019Team.buildTeam(e.entrySet());
            //og.v("minto", "f");
            helper.updateTeamStats(team);
        }

    }

    private static void yeet() {
    }

    public JSONObject getJson() {
        return json;
    }

    public boolean hasJson() {
        return (json != null);
    }
}


