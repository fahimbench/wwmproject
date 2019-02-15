package com.bgeiotdev.eval.Classes.Grid;

import com.bgeiotdev.eval.Others.GridGen;

public class Grid {
    private Cell[] grid;
    private boolean isComplete = false;
    private int difficulty;
    private int row_column;
    private GridGen gridgen = new GridGen();
    public Grid(int difficulty, int row_column){
        this.difficulty = difficulty;
        this.row_column = row_column;
        this.grid = new Cell[row_column*row_column];
        generateFullGrid();
        //Arrays.fill(grid, new Cell(0,null));
    }

    private void generateFullGrid(){
        int[][] a = gridgen.generate(difficulty);
        int k = 0;
        for(int i = 0; i < a.length; i++){
            for(int j = 0; j < a[i].length; j++){
                grid[k] = new Cell(a[i][j], new Position((int) i+1, (int) j+1));
                k++;
            }

        }

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
