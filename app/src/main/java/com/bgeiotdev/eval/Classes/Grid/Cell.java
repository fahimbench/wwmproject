package com.bgeiotdev.eval.Classes.Grid;

public class Cell {
    private int value;
    private Position position;

    public Cell(int value, Position position){
        this.value = value;
        this.position = position;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

}
