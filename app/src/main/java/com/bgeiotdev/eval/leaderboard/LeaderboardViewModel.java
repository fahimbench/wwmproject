package com.bgeiotdev.eval.leaderboard;

import android.arch.lifecycle.ViewModel;

import org.json.JSONObject;

import java.util.ArrayList;

public class LeaderboardViewModel extends ViewModel {

    public ArrayList<JSONObject> list = new ArrayList<>();
    public int difficulty;

}