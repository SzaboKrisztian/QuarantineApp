package com.group4.quaratineapp.recommendations;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.group4.quaratineapp.MainActivity;
import com.group4.quaratineapp.R;

public class Recommendation_fragment extends Fragment {

    private MainActivity mainActivity;


    public static Recommendation_fragment newInstance() {
        Recommendation_fragment detailFragment = new Recommendation_fragment();
        return detailFragment;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.rec_fragment_layout, container, false);

        //TODO:
        // bind elements

        return view;
    }

}
