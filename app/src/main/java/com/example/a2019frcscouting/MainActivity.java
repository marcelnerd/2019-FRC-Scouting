package com.example.a2019frcscouting;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteCursor;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;

import com.android.volley.RequestQueue;
import com.android.volley.toolbox.Volley;

import static com.example.a2019frcscouting.TBAHandler.helper;

/*
TODO // IMPROVE RESPONSIVENESS!!!!!!!
TODO // or not, I mean only you and Raf will probably use the app a lot
TODO // Finish team info page
TODO // Sorting by different values //DONE
TODO // settings page
TODO // Replace for 2019 data //DONE
TODO //
 */

public class MainActivity extends FragmentActivity implements AdapterView.OnItemSelectedListener {
    public static Context c; // Static context that can be accessed from other classes
    private static int currentMatch = 1;
    //public com.example.cameron.sql_testing.DatabaseContainer container = new DatabaseContainer(this);
    Button button;
    static TBAHandler handler;
    static ListView list;
    public static SharedPreferences sharedPref;
    public static String TBAKey;
    public static Spinner sortSpinner;
    public static ArrayAdapter<CharSequence> adap;
    public static RequestQueue queue;
    public static EditText searchText;
    public static Button searchButton;

    public static int getCurrentMatch() {
        return currentMatch;
    }

    public static void setCurrentMatch(int m) {
        currentMatch = m;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        c = getBaseContext();
        setTheme(R.style.AppTheme);
        super.onCreate(savedInstanceState);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        list = findViewById(R.id.listMain);
        sortSpinner = findViewById(R.id.sortSpinner);
        handler = new TBAHandler(this);
        TBAKey = sharedPref.getString(getString(R.string.settings_key_key), "yeet");
        queue = Volley.newRequestQueue(this);
        queue.start();

        Fragment fragment;
        FragmentTransaction transaction;

        searchButton = findViewById(R.id.searchButton);
        searchText = findViewById(R.id.searchText);

        searchButton.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrodoCursorAdapter todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getTeamEntry(Integer.parseInt(searchText.getText().toString())), "teleop");
                MainActivity.list.setAdapter(todoAdapter);
            }
        });

        handler.helper.onUpgrade(handler.helper.getWritableDatabase(), 0, 4);

        button.setOnClickListener(new Button.OnClickListener() {
            @Override
            public void onClick(View view) {
                FrodoCursorAdapter todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getAllEntriesTeleopCursor(), "teleop");
                MainActivity.list.setAdapter(todoAdapter);
            }
        });

        //sortSpinner = findViewById(R.id.sortSpinner);
        adap = ArrayAdapter.createFromResource(this, R.array.sort_array, android.R.layout.simple_spinner_item);
        adap.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adap);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FrodoCursorAdapter todoAdapter;
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                switch(parent.getSelectedItem().toString()) {
                    case "Team Number":
                        todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getAllEntriesTeamCursor(), "teamNum");
                        MainActivity.list.setAdapter(todoAdapter);
                        break;
                    case "Teleop Points":
                        //handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", currentMatch));
                        todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getAllEntriesTeleopCursor(), "teleop");
                        MainActivity.list.setAdapter(todoAdapter);
                        //transaction.replace(R.id.listFrameLayout, new ListFragment());
                        //transaction.commit();
                        break;
                    case "Autonomous Points":
                        todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getAllEntriesAutoCursor(), "auto");
                        MainActivity.list.setAdapter(todoAdapter);
                        break;
                    case "Hatch Points":
                        todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getAllEntriesHatchCursor(), "hatch");
                        MainActivity.list.setAdapter(todoAdapter);
                        break;
                    case "Cargo Points":
                        todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getAllEntriesCargoCursor(), "cargo");
                        MainActivity.list.setAdapter(todoAdapter);
                        break;
                    case "Win Rate":
                        todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getAllEntriesWinCursor(), "win");
                        MainActivity.list.setAdapter(todoAdapter);
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
//                FrodoCursorAdapter todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getAllEntriesTeamCursor(), "teamNum");
//                MainActivity.list.setAdapter(todoAdapter);
            }
        });

        MainActivity.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //(new MainActivity()).startTeamInfoFragment((SQLiteCursor) parent.getItemAtPosition(position));
                SQLiteCursor cursor = (SQLiteCursor) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.c, TeamInfoActivity.class);

                intent.putExtra("teamNum", Integer.toString(cursor.getInt(0)));
                intent.putExtra("teleop", Float.toString(cursor.getFloat(1)));
                intent.putExtra("cargoPoints", Float.toString(cursor.getFloat(cursor.getColumnIndex("cargoPoints"))));
                intent.putExtra("hatchPoints", Float.toString(cursor.getFloat(cursor.getColumnIndex("hatchPoints"))));
                intent.putExtra("autoPoints", Float.toString(cursor.getFloat(cursor.getColumnIndex("autoPoints"))));
                intent.putExtra("win", Float.toString(cursor.getFloat(cursor.getColumnIndex("winRate"))));
                startActivity(intent);

            }
        });

        for (int i = 1; i < 82; i++) {
            handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2019mndu", i));
        }

        /*FrodoCursorAdapter todoAdapter = new FrodoCursorAdapter(MainActivity.c, helper.getAllEntriesTeleopCursor(), "teleop");
        MainActivity.list.setAdapter(todoAdapter);*/

    }

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}
