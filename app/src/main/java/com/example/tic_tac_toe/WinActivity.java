package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WinActivity extends AppCompatActivity {

    TextView res, gameTime;
    Button playAgain, goToList, saveRes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        res = findViewById(R.id.results);
        gameTime = findViewById(R.id.time);
        String result = getIntent().getStringExtra("winner");
        String time = getIntent().getStringExtra("game time");
        res.setText(result);
        gameTime.setText("game time: " + Long.parseLong(time) + " Seconds");

        playAgain = findViewById(R.id.playAgainButton);
        playAgain.setOnClickListener(view -> {
            Intent intent = new Intent(this,GameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });

        goToList = findViewById(R.id.goToRecordButton);
        goToList.setOnClickListener(view -> {
            Intent intent = new Intent(this,WinnersListActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });

        AppDatabase db = Room.databaseBuilder(getApplicationContext(),
                AppDatabase.class, "database-name").build();

        WinnerDao winnerDao = db.winnerDataDao();

        saveRes = findViewById(R.id.saveResButton);
        saveRes.setOnClickListener(view -> {
            String name = ((TextView) findViewById(R.id.enterName)).getText().toString();
            Thread t = new Thread(() -> {
                if (name != null && !name.isEmpty()) {
                    int lastId = winnerDao.getLastId();
                    int currentId = lastId + 1;
                    Date date = new Date();
                    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate= formatter.format(date);
                    WinnerData winnerData = new WinnerData(currentId, name, time + " Seconds", strDate);
                    winnerDao.insertAll(winnerData);
                    Intent intent = new Intent(this,WinnersListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, "Please Enter Name", Toast.LENGTH_SHORT).show();
            });
            t.start();
        });
    }

}