package com.group4.quaratineapp.recommendations;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.group4.quaratineapp.R;

public class Recommendations extends AppCompatActivity {

    ImageView iView;
    Button nextButton;
    TextView instructionTitle;
    TextView instructionBody;
    int currentRecommendation = 1;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recommendations);


        iView = findViewById(R.id.rec_image);
        nextButton = findViewById(R.id.nextButton);
        instructionTitle = findViewById(R.id.instructionTitle);
        instructionBody = findViewById(R.id.instructionBody);

        nextRecommendation(currentRecommendation);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nextRecommendation(currentRecommendation);
            }
        });

    }

    private void nextRecommendation(int cRec) {

        //TODO:
        // make a more dynamic solution to display instances of recommendations in current activity

        switch(cRec) {
            case 1: {
                iView.setImageResource(R.drawable.wash_hands);
                instructionTitle.setText("Wash your hands");
                instructionBody.setText("Frequent washing of hands reduce disease by 79%");
                currentRecommendation = 2;
                break;
            }
            case 2: {
                iView.setImageResource(R.drawable.social_distance);
                instructionTitle.setText("Exercise social distancing");
                instructionBody.setText("Being isolated reduces risk of contracting a lot of diseases");
                currentRecommendation = 3;
                break;
            }
            case 3: {
                iView.setImageResource(R.drawable.dont_touch);
                instructionTitle.setText("Don't touch eyes or face");
                instructionBody.setText("It will also make your skin prettier.");
                currentRecommendation = 4;
                break;
            }
            case 4: {
                iView.setImageResource(R.drawable.drink_beer);
                instructionTitle.setText("Drink beer");
                instructionBody.setText("It might make you happier. Or sadder.");
                currentRecommendation = 1;
                break;
            }
        }

    }


}
