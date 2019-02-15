package com.bgeiotdev.eval.Play;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.widget.TextView;

import com.bgeiotdev.eval.Classes.User.User;
import com.bgeiotdev.eval.Database.AppDatabase;
import com.bgeiotdev.eval.Database.AppRepo;
import com.bgeiotdev.eval.Others.TimeUtils;
import com.bgeiotdev.eval.Others.UserModelView;
import com.bgeiotdev.eval.R;

import java.util.List;

public class PlaySuite extends AppCompatActivity {

    TextView timer;
    TextView player;
    User user;
    AsyncTask at;
    UserModelView umv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play_suite);
        umv = ViewModelProviders.of(this).get(UserModelView.class);
        user = umv.loadUser().get(0);
        long time = getIntent().getLongExtra("timer", 0);

        timer = findViewById(R.id.timer);
        player = findViewById(R.id.player);




        player.setText("Félicitation " + user.getKey());
        timer.setText("Vous avez fini la grille en " + TimeUtils.secondsToString(time) + "");


    }


}
