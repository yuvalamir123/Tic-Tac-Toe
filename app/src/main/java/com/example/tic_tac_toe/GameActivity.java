package com.example.tic_tac_toe;

import androidx.annotation.ColorInt;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class GameActivity extends AppCompatActivity {

    ImageView loaction1, loaction2, loaction3, loaction4 ,loaction5, loaction6 ,loaction7 ,loaction8 ,loaction9;
    boolean Xturn = true;
    HashMap<Integer, Boolean> state = new HashMap<>();
    TextView turn;
    TextView timePassed;

    long start, finish, timeElapsed;

    int[] win_location1 = {1,2,3};
    int[] win_location2 = {4,5,6};
    int[] win_location3 = {7,8,9};
    int[] win_location4 = {1,4,7};
    int[] win_location5 = {2,5,8};
    int[] win_location6 = {3,6,9};
    int[] win_location7 = {1,5,9};
    int[] win_location8 = {3,5,7};

    ArrayList<int[]> winner_locations = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        turn = findViewById(R.id.turn);
        turn.setText(R.string.Xturn);

        timePassed = findViewById(R.id.timePassed);

        loaction1 = findViewById(R.id.location1);
        loaction2 = findViewById(R.id.location2);
        loaction3 = findViewById(R.id.location3);
        loaction4 = findViewById(R.id.location4);
        loaction5 = findViewById(R.id.location5);
        loaction6 = findViewById(R.id.location6);
        loaction7 = findViewById(R.id.location7);
        loaction8 = findViewById(R.id.location8);
        loaction9 = findViewById(R.id.location9);

        winner_locations.add(win_location1);
        winner_locations.add(win_location2);
        winner_locations.add(win_location3);
        winner_locations.add(win_location4);
        winner_locations.add(win_location5);
        winner_locations.add(win_location6);
        winner_locations.add(win_location7);
        winner_locations.add(win_location8);

        ImageView[] imageViews = {loaction1, loaction2, loaction3, loaction4, loaction5, loaction6, loaction7, loaction8, loaction9};

        start = System.currentTimeMillis();
        Timer();

        for (int i = 0; i < imageViews.length; i++) {
            int finalI = i;
            imageViews[i].setOnClickListener(view -> {
                if (!state.containsKey(finalI + 1)) {
                    if (Xturn)
                        imageViews[finalI].setImageResource(R.drawable.x);
                    else
                        imageViews[finalI].setImageResource(R.drawable.o);
                    state.put(finalI + 1, Xturn);
                    CheckForWinner();
                    turn.setText((Xturn ? "O" : "X") + " turn");
                    Xturn = !Xturn;
                }
            });
        }
    }

    private void Timer() {
        new Thread(() -> {
            try {
                long currentTime = System.currentTimeMillis();
                timeElapsed = currentTime - start;
                timePassed.setText(timePassed.getText().toString().equals("") ? "" : "time passed: " + timeElapsed / 1000 + " Seconds" );
                Thread.sleep(1000);
                Timer();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }).start();
    }

    void CheckForWinner() {
        for (int[] win_location: winner_locations) {
            int Xscore = 0;
            int Oscore = 0;
            for (int i: win_location) {
                if (state.containsKey(i) && state.get(i))
                    Xscore += 1;
                else if (state.containsKey(i))
                    Oscore += 1;
            }
            if (Xscore == 3){
                //X win
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                Intent intent = new Intent(this,WinActivity.class);
                intent.putExtra("winner", "X is The winner");
                intent.putExtra("game time", String.valueOf(timeElapsed/ 1000));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                new Thread(() -> {
                    try {
                        turn.setText("Game Over");
                        turn.setTextColor(Color.RED);
                        timePassed.setText("");
                        Thread.sleep(1500);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            else if (Oscore == 3){
                //O win
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                Intent intent = new Intent(this,WinActivity.class);
                intent.putExtra("winner", "O is The winner");
                intent.putExtra("game time", String.valueOf(timeElapsed/ 1000));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                new Thread(() -> {
                    try {
                        turn.setText("Game Over");
                        turn.setTextColor(Color.RED);
                        timePassed.setText("");
                        Thread.sleep(1500);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
            }
            else if(state.keySet().size() == 9){
                //tie
                finish = System.currentTimeMillis();
                timeElapsed = finish - start;
                Intent intent = new Intent(this,WinActivity.class);
                intent.putExtra("winner", "No winner - its a Tie");
                intent.putExtra("game time", String.valueOf(timeElapsed/ 1000));
                intent.addFlags(Intent.FLAG_ACTIVITY_NO_HISTORY);
                new Thread(() -> {
                    try {
                        turn.setText("Game Over");
                        turn.setTextColor(Color.RED);
                        timePassed.setText("");
                        Thread.sleep(1500);
                        startActivity(intent);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }).start();
                break;
            }
        }
    }
}