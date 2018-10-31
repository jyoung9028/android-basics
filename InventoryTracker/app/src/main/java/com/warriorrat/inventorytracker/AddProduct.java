package com.warriorrat.inventorytracker;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

public class AddProduct extends AppCompatActivity {

    EditText productName, productPrice, inventoryAmount, supplierInformation, contactInformation;
    ProductOpenHelper helpMe;
    ImageView possibleImage;
    Bitmap myImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_product);

        productName = (EditText) findViewById(R.id.productField);
        productPrice = (EditText) findViewById(R.id.priceField);
        inventoryAmount = (EditText) findViewById(R.id.inventoryField);
        supplierInformation = (EditText) findViewById(R.id.supplyField);
        contactInformation = (EditText) findViewById(R.id.contactField);
        possibleImage = (ImageView) findViewById(R.id.imageView);
        helpMe = ProductOpenHelper.getHelperObject(this);

        Toast.makeText(this, "Click on Pizza Slice to upload an image", Toast.LENGTH_LONG).show();
    }

    public void saveItem(View v) {

        // Check to see if inputs are valid
        String addProductName = productName.getText().toString();
        if (addProductName.trim().equals("")) {
            Toast.makeText(AddProduct.this, "Name is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        int addProductPrice;
        try {
            addProductPrice = Integer.parseInt(productPrice.getText().toString());
            if (addProductPrice < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(AddProduct.this, "Price is invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        int addInventory;
        try {
            addInventory = Integer.parseInt(inventoryAmount.getText().toString());
            if (addInventory < 0) {
                throw new NumberFormatException();
            }
        } catch (NumberFormatException e) {
            Toast.makeText(AddProduct.this, "Inventory is invalid", Toast.LENGTH_SHORT).show();
            return;
        }
        String addSupplierInformation = supplierInformation.getText().toString();
        if (addSupplierInformation.trim().equals("")) {
            Toast.makeText(AddProduct.this, "Supplier is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        String addContactInformation = contactInformation.getText().toString();
        if (addContactInformation.trim().equals("")) {
            Toast.makeText(AddProduct.this, "Contact is empty", Toast.LENGTH_SHORT).show();
            return;
        }

        // Create and add product to database
        if (myImage == null) {
            Toast.makeText(AddProduct.this, "Please upload an image", Toast.LENGTH_SHORT).show();
            return;
        }
        Product item = new Product(addProductName, addInventory, addProductPrice,
                addSupplierInformation, addContactInformation, myImage);
        helpMe.addProduct(item);
        finish();

    }

    public void pickImage(View v) {
        Intent chooseImage = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(chooseImage, 3);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 3 && resultCode == RESULT_OK && data != null) {
            Uri selectedImage = data.getData();
            String[] filePathColumn = {MediaStore.Images.Media.DATA};
            Cursor cursor = getContentResolver().query(selectedImage, filePathColumn, null, null, null);
            try {
                cursor.moveToFirst();
            } catch (Exception e) {
                Log.e(getClass().getName(), "Exception on adding image");
            }
            int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
            String imagePath = cursor.getString(columnIndex);
            cursor.close();

            myImage = BitmapFactory.decodeFile(imagePath);

            // Resize an image to make it smaller in case of large images
            int imageBigSide = Math.max(myImage.getHeight(), myImage.getWidth());

            // This shows the divisor for our image to bring it down to a correct size (300x300)
            if (imageBigSide > 300) {
                int scaleModifier = imageBigSide / 300;
                myImage = Bitmap.createScaledBitmap(myImage, myImage.getWidth() / scaleModifier,
                        myImage.getHeight() / scaleModifier, true);
            }
            possibleImage.setImageBitmap(myImage);

        }
    }
}
