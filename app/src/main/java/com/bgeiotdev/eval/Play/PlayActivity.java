package com.bgeiotdev.eval.Play;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Display;
import android.view.Menu;
import android.view.MenuItem;


import android.widget.Chronometer;
import android.widget.GridView;

import com.bgeiotdev.eval.R;
import com.bgeiotdev.eval.classes.Grid;
import com.bgeiotdev.eval.classes.Play;
import com.bgeiotdev.eval.classes.User;
import com.bgeiotdev.eval.others.GridAdapter;

public class PlayActivity extends AppCompatActivity {

    GridView gridView;
    Chronometer chrono;
    User user;
    Play play;
    Grid grid;
    int difficulty;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);

        gridView = findViewById(R.id.gridSudoku);
        chrono = findViewById(R.id.chronometer);

        difficulty = Integer.valueOf(PreferenceManager.getDefaultSharedPreferences(this).getString("difficulty_settings", "1"));

        play = new Play(chrono, user, difficulty);

        grid = play.getGrid();
        double screensize = calculateScreenSize();
        GridAdapter adapter = new GridAdapter(this, R.layout.cells, grid.getGrid(), screensize, this);



        gridView.setAdapter(adapter);

        chrono.start();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_play, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {
                case R.id.validate:
                    chrono.stop();
                    break;
                case R.id.surrender:
                    chrono.stop();
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                    alertbox.setMessage("Êtes vous sûre de vouloir abandonner la partie en cours ?");
                    alertbox.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            chrono.start();
                        }
                    });
                    alertbox.setNegativeButton("Abandonner", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {
                            finish();
                        }
                    });
                    alertbox.show();
                    break;
            }
            return true;

    }

    private double calculateScreenSize(){
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        return size.x;
    }


}

