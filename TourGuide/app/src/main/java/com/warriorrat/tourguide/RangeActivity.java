package com.warriorrat.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class RangeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attractions);

        // Creates an ArrayList
        ArrayList<AreaLocation> name = new ArrayList<>();

        // Adds objects to the ArrayList
        name.add(new AreaLocation("Gateway", "(904) 771-2937", "9301 Zambito AVE", R.drawable.gun_one));
        name.add(new AreaLocation("On Target Sports", "(904) 301-0700", "Wells Pond CT", R.drawable.gun_two));
        name.add(new AreaLocation("Basics", "(904) 276-9710", "179 College DR #5", R.drawable.gun_one));
        name.add(new AreaLocation("Jacksonville Clay", "(904) 757-4584", "12125 New Berlin RD", R.drawable.gun_two));
        name.add(new AreaLocation("Gun Gallery", "(904) 641-1619", "10268 Beach BLVD", R.drawable.gun_one));
        name.add(new AreaLocation("Tackle & Gun Club", "(904) 733-0541", "9010 San Jose BLVD", R.drawable.gun_two));

        ListView listView = (ListView) findViewById(R.id.list_view);
        LocationAdapter placeAdapter = new LocationAdapter(this, name, R.color.category_food);
        listView.setAdapter(placeAdapter);
    }
}