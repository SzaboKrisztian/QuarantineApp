package com.group4.quaratineapp.recommendations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.quaratineapp.R;

public class Recommendations extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);

        int currentRecommendation = 1;

        ImageView iView = findViewById(R.id.rec_image);
        Button nextButton = findViewById(R.id.nextButton);
        TextView instructionTitle = findViewById(R.id.instructionTitle);
        TextView instructionBody = findViewById(R.id.instructionBody);

        //TODO:
        // make a more dynamic solution to display instances of recommendations in current activity
        iView.setImageResource(R.drawable.wash_hands);
        instructionTitle.setText("Wash your hands");
        instructionBody.setText("Frequent washing of hands reduce disease by 79%");



    }


}
