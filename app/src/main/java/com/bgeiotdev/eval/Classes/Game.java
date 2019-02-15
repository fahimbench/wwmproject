package com.bgeiotdev.eval.Classes;

import com.bgeiotdev.eval.Classes.User.User;

public class Game {

    private int id;
    private long score;
    private int type;
    private User user;

    public Game (int id, long score, int type, User user){
        this.id = id;
        this.score = score;
        this.type = type;
        this.user = user;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public long getScore() {
        return score;
    }

    public void setScore(long score) {
        this.score = score;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
