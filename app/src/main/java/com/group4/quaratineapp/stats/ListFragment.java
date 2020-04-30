package com.group4.quaratineapp.stats;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.quaratineapp.R;
import com.group4.quaratineapp.stats.model.Country;

import java.util.List;

public class ListFragment extends Fragment {

    private List<Country> countryData;
    private RecyclerView theList;
    private Stats activity;

    public ListFragment(List<Country> countryData, Stats activity) {
        this.countryData = countryData;
        this.activity = activity;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_stats_list, container, false);
        theList = view.findViewById(R.id.stats_recycler);
        theList.setLayoutManager(new LinearLayoutManager(activity));
        theList.setAdapter(new CountryDataAdapter(countryData, activity));

        return view;
    }
}
