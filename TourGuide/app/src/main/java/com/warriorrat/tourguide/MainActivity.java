package com.warriorrat.tourguide;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

/**
 * This app is designed for a short list of places to see. Mainly for things that are NOT
 * tourist attractions. There are plenty of other places that do that.
 */

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        /**
         * The rest of this code simple moves from one activity to another.
         */

        TextView food = (TextView)findViewById(R.id.food);

        food.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent numbersScreen = new Intent(MainActivity.this, FoodActivity.class);
                startActivity(numbersScreen);
            }
        });

        TextView hotels = (TextView)findViewById(R.id.hotels);

        hotels.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent familyScreen = new Intent(MainActivity.this, HotelsActivity.class);
                startActivity(familyScreen);
            }
        });

        TextView clubs = (TextView)findViewById(R.id.clubs);

        clubs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent colorsScreen = new Intent(MainActivity.this, ClubsActivity.class);
                startActivity(colorsScreen);
            }
        });

        TextView range = (TextView)findViewById(R.id.range);

        range.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent phrasesScreen = new Intent(MainActivity.this, RangeActivity.class);
                startActivity(phrasesScreen);
            }
        });

    }
}
