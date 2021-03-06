package com.bgeiotdev.eval.Leaderboard;

import android.app.Activity;
import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;
import android.support.v7.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;

import com.bgeiotdev.eval.R;

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

public class LeaderboardTask extends AsyncTask<Integer, Void, JSONObject[]> {

    private ProgressBar bar;
    private RecyclerView rcView;
    private ArrayList<JSONObject> arrstr;
    private LeaderboardAdapter adapter;
    private Context context;
    private int difficulty;
    private static Context cont;

    private JSONObject jsonUser;
    private JSONObject jsonGame;

    public LeaderboardTask(Context context) {
        this.context = context;
        this.cont = context;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        bar.setVisibility(View.VISIBLE);
    }

    @Override
    protected JSONObject[] doInBackground(Integer... i) {

        jsonGame = requestJSON("games");
        jsonUser = requestJSON("users");

        JSONObject[] jsonArr = {jsonGame, jsonUser};

        return jsonArr;
    }

    @Override
    protected void onPostExecute(JSONObject[] json) {
        super.onPostExecute(json);

        JSONObject jsonG = json[0];
        JSONObject jsonU = json[1];
        JSONArray members1 = null;
        JSONArray members2 = null;
        try {
            // Getting JSON Array
            members1 = jsonG.getJSONArray("hydra:member");
            members2 = jsonU.getJSONArray("hydra:member");
            arrstr.clear();
            for (int i = 0; i < members1.length(); i++) {
                JSONObject obj1 = members1.getJSONObject(i);
                if (obj1.getInt("type") == this.difficulty) {
                    String[] pseudo = obj1.getString("idUser").split("/");
                    JSONObject obj2 = getMembersJSONObject(members2, Integer.valueOf(pseudo[pseudo.length - 1]));
                    obj1.put("idUser", obj2.getString("pseudo"));
                    arrstr.add(obj1);
                }

            }
            if (this.difficulty == 0) {
                String pseudo_settings = PreferenceManager.getDefaultSharedPreferences(context).getString("pseudo_settings", null);

                boolean find1 = false;
                boolean find2 = false;
                boolean exit = true;
                for (int i = 0; exit && i < members1.length(); i++) {
                    JSONObject obj1 = members1.getJSONObject(i);
                    if (obj1.getInt("type") == 1 && !find1) {
                        String[] pseudo = obj1.getString("idUser").split("/");
                        JSONObject obj2 = getMembersJSONObject(members2, Integer.valueOf(pseudo[pseudo.length - 1]));
                        String ps = obj2.getString("pseudo");
                        if(ps.equals(pseudo_settings)) {
                            obj1.put("idUser", ps);
                            find1 = true;
                            arrstr.add(obj1);
                        }

                    }

                    if (obj1.getInt("type") == 2 && !find2) {
                        String[] pseudo = obj1.getString("idUser").split("/");
                        JSONObject obj2 = getMembersJSONObject(members2, Integer.valueOf(pseudo[pseudo.length - 1]));
                        String ps = obj2.getString("pseudo");

                        if(ps.equals(pseudo_settings)) {
                            obj1.put("idUser", ps);
                            find2 = true;
                            arrstr.add(obj1);
                        }
                    }
                    if(find1 && find2){ exit = false; }
                }
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        adapter = new LeaderboardAdapter(arrstr, difficulty);
        rcView.setAdapter(adapter);
        rcView.setLayoutManager(new LinearLayoutManager(context));
        bar.setVisibility(View.GONE);
    }

    public void setAll(ProgressBar bar, RecyclerView rcView, LeaderboardAdapter adapter, ArrayList<JSONObject> arrstr, int difficculty) {
        this.bar = bar;
        this.rcView = rcView;
        this.adapter = adapter;
        this.arrstr = arrstr;
        this.difficulty = difficculty;
    }


    public static URL buildUrl(String str) {
        String mystring = ((Activity) cont).getResources().getString(R.string.host);
        Uri.Builder builder = new Uri.Builder();
        builder.scheme("https")
                .encodedAuthority(mystring)
                .appendPath("api")
                .appendPath(str)
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

    public JSONObject requestJSON(String query) {
        String json;
        JSONObject jObj = null;
        try {
            json = getResponseFromHttpUrl(buildUrl(query));

            try {
                jObj = new JSONObject(json);
            } catch (JSONException e) {
                Log.e("JSON Parser", "Error parsing data " + e.toString());
            }
            return jObj;

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jObj;
    }

    public JSONObject getMembersJSONObject(JSONArray jsonArray, Integer nbr) {
        for (int i = 0; i < jsonArray.length(); i++) {
            try {
                if (jsonArray.getJSONObject(i).getInt("id") == nbr) {
                    return jsonArray.getJSONObject(i);
                }
            } catch (JSONException e) {
                e.getMessage();
            }

        }
        return null;
    }
}
