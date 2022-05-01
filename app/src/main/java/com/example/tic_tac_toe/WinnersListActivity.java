package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import java.util.List;

public class WinnersListActivity extends AppCompatActivity {

    RecyclerView recyclerView;
    Button backMain;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners_list);


        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        WinnerDao winnerDao = db.winnerDataDao();

        Thread t = new Thread(() -> {
            List<WinnerData> winnerDataList = winnerDao.getTopTen();
            runOnUiThread(() -> {
                WinnerAdapter winnerAdapter = new WinnerAdapter(this, winnerDataList);
                recyclerView.setAdapter(winnerAdapter);
            });
        });
        t.start();

        backMain = findViewById(R.id.backMainButton);
        backMain.setOnClickListener(view -> {
            Intent intent = new Intent(this,MainActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });
    }
}