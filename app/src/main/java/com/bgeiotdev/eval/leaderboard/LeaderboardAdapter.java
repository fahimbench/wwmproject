package com.bgeiotdev.eval.leaderboard;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.bgeiotdev.eval.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private ArrayList<JSONObject> test;

    // Pass in the contact array into the constructor
    public LeaderboardAdapter(ArrayList<JSONObject> test) {
        this.test = test;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int viewType) {
        Context context = viewGroup.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View contactView = inflater.inflate(R.layout.leaderboard_items, viewGroup, false);

        // Return a new holder instance
        ViewHolder viewHolder = new ViewHolder(contactView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        JSONObject obj = test.get(i);


        TextView posView = viewHolder.pos;
        TextView pseudoview = viewHolder.pseudo;
        TextView scoreView = viewHolder.score;

        try {
            posView.setText(obj.getString("id"));
            pseudoview.setText(obj.getString("firstname"));
            scoreView.setText(obj.getString("lastname"));
        }catch (JSONException e){
            e.getMessage();
        }

    }

    @Override
    public int getItemCount() {
        return test.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        public TextView pseudo;
        public TextView pos;
        public TextView score;


        public ViewHolder(View itemView) {

            super(itemView);



            pos = itemView.findViewById(R.id.pos_leaderboard);
            pseudo = itemView.findViewById(R.id.pseudo_leaderboard);
            score = itemView.findViewById(R.id.score_leaderboard);
        }
    }
}