package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class WinActivity extends AppCompatActivity {

    TextView res, gameTime;
    Button playAgain, goToList, saveRes;
    EditText name_text;

    @SuppressLint("SetTextI18n")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        res = findViewById(R.id.results);
        gameTime = findViewById(R.id.time);
        String result = getIntent().getStringExtra(getString(R.string.winner));
        String time = getIntent().getStringExtra(getString(R.string.game_time));
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

        name_text =  findViewById(R.id.enterName);
        saveRes = findViewById(R.id.saveResButton);

        Thread t1 = new Thread(()->{
            List<WinnerData> winnersData = winnerDao.getTopTen();
            boolean isFaster = false;
            for (WinnerData winner: winnersData) {
                if((Integer.parseInt(time) < Integer.parseInt(winner.score) && winnersData.size() >= 10) ||
                                                                winnersData.size() < 10){
                    isFaster = true;
                }
            }
            if (winnersData.size() != 0 && (!isFaster || result.contains("Tie"))){
                runOnUiThread(()->{
                    saveRes.setVisibility(View.INVISIBLE);
                    name_text.setVisibility(View.INVISIBLE);

                });
            }
        });
        t1.start();
        saveRes.setOnClickListener(view -> {
            String name = name_text.getText().toString();
                    Thread t = new Thread(() -> {
                if (!name.isEmpty()) {
                    int lastId = winnerDao.getLastId();
                    int currentId = lastId + 1;
                    Date date = new Date();
                    @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
                    String strDate= formatter.format(date);
                    WinnerData winnerData = new WinnerData(currentId, name, time, strDate);
                    winnerDao.insertAll(winnerData);
                    Intent intent = new Intent(this,WinnersListActivity.class);
                    intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                    startActivity(intent);
                }
                else
                    Toast.makeText(this, getString(R.string.Please_Enter_Name), Toast.LENGTH_SHORT).show();
            });
            t.start();
        });
    }

}