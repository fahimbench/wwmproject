package com.bgeiotdev.eval.Leaderboard;


import android.arch.lifecycle.ViewModelProviders;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ProgressBar;

import com.bgeiotdev.eval.R;

import java.util.HashMap;
import java.util.Map;


public class LeaderboardActivity extends AppCompatActivity {

    private HashMap<String, Integer> listMenu = new HashMap<>();
    private LeaderboardAdapter adapter;
    private RecyclerView rcView;
    private LeaderboardViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leaderboard);
        ActionBar actionbar = getSupportActionBar();
        actionbar.setDisplayShowTitleEnabled(false);
        actionbar.setDisplayHomeAsUpEnabled(true);
        actionbar.setHomeButtonEnabled(true);

        rcView = findViewById(R.id.leaderboard_recycler);
        mViewModel = ViewModelProviders.of(this).get(LeaderboardViewModel.class);
        DisplayLeaderboard();
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_leaderboard, menu);
        //menu.performIdentifierAction(R.id.easy_tab, 0);

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
                mViewModel.difficulty = 1;
                executeSearchLeaderboard(1);
                break;
            case R.id.hard_tab:
                mViewModel.difficulty = 2;
                executeSearchLeaderboard(2);
                break;
            case R.id.alone_tab:
                mViewModel.difficulty = 3;
                executeSearchLeaderboard(0);
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

    private void executeSearchLeaderboard(int i){
        ProgressBar bar = findViewById(R.id.progress_leaderboard);
        LeaderboardTask task = new LeaderboardTask(LeaderboardActivity.this);
        task.setAll(bar,rcView,adapter, mViewModel.list ,i);
        task.execute(i);
    }

    private void DisplayLeaderboard(){
        adapter = new LeaderboardAdapter(mViewModel.list, mViewModel.difficulty);
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(this));
    }


}


