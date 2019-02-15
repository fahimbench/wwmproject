package com.bgeiotdev.eval.Others;

import android.app.Activity;
import android.content.Context;
import android.os.AsyncTask;

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


public class JsonAsync  extends AsyncTask<String, Void, Void> {

    private String request;
    private String type;
    private JSONObject jsonParam;

    public JsonAsync(String request, String type, JSONObject jsonParam){
        this.request = request;
        this.type = type;
        this.jsonParam = jsonParam;
    }

    public Void doInBackground(String... arg0) {

        URL url = null;
        try {
            url = new URL(request);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setDoOutput(true);
            connection.setDoInput(true);
            connection.setRequestMethod("POST");
            connection.setConnectTimeout(10000);
            connection.setRequestProperty("Content-Type", "application/json");
            connection.setRequestProperty("charset", "utf-8");
            DataOutputStream wr = new DataOutputStream(connection.getOutputStream());

            System.out.println(jsonParam.toString());
            wr.writeBytes(jsonParam.toString());

            wr.flush();
            wr.close();
            connection.getResponseMessage();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }
}