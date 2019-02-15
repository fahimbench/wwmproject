package com.bgeiotdev.eval.Database;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v7.preference.PreferenceManager;

import com.bgeiotdev.eval.Classes.User.User;
import com.bgeiotdev.eval.Classes.User.UserDao;
import com.bgeiotdev.eval.Others.MyApplication;

import java.util.List;
import java.util.concurrent.ExecutionException;

public class AppRepo {

    private UserDao userDao;
    private AppDatabase db;

    public AppRepo(Application application){
        db = AppDatabase.getDatabase(application);
        userDao = db.userDao();
    }


    public List<User> loadUser() {
        try {
            return (List<User>) new AsyncTaskUser(userDao).execute("loadUser").get();
        } catch (ExecutionException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void insertUser(User user) {
        new AsyncTaskUser(userDao).execute("insert", user);
    }

    public void updateUser(User user) {
        new AsyncTaskUser(userDao).execute("update", user);
    }

    public void deleteUser(User user) {
        new AsyncTaskUser(userDao).execute("delete", user);
    }

    public void deleteAll() {
        new AsyncTaskUser(userDao).execute("deleteAll");
    }

    private static class AsyncTaskUser extends AsyncTask<Object, Void, Object> {



        private UserDao AsyncUserDao;

        AsyncTaskUser(UserDao dao) {
            AsyncUserDao = dao;
        }

        @Override
        protected Object doInBackground(Object... params) {
            switch ((String) params[0]){
                case "deleteAll":
                    AsyncUserDao.deleteAll();
                    return null;
                case "loadUser":
                    return AsyncUserDao.loadUser();
                case "insert":
                    AsyncUserDao.insertUser((User) params[1]);
                    return null;
                case "update":
                    AsyncUserDao.updateUser((User) params[1]);
                    return null;
                case "delete":
                    AsyncUserDao.deleteUser((User) params[1]);
                    return null;

            }
            return null;
        }

    }
}
