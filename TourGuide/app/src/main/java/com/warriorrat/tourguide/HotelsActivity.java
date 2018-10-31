package com.warriorrat.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class HotelsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attractions);

        // Creates an ArrayList
        ArrayList<AreaLocation> name = new ArrayList<>();

        // Adds objects to the ArrayList
        name.add(new AreaLocation("Country Inn", "(904) 772-7771", "5945 Youngerman CIR E", R.drawable.hotel));
        name.add(new AreaLocation("Best Western", "(904) 281-0900", "4660 Salisbury RD", R.drawable.hotel));
        name.add(new AreaLocation("Aloft", "(904) 998-4448", "4812 W Deer Lake DR", R.drawable.hotel));
        name.add(new AreaLocation("Marble Waters", "(904) 337-1700", "45 Kernan BLVD N", R.drawable.hotel));
        name.add(new AreaLocation("DoubleTree", "(904) 398-8800", "1201 Riverplace BLVD", R.drawable.hotel));
        name.add(new AreaLocation("Red Roof Inn", "(904) 777-1000", "315 Blanding BLVD", R.drawable.hotel));

        ListView listView = (ListView) findViewById(R.id.list_view);
        LocationAdapter placeAdapter = new LocationAdapter(this, name, R.color.category_food);
        listView.setAdapter(placeAdapter);
    }
}