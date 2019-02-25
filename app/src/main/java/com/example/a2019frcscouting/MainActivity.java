package com.example.a2019frcscouting;


import android.content.Context;
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
import android.widget.Button;
import android.widget.ListView;

/*
TODO // IMPROVE RESPONSIVENESS!!!!!!!
TODO // or not, I mean only you and Raf will probably use the app a lot
TODO //
TODO // Sorting by different values
TODO // settings page
TODO //
 */

public class MainActivity extends FragmentActivity implements ListFragment.OnFragmentInteractionListener {
    public static Context c; // Static context that can be accessed from other classes
    private static int currentMatch = 1;
    FragmentManager manager = this.getSupportFragmentManager();
    //public com.example.cameron.sql_testing.DatabaseContainer container = new DatabaseContainer(this);
    Button button;
    static TBAHandler handler;
    static ListView list;
    SharedPreferences sharedPref;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            Fragment fragment;
            FragmentTransaction transaction;
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    fragment = new ListFragment();
                    transaction = getSupportFragmentManager().beginTransaction();
                    transaction.replace(R.id.listFrameLayout, fragment);
                    transaction.commit();
                    //mTextMessage.setText(R.string.title_home);
                    return true;
                case R.id.navigation_dashboard:

                    //mTextMessage.setText(R.string.title_dashboard);
                    return true;
                case R.id.navigation_notifications:
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
        c = getBaseContext();
        super.onCreate(savedInstanceState);
        sharedPref = this.getPreferences(Context.MODE_PRIVATE);
        setContentView(R.layout.fragment_list);
        button = findViewById(R.id.button);
        list = findViewById(R.id.listMain);
        handler = new TBAHandler(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

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

//        FrodoCursorAdapter todoAdapter = new FrodoCursorAdapter(this, TBAHandler.helper.getAllEntriesCursor());
//        list.setAdapter(todoAdapter);
    }

    @Override
    public void onFragmentInteraction(Uri uri) {}

    public void startTeamInfoFragment(SQLiteCursor cursor) {
        TeamInfoFragment fragment = new TeamInfoFragment();
        fragment.setThing(cursor);
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.teamFrameLayout, fragment);
        transaction.commit();
    }

}
