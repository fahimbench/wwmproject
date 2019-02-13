package com.bgeiotdev.eval.classes;

import android.content.SharedPreferences;
import android.view.MenuItem;
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
        this.grid = new Grid(difficulty, 9);
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

    public boolean seeIfCompleteGrid(MenuItem menuitem)
    {

        return true;
    }

    public boolean isWin(){
        return true;
    }

    //GETTER SETTER


    public Grid getGrid() {
        return grid;
    }

    public void setGrid(Grid grid) {
        this.grid = grid;
    }

    public Chronometer getTimer() {
        return timer;
    }

    public void setTimer(Chronometer timer) {
        this.timer = timer;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
