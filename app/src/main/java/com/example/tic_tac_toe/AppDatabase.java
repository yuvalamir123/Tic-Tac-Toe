package com.example.tic_tac_toe;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {WinnerData.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {
    public abstract WinnerDao winnerDataDao();
}
