package com.bgeiotdev.eval.Play;

import android.content.DialogInterface;
import android.content.pm.ActivityInfo;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuItem;

import android.widget.ArrayAdapter;
import android.widget.Chronometer;
import android.widget.GridView;

import com.bgeiotdev.eval.R;
import com.bgeiotdev.eval.classes.Cell;
import com.bgeiotdev.eval.classes.Grid;
import com.bgeiotdev.eval.classes.Play;
import com.bgeiotdev.eval.classes.User;

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
        difficulty = PreferenceManager.getDefaultSharedPreferences(this).getInt("difficulty_settings", -1);
        System.out.println(difficulty);
        play = new Play(chrono, user, difficulty);
        grid = play.getGrid();

        ArrayAdapter<Cell> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, grid.getGrid());

        gridView.setAdapter(adapter);


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

                    break;
                case R.id.surrender:
                    AlertDialog.Builder alertbox = new AlertDialog.Builder(this);
                    alertbox.setMessage("Êtes vous sûre de vouloir abandonner la partie en cours ?");
                    alertbox.setNeutralButton("Annuler", new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface arg0, int arg1) {

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

}

