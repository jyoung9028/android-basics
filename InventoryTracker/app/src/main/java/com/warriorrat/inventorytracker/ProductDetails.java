package com.warriorrat.inventorytracker;

import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class ProductDetails extends AppCompatActivity {

    TextView detailName, detailPrice, detailStock, detailSupplier, detailContactInfo;
    ImageView detailImage;
    private Product productInformation;
    private ProductOpenHelper productHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_product_details);

        detailName = (TextView) findViewById(R.id.detailName);
        detailPrice = (TextView) findViewById(R.id.detailPrice);
        detailStock = (TextView) findViewById(R.id.detailStock);
        detailSupplier = (TextView) findViewById(R.id.detailSupplier);
        detailContactInfo = (TextView) findViewById(R.id.detailContactInfo);
        detailImage = (ImageView) findViewById(R.id.imageView);

        String productName = getIntent().getExtras().getString("ProductName");
        productHelper = ProductOpenHelper.getHelperObject(this);
        productInformation = productHelper.getProductDetails(productName);

        detailName.setText(productInformation.getProductName());
        detailPrice.setText("" + productInformation.getPrice());
        detailStock.setText("" + productInformation.getInventory());
        detailSupplier.setText(productInformation.getSupplier());
        detailContactInfo.setText(productInformation.getContact());
        detailImage.setImageBitmap(productInformation.getBitmap());
    }

    public void decreaseStock(View v) {

        int currentStock = productInformation.getInventory();
        if (currentStock <= 0) {
            Toast.makeText(this, "Not enough stock on hand", Toast.LENGTH_SHORT).show();
            return;
        } else {
            productInformation.setInventory(--currentStock);
            ProductOpenHelper.getHelperObject(null).updateProduct(productInformation);
            detailStock.setText("" + currentStock);
        }
    }

    public void increaseStock(View v) {

        int currentStock = productInformation.getInventory();
        productInformation.setInventory(++currentStock);
        ProductOpenHelper.getHelperObject(null).updateProduct(productInformation);
        detailStock.setText("" + currentStock);
    }

    public void orderMore(View v) {

        Intent intent = new Intent(Intent.ACTION_SENDTO);
        intent.setData(Uri.parse("mailto:")); // only email apps should handle this
        intent.putExtra(Intent.EXTRA_SUBJECT, "Order more of " + detailName.getText().toString());
        intent.putExtra(Intent.EXTRA_EMAIL, new String[] {detailContactInfo.getText().toString()});
        if (intent.resolveActivity(getPackageManager()) != null) {
            startActivity(intent);
        }
    }

    public void removeItem(View v) {

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setMessage("Are you sure you want to delete this entry?")
                .setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        productHelper.deleteSingleEntry(productInformation.getProductName());
                        Toast.makeText(getApplicationContext(), "Entry deleted", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                })
                .setNegativeButton("No", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        return;
                    }
                });
        // Create the AlertDialog object and return it
        builder.create().show();

    }
}
