package com.bgeiotdev.eval;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.bgeiotdev.eval.Play.PlayActivity;
import com.bgeiotdev.eval.Settings.SettingsActivity;
import com.bgeiotdev.eval.leaderboard.LeaderboardActivity;
import com.bgeiotdev.eval.others.ConnectivityHelper;
import com.bgeiotdev.eval.others.NetworkTask;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView play;
    private TextView ladder;
    private TextView pref;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        play = findViewById(R.id.play);
        ladder = findViewById(R.id.ladder);
        pref = findViewById(R.id.preferences);
        progressBar = findViewById(R.id.mainprogress);

        play.setOnClickListener(this);
        ladder.setOnClickListener(this);
        pref.setOnClickListener(this);

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        NetworkTask nt = null;
        switch(view.getId()){
            case R.id.play:
                nt = new NetworkTask(this, PlayActivity.class, getWindow(), progressBar);
                nt.execute();
                break;
            case R.id.ladder:
                nt = new NetworkTask(this, LeaderboardActivity.class, getWindow(), progressBar);
                nt.execute();
                break;
            case R.id.preferences:
                nt = new NetworkTask(this, SettingsActivity.class, getWindow(), progressBar);
                nt.execute();
                break;
                default:

        }
    }
}
