package com.bgeiotdev.eval.classes;

public class Grid {
    private Cell[][] grid = new Cell[9][9];
    private boolean isComplete = false;
    private int difficulty;

    public Grid(int difficulty){
        this.difficulty = difficulty;
    }

    private void generateFullGrid(){

    }

    private void pickSomeCells(){

    }

    private boolean seeIfComplete(){
        return true;
    }

    public boolean validateGrid(){
        return true;
    }
}
