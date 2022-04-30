package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    TextView res, game_time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        res = findViewById(R.id.results);
        game_time = findViewById(R.id.time);
        String result = getIntent().getStringExtra("winner");
        String time = getIntent().getStringExtra("game time");
        res.setText(result);
        game_time.setText(time);
    }

}