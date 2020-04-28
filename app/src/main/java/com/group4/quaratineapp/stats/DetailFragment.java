package com.group4.quaratineapp.stats;

import android.icu.text.NumberFormat;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group4.quaratineapp.R;
import com.group4.quaratineapp.stats.model.Country;

import java.util.Locale;

public class DetailFragment extends Fragment {

    private Stats activity;
    private Country country;

    public DetailFragment(Stats activity) {
        this.activity = activity;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats_detail, container, false);
        TextView countryName = view.findViewById(R.id.stats_detail_country_name);
        countryName.setText(country.getName());
        TextView countryCode = view.findViewById(R.id.stats_detail_country_code);
        countryCode.setText(country.getCode());
        TextView numConfirmed = view.findViewById(R.id.stats_detail_num_confirmed);
        numConfirmed.setText(formatNumber(country.getConfirmed()));
        TextView numActive = view.findViewById(R.id.stats_detail_num_active);
        numActive.setText(formatNumber(country.getActive()));
        TextView numRecovered = view.findViewById(R.id.stats_detail_num_recovered);
        numRecovered.setText(formatNumber(country.getRecovered()));
        TextView numDeceased = view.findViewById(R.id.stats_detail_num_deceased);
        numDeceased.setText(formatNumber(country.getDead()));
        Button backButton = view.findViewById(R.id.stats_detail_button_back);
        backButton.setOnClickListener(v -> activity.showList());
        return view;
    }
    
    public static String formatNumber(int number) {
        return NumberFormat.getNumberInstance(Locale.ENGLISH).format(number);
    }
}
