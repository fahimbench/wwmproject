package com.bgeiotdev.eval.classes;

import android.support.annotation.NonNull;

public class Cell {
    private int value = 0;
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

    @NonNull
    @Override
    public String toString() {
        return String.valueOf(value);
    }
}
