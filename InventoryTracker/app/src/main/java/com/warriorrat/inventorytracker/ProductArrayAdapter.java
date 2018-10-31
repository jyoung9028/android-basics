package com.warriorrat.inventorytracker;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

/**
 * Created by Jake on 6/30/2016.
 */
public class ProductArrayAdapter extends ArrayAdapter<Product> {

    public ProductArrayAdapter(Context context, ArrayList<Product> objects) {
        super(context, 0, objects);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View listItemView = convertView;
        if (listItemView == null) {
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.list_item, parent, false);
        }
        final Product product = getItem(position);
        TextView itemName = (TextView) listItemView.findViewById(R.id.itemName);
        final TextView inventoryCount = (TextView) listItemView.findViewById(R.id.inventoryCount);
        TextView itemPrice = (TextView) listItemView.findViewById(R.id.priceTextAmount);

        Button sellFromHome = (Button) listItemView.findViewById(R.id.sellFromHome);
        sellFromHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int currentStock = product.getInventory();
                if (currentStock <= 0) {
                    Toast.makeText(getContext(), "Not enough stock on hand", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    product.setInventory(--currentStock);
                    ProductOpenHelper.getHelperObject(null).updateProduct(product);
                    inventoryCount.setText("" + currentStock);
                }
            }
        });


        // Displays on the listView the information desired

        itemName.setText(product.getProductName());
        inventoryCount.setText(String.valueOf(product.getInventory()));
        itemPrice.setText(String.valueOf(product.getPrice()));
        return listItemView;
    }
}
