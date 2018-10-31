package com.warriorrat.inventorytracker;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Stackoverflow is love. Stackoverflow is life.
 */

public class MainActivity extends AppCompatActivity {

    ProductOpenHelper productHelper;
    ListView listView;
    ProductArrayAdapter customAdapter;
    TextView helpText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productHelper = ProductOpenHelper.getHelperObject(this);
        listView = (ListView) findViewById(R.id.list);
        helpText = (TextView) findViewById(R.id.helpText);
        listView.setEmptyView(helpText);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product value = (Product) listView.getItemAtPosition(position);
                Intent goToDetails = new Intent(MainActivity.this, ProductDetails.class);
                goToDetails.putExtra("ProductName", value.getProductName());
                startActivity(goToDetails);

            }
        });

    }

    public void deleteItems (View v) {
        productHelper.deleteProducts();
        update();
    }

    public void buyStuff(View v) {
        Intent i = new Intent(this, AddProduct.class);
        startActivity(i);

    }

    private void update() {
        ArrayList<Product> data = productHelper.readProduct();
        customAdapter = new ProductArrayAdapter(this, data);

        listView.setAdapter(customAdapter);
        listView.invalidateViews();
    }

    @Override
    protected void onResume() {
        super.onResume();
        update();
    }
}