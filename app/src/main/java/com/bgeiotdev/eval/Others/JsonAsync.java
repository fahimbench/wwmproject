package com.bgeiotdev.eval.Others;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

import com.bgeiotdev.eval.Classes.User.User;
import com.bgeiotdev.eval.R;

import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;


public class JsonAsync extends AsyncTask<String, Void, Void> {

    private URL url;
    private String type;
    private JSONObject jsonObject;

    public JsonAsync(URL url, String type, JSONObject jsonObject ) {
        this.url = url;
        this.type = type;
        this.jsonObject = jsonObject;
    }

    public Void doInBackground(String... arg0) {

        try {


            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setConnectTimeout(10000);
            connection.setInstanceFollowRedirects(false);
            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod(type);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            connection.setUseCaches(false);
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());


            wr.writeBytes(jsonObject.toString());


            wr.flush();
            wr.close();
            System.out.println(connection.getResponseMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}