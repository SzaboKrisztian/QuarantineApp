package com.group4.quaratineapp.symptoms;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.group4.quaratineapp.R;

public class Symptoms extends AppCompatActivity {
 ListView mListView;
 TextView mTextView;

 int symptoms_images[] =    {R.drawable.fever,
                            R.drawable.chills,
                            R.drawable.coughing,
                            R.drawable.fatigue,
                            R.drawable.headaches,
                            R.drawable.shortness,
                            R.drawable.sorethroats,
                            R.drawable.nausea,
                            R.drawable.smells,

                                                };
 String[] sypmtoms = {"High temperature(fever)",
                    "Chills","Dry Cough",
                    "Fatique","Headaches",
                    "Shorthness of breath",
                    "Sore throats",
                    "Nausea/Vomitting",
                    "Lost of Smell/Taste"};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_symptoms);
        mListView = findViewById(R.id.symptoms_list);

        CustomLayout customLayout = new CustomLayout();
        mListView.setAdapter(customLayout);

    }

        class CustomLayout extends BaseAdapter{

            @Override
            public int getCount() {
                return symptoms_images.length;
            }

            @Override
            public Object getItem(int position) {
                return null;
            }

            @Override
            public long getItemId(int position) {
                return 0;
            }

            @Override
            public View getView(int position, View convertView, ViewGroup parent) {
                View view = getLayoutInflater().inflate(R.layout.custom_symptoms,null);

                ImageView mImageView = view.findViewById(R.id.symptoms_image);
                TextView mTextView = view.findViewById(R.id.imageText);
                mImageView.setImageResource(symptoms_images[position]);
                mTextView.setText(sypmtoms[position]);

                return view;

        }

    }
}
