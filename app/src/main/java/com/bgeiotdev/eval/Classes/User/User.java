package com.bgeiotdev.eval.Classes.User;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.bgeiotdev.eval.Others.TimeUtils;

import java.io.Serializable;


@Entity(tableName = "user")
public class User implements Serializable {

    @PrimaryKey(autoGenerate = true)
    @NonNull
    private int id;

    @ColumnInfo(name = "pseudo")
    private String pseudo;

    @ColumnInfo(name = "key")
    private long key;

    public User( String pseudo){

        this.pseudo = pseudo;
        this.key = TimeUtils.uniqueCurrentTimeMS();

    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPseudo() {
        return pseudo;
    }

    public void setPseudo(String pseudo) {
        this.pseudo = pseudo;
    }

    public long getKey() {
        return key;
    }

    public void setKey(long key) {
        this.key = key;
    }
}
