package com.warriorrat.inventorytracker;

import android.graphics.Bitmap;

/**
 * Created by Jake on 6/30/2016.
 */
public class Product {

    private int mInventory;
    private int mPrice;
    private String mProductName;
    private String mSupplier;
    private String mContact;
    private Bitmap mBitmap;


    public Product(String productName, int inventory, int price, String supplier, String contact, Bitmap bitmap) {
        mProductName = productName;
        mInventory = inventory;
        mPrice = price;
        mSupplier = supplier;
        mContact = contact;
        mBitmap = bitmap;
    }

    public int getInventory() {
        return mInventory;
    }

    public String getProductName() {
        return mProductName;
    }

    public int getPrice() {
        return mPrice;
    }

    public String getSupplier() {
        return mSupplier;
    }

    public String getContact() {
        return mContact;
    }

    public Bitmap getBitmap() {
        return mBitmap;
    }

    public void setInventory(int amount) {
        mInventory = amount;

    }
}
