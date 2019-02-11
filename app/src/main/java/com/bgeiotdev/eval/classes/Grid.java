package com.bgeiotdev.eval.classes;

import java.util.Arrays;

public class Grid {
    private Cell[] grid;
    private boolean isComplete = false;
    private int difficulty;
    private int row_column;

    public Grid(int difficulty, int row_column){
        this.difficulty = difficulty;
        this.row_column = row_column;
        this.grid = new Cell[row_column^2];
        Arrays.fill(grid, null);
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

    //----------
    //getter setter
    //--------

    public Cell[] getGrid() {
        return grid;
    }

    public void setGrid(Cell[] grid) {
        this.grid = grid;
    }

    public boolean isComplete() {
        return isComplete;
    }

    public void setComplete(boolean complete) {
        isComplete = complete;
    }

    public int getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(int difficulty) {
        this.difficulty = difficulty;
    }
}
