package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class WinActivity extends AppCompatActivity {

    TextView res, gameTime;
    Button playAgain, goToList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_win);
        res = findViewById(R.id.results);
        gameTime = findViewById(R.id.time);
        String result = getIntent().getStringExtra("winner");
        String time = getIntent().getStringExtra("game time");
        res.setText(result);
        gameTime.setText(time);

        playAgain = findViewById(R.id.playAgainButton);
        playAgain.setOnClickListener(view -> {
            Intent intent = new Intent(this,GameActivity.class);
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
            startActivity(intent);
        });
    }

}