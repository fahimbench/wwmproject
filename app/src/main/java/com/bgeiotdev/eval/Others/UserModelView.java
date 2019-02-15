package com.bgeiotdev.eval.Others;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.support.v7.preference.PreferenceManager;

import com.bgeiotdev.eval.Classes.User.User;
import com.bgeiotdev.eval.Database.AppRepo;

import java.util.List;

public class UserModelView extends AndroidViewModel {

    private AppRepo mRepo;

    private List<User> mUsers;

    public UserModelView(Application application) {
        super(application);
        mRepo = new AppRepo(application);
        mUsers = mRepo.loadUser();
    }

    public List<User> loadUser() {
        if(mUsers.size() > 0) {
            PreferenceManager.getDefaultSharedPreferences(MyApplication.getAppContext()).edit().putString("pseudo_settings", mUsers.get(0).getPseudo()).apply();
        }
        return mUsers;
    }

    public void insertUser(User user) {
        mRepo.insertUser(user);
    }

    public void updateUser(User user) {
        mRepo.updateUser(user);
    }

    public void deleteUser(User user) {
        mRepo.deleteUser(user);
    }

    public void deleteAll() {
        mRepo.deleteAll();
    }
}