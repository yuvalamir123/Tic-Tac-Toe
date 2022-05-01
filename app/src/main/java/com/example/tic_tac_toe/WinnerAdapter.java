package com.example.tic_tac_toe;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class WinnerAdapter extends RecyclerView.Adapter<WinnerAdapter.ViewHolder> {

    private LayoutInflater layoutInflater;
    private List<WinnerData> data;

    public WinnerAdapter(Context layoutInflater, List<WinnerData> data) {
        this.layoutInflater =  LayoutInflater.from(layoutInflater);
        this.data = data;
    }

    @NonNull
    @Override
    public WinnerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = layoutInflater.inflate(R.layout.winner_recycler_view,viewGroup,false);
        return new WinnerAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WinnerAdapter.ViewHolder viewHolder, int i) {

        String name = data.get(i).getName();
        viewHolder.name.setText(name);

        String score = data.get(i).getScore();
        viewHolder.score.setText(score + " Seconds");

        String date = data.get(i).getDate();
        viewHolder.date.setText(date);
    }

    @Override
    public int getItemCount() {return data.size(); }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView name, score, date;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name);
            score = itemView.findViewById(R.id.score);
            date = itemView.findViewById(R.id.date);
        }
    }
}

