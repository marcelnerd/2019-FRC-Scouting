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
import android.widget.ListView;
import android.widget.Spinner;

/*
TODO // IMPROVE RESPONSIVENESS!!!!!!!
TODO // or not, I mean only you and Raf will probably use the app a lot
TODO // Finish team info page
TODO // Sorting by different values //DONE
TODO // settings page
TODO // Replace for 2019 data
 */

public class MainActivity extends FragmentActivity implements ListFragment.OnFragmentInteractionListener, SettingsFragment.OnFragmentInteractionListener, AdapterView.OnItemSelectedListener {
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


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            FragmentTransaction transaction;
            switch (item.getItemId()) {
                case R.id.navigation_home:
//                    fragment = new ListFragment();
//                    transaction = getSupportFragmentManager().beginTransaction();
//                    transaction.replace(R.id.listFrameLayout, fragment);
//                    transaction.commit(); //TODO FUCK SHIT UP
                    list.deferNotifyDataSetChanged();

                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:

                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
                    fragment = new SettingsFragment();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.settingsFrameLayout, fragment);
                    transaction.commit();
                    //mTextMessage.setText(R.string.title_notifications);
                    return true;
            }
            return false;
        }
    };

    public static int getCurrentMatch() {
        return currentMatch;
    }

    public static void setCurrentMatch(int m) {
        currentMatch = m;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Fragment fragment;
        FragmentTransaction transaction;
        c = getBaseContext();
        super.onCreate(savedInstanceState);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        list = findViewById(R.id.listMain);
        handler = new TBAHandler(this);
        TBAKey = sharedPref.getString(getString(R.string.settings_key_key), "yeet");

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        sortSpinner = findViewById(R.id.sortSpinner);
        adap = ArrayAdapter.createFromResource(this, R.array.sort_array, android.R.layout.simple_spinner_item);
        adap.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        sortSpinner.setAdapter(adap);
        sortSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                FrodoCursorAdapter todoAdapter;
                FragmentManager manager = getSupportFragmentManager();
                FragmentTransaction transaction = manager.beginTransaction();
                switch(position) {
                    case 0:
                        //handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", currentMatch));
                        todoAdapter = new FrodoCursorAdapter(MainActivity.c, TBAHandler.helper.getAllEntriesTeamCursor(), "team");
                        MainActivity.list.setAdapter(todoAdapter);
                        //transaction.replace(R.id.listFrameLayout, new ListFragment());
                        //transaction.commit();
                        break;
                    case 1:
                        todoAdapter = new FrodoCursorAdapter(MainActivity.c, TBAHandler.helper.getAllEntriesTeleopCursor(), "teleop");
                        MainActivity.list.setAdapter(todoAdapter);
                        break;
                    case 2:
                        break;
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                FrodoCursorAdapter todoAdapter = new FrodoCursorAdapter(MainActivity.c, TBAHandler.helper.getAllEntriesTeamCursor(), "team");
                MainActivity.list.setAdapter(todoAdapter);
            }
        });

        MainActivity.list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //(new MainActivity()).startTeamInfoFragment((SQLiteCursor) parent.getItemAtPosition(position));
                SQLiteCursor cursor = (SQLiteCursor) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.c, TeamInfoActivity.class);

                intent.putExtra("teamNum", Integer.toString(cursor.getInt(0)));
                startActivity(intent);

            }
        });

        for (int i = 1; i < 3; i++) {
            handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", i));
        }

       /* fragment = new ListFragment();
        transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.listFrameLayout, fragment);
        transaction.commit();*/



        //handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018_mndu", 1));
        //Log.v("minto", handler.helper.getAllEntries());

//        button.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", currentMatch));
//                currentMatch++;
//            }
//        });

        //handler.helper.onUpgrade(handler.helper.getWritableDatabase(), 4, 5);
//        for (int i = 1; i < 100; i++) {
//            handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", i));
//        }

//        FrodoCursorAdapter todoAdapter = new FrodoCursorAdapter(this, TBAHandler.helper.getAllEntriesTeamCursor());
//        list.setAdapter(todoAdapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {

    }

    public void onNothingSelected(AdapterView<?> parent) {

    }

}
