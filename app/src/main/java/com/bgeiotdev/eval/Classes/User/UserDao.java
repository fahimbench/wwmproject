package com.bgeiotdev.eval.Classes.User;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

import static android.arch.persistence.room.OnConflictStrategy.REPLACE;

@Dao
public interface UserDao {

    @Update
    void updateUser(User user);

    @Insert
    void insertUser(User user);

    @Query("SELECT * FROM user")
    List<User> loadUser();

    @Delete
    void deleteUser(User user);

    @Query("DELETE FROM user")
    void deleteAll();

}