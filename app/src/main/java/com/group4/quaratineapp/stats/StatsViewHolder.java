package com.group4.quaratineapp.stats;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.group4.quaratineapp.R;
import com.group4.quaratineapp.stats.model.Country;

public class StatsViewHolder extends RecyclerView.ViewHolder {

    private TextView label;
    private TextView count;
    private ConstraintLayout layout;

    public StatsViewHolder(@NonNull View itemView) {
        super(itemView);

        layout = (ConstraintLayout) itemView;
        count = layout.findViewById(R.id.stats_list_item_count);
        label = layout.findViewById(R.id.stats_list_item_country);
    }

    public void setCountry(Country country) {
        label.setText(country.getName());
        String countText = "Cases: " + DetailFragment.formatNumber(country.getConfirmed());
        count.setText(countText);
    }

    public void setClickListener(View.OnClickListener listener) {
        layout.setOnClickListener(listener);
    }
}
