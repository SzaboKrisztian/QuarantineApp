package com.group4.quaratineapp.stats;

import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.quaratineapp.R;
import com.group4.quaratineapp.stats.model.Country;

import java.util.List;

public class CountryDataAdapter extends RecyclerView.Adapter<StatsViewHolder> {

    private List<Country> countryData;
    private Stats activity;

    public CountryDataAdapter(List<Country> countryData, Stats activity) {
        this.countryData = countryData;
        this.activity = activity;
    }

    @NonNull
    @Override
    public StatsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new StatsViewHolder(LayoutInflater.from(parent.getContext())
                .inflate(R.layout.stats_list_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull StatsViewHolder holder, int position) {
        holder.setCountry(countryData.get(position));
        holder.setClickListener(v -> {
            activity.showDetail(countryData.get(position));
        });
    }

    @Override
    public int getItemCount() {
        return countryData.size();
    }
}
