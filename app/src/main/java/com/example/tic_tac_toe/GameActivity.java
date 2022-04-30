package com.example.tic_tac_toe;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

public class GameActivity extends AppCompatActivity {

    ImageView loaction1, loaction2, loaction3, loaction4 ,loaction5, loaction6 ,loaction7 ,loaction8 ,loaction9;
    boolean Xturn = true;
    HashMap<Integer, Boolean> state = new HashMap<>();
    TextView turn;

    int[][][] threeD_arr = new int[10][20][30];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        turn = findViewById(R.id.turn);
        turn.setText("X turn");

        loaction1 = findViewById(R.id.location1);
        loaction2 = findViewById(R.id.location2);
        loaction3 = findViewById(R.id.location3);
        loaction4 = findViewById(R.id.location4);
        loaction5 = findViewById(R.id.location5);
        loaction6 = findViewById(R.id.location6);
        loaction7 = findViewById(R.id.location7);
        loaction8 = findViewById(R.id.location8);
        loaction9 = findViewById(R.id.location9);

        ImageView[] imageViews = {loaction1, loaction2, loaction3, loaction4, loaction5, loaction6, loaction7, loaction8, loaction9};

        for (int i = 0; i < imageViews.length; i++) {
            int finalI = i;
            imageViews[i].setOnClickListener(view -> {
                if (!state.containsKey(finalI + 1)) {
                    if (Xturn)
                        imageViews[finalI].setImageResource(R.drawable.x);
                    else
                        imageViews[finalI].setImageResource(R.drawable.o);
                    state.put(finalI + 1, Xturn);
                    turn.setText((Xturn ? "O" : "X") + " turn");
                    Xturn = !Xturn;
                }
            });
        }
    }


}