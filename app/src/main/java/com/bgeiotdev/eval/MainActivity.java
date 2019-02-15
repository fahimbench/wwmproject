package com.bgeiotdev.eval;

import android.app.Activity;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.graphics.Point;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.preference.PreferenceManager;
import android.view.Display;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.bgeiotdev.eval.Classes.User.User;
import com.bgeiotdev.eval.Others.JsonAsync;
import com.bgeiotdev.eval.Others.TimeUtils;
import com.bgeiotdev.eval.Others.UserModelView;
import com.bgeiotdev.eval.Play.PlayActivity;
import com.bgeiotdev.eval.Settings.SettingsActivity;
import com.bgeiotdev.eval.Leaderboard.LeaderboardActivity;
import com.bgeiotdev.eval.Others.NetworkTask;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView play;
    private TextView ladder;
    private TextView pref;
    private User user;
    private List<User> users;
    private ProgressBar progressBar;
    private UserModelView umv;

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
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);

        umv = ViewModelProviders.of(this).get(UserModelView.class);
        users = umv.loadUser();
        try{
            user = users.get(0);
        }catch (Exception e){
            e.printStackTrace();
            user = new User("Guest"+ TimeUtils.uniqueCurrentTimeMS());
            umv.insertUser(user);
            String request = "http://" + this.getResources().getString(R.string.host) + "/api/users";
            JSONObject jsonParam = new JSONObject();
            try {

                jsonParam.put("pseudo", user.getPseudo());
                jsonParam.put("email", "emailbidon@example.com");
                jsonParam.put("firstname", "robert");
                jsonParam.put("lastname", "De niro");
                jsonParam.put("password", "unpasspastropcompliqué");
                jsonParam.put("keyUser", user.getKey());

            }catch (JSONException ex){
               e.printStackTrace();
            }
            (new JsonAsync(request, "POST", jsonParam)).execute();
        }
            PreferenceManager.getDefaultSharedPreferences(this).edit().putString("pseudo_settings", user.getPseudo()).apply();
        }


    @Override
    public void onClick(View view) {
        Intent intent;
        NetworkTask nt = null;
        switch(view.getId()){
            case R.id.play:

                intent = new Intent(this, PlayActivity.class);
                intent.putExtra("user", user);
                startActivity(intent);
                break;
            case R.id.ladder:
                nt = new NetworkTask(this, LeaderboardActivity.class, getWindow(), progressBar);
                nt.execute();
                break;
            case R.id.preferences:
                intent = new Intent(this, SettingsActivity.class);
                startActivity(intent);
                break;
                default:

        }
    }
}
