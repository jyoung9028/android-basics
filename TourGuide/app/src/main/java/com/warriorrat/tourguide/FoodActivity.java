package com.warriorrat.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class FoodActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attractions);

        // Creates an ArrayList
        ArrayList<AreaLocation> name = new ArrayList<>();

        // Adds objects to the ArrayList
        name.add(new AreaLocation("AppleBee's", "(904) 771-0000", "8635-201 Blanding BLVD", R.drawable.applebees));
        name.add(new AreaLocation("Chili's", "(904) 264-6907", "1664 Wells RD", R.drawable.chilis));
        name.add(new AreaLocation("Olive Garden", "(904) 777-9827", "6050 Youngerman Circle", R.drawable.olive_garden));
        name.add(new AreaLocation("Col Mustard's", "(904) 374-2620", "8060 Philips HWY", R.drawable.burger));
        name.add(new AreaLocation("The Loop", "(904) 269-0756", "550 Wells RD", R.drawable.the_loop));
        name.add(new AreaLocation("IHOP", "(904) 272-0690", "315 Blanding BLVD", R.drawable.ihop));

        ListView listView = (ListView) findViewById(R.id.list_view);
        LocationAdapter placeAdapter = new LocationAdapter(this, name, R.color.category_food);
        listView.setAdapter(placeAdapter);
    }
}