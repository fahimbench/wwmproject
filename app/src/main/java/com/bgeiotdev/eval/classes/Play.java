package com.bgeiotdev.eval.classes;

import android.content.SharedPreferences;
import android.widget.Chronometer;

public class Play {

    private Grid grid;
    private Chronometer timer;
    private User user;
    private int difficulty;

    public Play(Chronometer timer, User user, int difficulty){
        this.timer = timer;
        this.user = user;
        this.difficulty = difficulty;
        this.grid = new Grid(difficulty);
    }

    public void chronoStart(){

    }

    public void chronoPause(){

    }

    public void chronoStop(){

    }

    public boolean verifyIfWin(){
        return true;
    }

    public boolean seeIfCompleteGrid(){
        return true;
    }

    public boolean isWin(){
        return true;
    }
}
