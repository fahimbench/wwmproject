package com.bgeiotdev.eval;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.ViewHolder> {

    private ArrayList<String> test;

    // Pass in the contact array into the constructor
    public LeaderboardAdapter(ArrayList<String> test) {
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
        String rrr = test.get(i);

        TextView pseudoview = viewHolder.pseudo;
        pseudoview.setText(rrr.toString());

        TextView posView = viewHolder.pos;
        posView.setText(rrr.toString());

        TextView scoreView = viewHolder.score;
        scoreView.setText(rrr.toString());
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


            pseudo = itemView.findViewById(R.id.pseudo_leaderboard);
            pos = itemView.findViewById(R.id.pos_leaderboard);
            score = itemView.findViewById(R.id.score_leaderboard);
        }
    }
}