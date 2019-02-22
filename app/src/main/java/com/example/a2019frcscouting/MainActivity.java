package com.example.a2019frcscouting;


import android.content.Context;
import android.content.SharedPreferences;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import com.example.a2019frcscouting.*;

import com.example.a2019frcscouting.R;


public class MainActivity extends FragmentActivity {
    public static Context c; // Static context that can be accessed from other classes
    private static int currentMatch = 1;
    //public com.example.cameron.sql_testing.DatabaseContainer container = new DatabaseContainer(this);
    Button button;
    TBAHandler handler;
    ListView list;
    SharedPreferences sharedPref;


    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
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
        setContentView(R.layout.activity_main);
        button = findViewById(R.id.button);
        list = findViewById(R.id.listMain);
        handler = new TBAHandler(this);

        BottomNavigationView navigation = (BottomNavigationView) findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);

        //handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018_mndu", 1));
        //Log.v("minto", handler.helper.getAllEntries());

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", currentMatch));
                currentMatch++;
            }
        });

        //handler.helper.onUpgrade(handler.helper.getWritableDatabase(), 4, 5);
        for (int i = 1; i < 80; i++) {
            handler.getMatchData(String.format("/match/%1$s_qm%2$d", "2018mndu", i));
        }

        FrodoCursorAdapter todoAdapter = new FrodoCursorAdapter(this, TBAHandler.helper.getAllEntriesCursor());
        list.setAdapter(todoAdapter);
    }

}
