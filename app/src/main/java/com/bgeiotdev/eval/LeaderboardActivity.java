package com.bgeiotdev.eval;

import android.content.Context;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class LeaderboardActivity extends AppCompatActivity {
    private HashMap<String, Integer> listMenu = new HashMap<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ActionBar actionbar = getSupportActionBar();

        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);

        RecyclerView rcView = (RecyclerView) findViewById(R.id.leaderboard_recycler);

        ArrayList<String> rr = new ArrayList<String>();

        LeaderboardAdapter adapter = new LeaderboardAdapter(rr);

        rcView.setAdapter(adapter);

        rcView.setLayoutManager(new LinearLayoutManager(this));
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_leaderboard, menu);
        return true;

    }


    @Override
    public boolean onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
        listMenu.put("easy", R.id.easy_tab);
        listMenu.put("hard", R.id.hard_tab);
        listMenu.put("solo", R.id.alone_tab);

        return super.onPrepareOptionsMenu(menu);
    }


    public boolean onOptionsItemSelected(MenuItem item) {

        switch (item.getItemId()) {
            case android.R.id.home:
                this.finish();
                return true;
            case R.id.easy_tab:
                loadList(R.id.easy_tab);
                return true;

            case R.id.hard_tab:
                loadList(R.id.hard_tab);
                return true;

            case R.id.alone_tab:
                loadList(R.id.alone_tab);
                return true;

        }

        return super.onOptionsItemSelected(item);

    }

    private void loadList(int menuItemId){

        disabledTabAndEnabledOthers(menuItemId);
        searchLeaderboard(menuItemId);
    }

    private void searchLeaderboard(int menuItemId) {

        switch (menuItemId) {

            case R.id.easy_tab:

                break;
            case R.id.hard_tab:

                break;
            case R.id.alone_tab:

                break;

        }
    }

    private void disabledTabAndEnabledOthers(int menuItemId){

        for (Map.Entry<String, Integer> entry : listMenu.entrySet())
        {

            if(entry.getValue() == menuItemId && (findViewById(entry.getValue())).isEnabled()){
                (findViewById(entry.getValue())).setEnabled(false);
            }else{
                (findViewById(entry.getValue())).setEnabled(true);
            }

        }
    }
}


