package com.bgeiotdev.eval;

import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.bgeiotdev.eval.Settings.SettingsActivity;
import com.bgeiotdev.eval.leaderboard.LeaderboardActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView play;
    private TextView ladder;
    private TextView pref;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        getSupportActionBar().hide();

        play = findViewById(R.id.play);
        ladder = findViewById(R.id.ladder);
        pref = findViewById(R.id.preferences);

        play.setOnClickListener(this);
        ladder.setOnClickListener(this);
        pref.setOnClickListener(this);


        //Test shared pref pour la config de l'api
        SharedPreferences pref = getSharedPreferences("shared_prefs", MODE_PRIVATE);
        SharedPreferences.Editor edit = pref.edit();
        edit.putString("host", "http://localhost:8000/api/");
        edit.commit();

    }

    @Override
    public void onClick(View view) {
        Intent intent;
        switch(view.getId()){
            case R.id.play:
                System.out.println(view);
                break;
            case R.id.ladder:
                intent = new Intent(MainActivity.this, LeaderboardActivity.class);
                startActivity(intent);
                break;
            case R.id.preferences:
                intent = new Intent(MainActivity.this, SettingsActivity.class);
                startActivity(intent);
                break;
                default:

        }
    }
}
