package com.warriorrat.tourguide;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ListView;

import java.util.ArrayList;

public class ClubsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.attractions);

        // Creates an ArrayList
        ArrayList<AreaLocation> name = new ArrayList<>();

        // Adds objects to the ArrayList
        name.add(new AreaLocation("Pure Night Club", "(800) 694-1253", "8206 Philips HWY", R.drawable.club));
        name.add(new AreaLocation("Eclipse", "No Phone Number Listed", "4219 St. Johns AVE", R.drawable.club));
        name.add(new AreaLocation("theLOFT", "(904) 551-1650", "925 King ST", R.drawable.club));
        name.add(new AreaLocation("Rusty's Pub", "(904) 771-4199", "8970 103rd ST", R.drawable.beer));
        name.add(new AreaLocation("Rascal's", "(904) 772-7335", "3950 Confederate Point", R.drawable.beer));
        name.add(new AreaLocation("Timuquana Liquors", "(904) 777-5940", "5398 Timuquana RD", R.drawable.beer));

        ListView listView = (ListView) findViewById(R.id.list_view);
        LocationAdapter placeAdapter = new LocationAdapter(this, name, R.color.category_food);
        listView.setAdapter(placeAdapter);
    }
}