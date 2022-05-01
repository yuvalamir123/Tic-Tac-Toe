package com.example.tic_tac_toe;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity
public class WinnerData {

    @PrimaryKey
    int id;

    @ColumnInfo(name = "name")
    String name;

    @ColumnInfo(name = "score")
    String score;

    @ColumnInfo(name = "date")
    String date;

    public WinnerData(int id, String name, String score, String date) {
        this.id = id;
        this.name = name;
        this.score = score;
        this.date = date;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
