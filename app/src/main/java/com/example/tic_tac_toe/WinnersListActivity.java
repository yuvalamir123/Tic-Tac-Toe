package com.example.tic_tac_toe;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.ArraySet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

public class WinnersListActivity extends AppCompatActivity {

    RecyclerView recyclerView;

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_winners_list);

        SharedPreferences settings = getApplicationContext().getSharedPreferences("winnersss", 0);
        SharedPreferences.Editor editor = settings.edit();
        ArraySet<String> winnerData = new ArraySet<>();
        winnerData.add("Yuval");
        winnerData.add("6");
        winnerData.add(new Date().toString());
        editor.putStringSet("1", winnerData);

        editor.apply();

        recyclerView = findViewById(R.id.recycleView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        List<WinnerData> winnerDataList = new ArrayList<>();

        Set<String> getWinnerData1 = settings.getStringSet("1", winnerData);
        Object[] data = getWinnerData1.toArray();
        winnerDataList.add(new WinnerData(data[0].toString() ,data[1].toString(), data[2].toString()));

        WinnerAdapter winnerAdapter = new WinnerAdapter(this, winnerDataList);
        recyclerView.setAdapter(winnerAdapter);
    }
}