package com.bgeiotdev.eval.Others;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.bgeiotdev.eval.R;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class ConnectivityHelper {
    private static Context context;


    private static boolean isNetworkAvailable() {

        ConnectivityManager connectivityManager
                = (ConnectivityManager) ConnectivityHelper.context.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null;
    }

    public static boolean isConnectedToNetwork(Context context, String message) {
        ConnectivityHelper.context = context;
        String mystring = ((Activity) context).getResources().getString(R.string.host);
        //Verifie si il accede bien à internet
        if (isNetworkAvailable()) {
            try {
                HttpURLConnection urlc = (HttpURLConnection) (new URL("https://"+ mystring + "/api/users").openConnection());
                urlc.setRequestProperty("User-Agent", "Test");
                urlc.setRequestProperty("Connection", "close");
                urlc.setConnectTimeout(5000);
                urlc.connect();
                return (urlc.getResponseCode() == 200);
            } catch (IOException e) {
                return false;
            }
        } else {
            return false;
        }
    }

    public static  boolean isConnectedToNetwork(Context context) {
        return isConnectedToNetwork(context, "Vous n'êtes pas connecté à internet");
    }

}