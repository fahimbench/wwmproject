package com.bgeiotdev.eval.leaderboard;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bgeiotdev.eval.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private ArrayList<JSONObject> test;
    private int difficulty;
    // Pass in the contact array into the constructor
    public LeaderboardAdapter(ArrayList<JSONObject> test, int difficulty) {

        this.test = test;
        this.difficulty = difficulty;
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
        LinearLayout layoutdiff = viewHolder.layoutdiff;
        TextView hard = viewHolder.hard;
        TextView easy = viewHolder.easy;

        try {
            if(this.difficulty != 0){
                posView.setText(String.valueOf(i+1));
                pseudoview.setText(obj.getString("idUser"));
                scoreView.setText(secondsToString(Integer.valueOf(obj.getString("score"))));
            }else{
                posView.setText("Meilleur temps : ");


                if(Integer.valueOf(obj.getString("type")) == 1){

                    easy.setVisibility(View.VISIBLE);
                }else{

                    hard.setVisibility(View.VISIBLE);
                }

                scoreView.setText(secondsToString(Integer.valueOf(obj.getString("score"))));
            }

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
        public LinearLayout layoutdiff;
        public TextView hard;
        public TextView easy;
        public ViewHolder(View itemView) {

            super(itemView);



            pos = itemView.findViewById(R.id.pos_leaderboard);
            pseudo = itemView.findViewById(R.id.pseudo_leaderboard);
            score = itemView.findViewById(R.id.score_leaderboard);
            layoutdiff = itemView.findViewById(R.id.layout_diff);
            hard = itemView.findViewById(R.id.for_hard);
            easy = itemView.findViewById(R.id.for_easy);
        }
    }

    private String secondsToString(int pTime) {
        return String.format("%02d:%02d", pTime / 60, pTime % 60);
    }
}