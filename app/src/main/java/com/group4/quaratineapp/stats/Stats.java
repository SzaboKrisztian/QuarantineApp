package com.group4.quaratineapp.stats;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.group4.quaratineapp.R;
import com.group4.quaratineapp.stats.model.Country;
import com.group4.quaratineapp.stats.model.DataDownloader;
import com.group4.quaratineapp.stats.model.DataReadyCallback;

import java.util.ArrayList;
import java.util.List;

public class Stats extends AppCompatActivity implements DataReadyCallback {

    private List<Country> countryData = new ArrayList<>();
    private TextView message;
    private ListFragment listFragment;
    private DetailFragment detailFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        // Get references to the views

        message = findViewById(R.id.stats_messageView);

        // Instantiate the fragments

        listFragment = new ListFragment(countryData, this);
        detailFragment = new DetailFragment(this);

        // Initialize the data downloader

        DataDownloader.getInstance().setCountryData(this.countryData);
        DataDownloader.getInstance().setReadyCallback(this);
        DataDownloader.getInstance().setContext(this);

        // And finally, start the download

        DataDownloader.getInstance().downloadData();
    }

    // This method will be called from the downloader when it's done

    @Override
    public void dataReady() {
        message.setVisibility(View.GONE);
        showList();
    }

    // This method will be called from the downloader in case of an error

    @Override
    public void error() {
        message.setVisibility(View.VISIBLE);
        message.setText("Error loading data.");
    }

    public void showDetail(Country country) {
        detailFragment.setCountry(country);
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.stats_theFrame, detailFragment)
            .commit();
    }

    public void showList() {
        getSupportFragmentManager().beginTransaction()
            .replace(R.id.stats_theFrame, listFragment)
            .commit();
    }
}
