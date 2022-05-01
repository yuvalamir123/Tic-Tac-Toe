package com.example.tic_tac_toe;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

@Dao
public interface WinnerDao {
    @Query("SELECT * FROM winnerData")
    List<WinnerData> getAll();

    @Query("SELECT * FROM winnerData WHERE id IN (:winnerIds)")
    List<WinnerData> loadAllByIds(int[] winnerIds);

    @Query("SELECT * FROM winnerData WHERE name LIKE :name LIMIT 1")
    WinnerData findByName(String name);

    @Query("SELECT * FROM winnerData ORDER BY score LIMIT 10")
    List<WinnerData> getTopTen();

    @Query("SELECT id FROM winnerData ORDER BY id desc LIMIT 1")
    int getLastId();

    @Insert
    void insertAll(WinnerData... winnersData);

    @Delete
    void delete(WinnerData winnerData);
}
