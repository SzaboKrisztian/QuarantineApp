package com.group4.quaratineapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.group4.quaratineapp.exclusion.Exclusion;
import com.group4.quaratineapp.recommendations.Recommendations;
import com.group4.quaratineapp.stats.Stats;
import com.group4.quaratineapp.symptoms.Symptoms;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void startExclusion(View view) {
        Intent intent = new Intent(this, Exclusion.class);
        startActivity(intent);
    }

    public void startRecommendations(View view) {
        Intent intent = new Intent(this, Recommendations.class);
        startActivity(intent);
    }

    public void startStats(View view) {
        Intent intent = new Intent(this, Stats.class);
        startActivity(intent);
    }

    public void startSymptoms(View view) {
        Intent intent = new Intent(this, Symptoms.class);
        startActivity(intent);
    }
}
