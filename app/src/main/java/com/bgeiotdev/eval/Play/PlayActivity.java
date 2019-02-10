package com.bgeiotdev.eval.Play;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import com.bgeiotdev.eval.R;

public class PlayActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        getSupportActionBar().setDisplayShowTitleEnabled(false);
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

