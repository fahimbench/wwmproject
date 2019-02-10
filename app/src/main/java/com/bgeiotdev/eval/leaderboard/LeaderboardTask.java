package com.bgeiotdev.eval.leaderboard;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.ProgressBar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Scanner;

public class LeaderboardTask extends AsyncTask<Integer,Void,JSONObject> {

    private ProgressBar bar;
    private RecyclerView rcView;
    private ArrayList<JSONObject> arrstr;
    private LeaderboardAdapter adapter;
    private Context context;

    public LeaderboardTask(Context context){
        this.context = context;
    }
    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected JSONObject doInBackground(Integer... i) {

        String json;
        JSONObject jObj = null;
        try {
            json = getResponseFromHttpUrl(buildUrl("d"));

            try {
                jObj = new JSONObject(json);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }

            // return JSON String
            return jObj;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jObj;
    }

    @Override
    protected void onPostExecute(JSONObject json) {
        super.onPostExecute(json);
        JSONArray members = null;

        try {
            // Getting JSON Array
            members = json.getJSONArray("hydra:member");

            for (int i = 0; i < members.length(); i++) {
                JSONObject obj = members.getJSONObject(i);
                arrstr.add(obj);
            }
            }catch(JSONException e){
                e.printStackTrace();
            }

        adapter = new LeaderboardAdapter(arrstr);
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(context));
        bar.setVisibility(View.GONE);
    }

    public void setAll(ProgressBar bar, RecyclerView rcView, LeaderboardAdapter adapter, ArrayList<JSONObject> arrstr) {
        this.bar = bar;
        this.rcView = rcView;
        this.adapter = adapter;
        this.arrstr = arrstr;

    }


    public static URL buildUrl(String str) {
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .encodedAuthority("domina.serveo.net")
                .appendPath("api")
                .appendPath("users")
                .build();

        URL url = null;
        try {
            url = new URL(builder.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        return url;
    }

    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");

            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }

    }
}
