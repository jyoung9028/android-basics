package com.warriorrat.inventorytracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;

/**
 * Created by Jake on 6/30/2016.
 */
public class ProductOpenHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "products.db";
    public static final String TABLE_NAME = "products";
    public static final String COLUMN_PRODUCTNAME = "ProductName";
    public static final String COLUMN_PRICE = "Price";
    public static final String COLUMN_ONHAND = "OnHand";
    public static final String COLUMN_SUPPLIER = "Supplier";
    public static final String COLUMN_CONTACT = "Contact";
    public static final String COLUMN_IMAGE = "Image";

    // Allows only one Object to be created
    private static ProductOpenHelper helperObject;

    public static ProductOpenHelper getHelperObject(Context context) {
        if (helperObject == null) {
            helperObject = new ProductOpenHelper(context.getApplicationContext());
        }
        return helperObject;
    }

    // Apart of deal from above, keep Constructor private
    private ProductOpenHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    // Use to convert bitmap into bytes
    private static byte[] getBytes(Bitmap bitmap) {
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 0, stream);
        return stream.toByteArray();
    }

    // Use to convert bytes back to bitmap
    private static Bitmap getBitmap(byte[] image) {
        return BitmapFactory.decodeByteArray(image, 0, image.length);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + TABLE_NAME + "(" +
                COLUMN_PRODUCTNAME + " TEXT PRIMARY KEY NOT NULL, " +
                COLUMN_ONHAND + " INTEGER NOT NULL, " +
                COLUMN_PRICE + " INTEGER NOT NULL, " +
                COLUMN_SUPPLIER + " TEXT NOT NULL, " +
                COLUMN_CONTACT + " TEXT NOT NULL, " +
                COLUMN_IMAGE + " BLOB NOT NULL " +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    //Add a new row to the database.
    public void addProduct(Product product) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_PRODUCTNAME, product.getProductName());
        values.put(COLUMN_PRICE, product.getPrice());
        values.put(COLUMN_ONHAND, product.getInventory());
        values.put(COLUMN_SUPPLIER, product.getSupplier());
        values.put(COLUMN_CONTACT, product.getContact());
        values.put(COLUMN_IMAGE, getBytes(product.getBitmap()));

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(TABLE_NAME, null, values); //Writing to database now.
        sqLiteDatabase.close();
    }

    public void updateProduct(Product product) {
        ContentValues values = new ContentValues();

        values.put(COLUMN_ONHAND, product.getInventory());

        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.update(TABLE_NAME, values, COLUMN_PRODUCTNAME + " = ?", new String[]{product.getProductName()}); //Writing to database now.
        sqLiteDatabase.close();
    }

    //Delete all products from the database.
    public void deleteProducts() {
        SQLiteDatabase byeFelicia = getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + TABLE_NAME;
        byeFelicia.execSQL(query);
        onCreate(byeFelicia);
    }

    public Product getProductDetails(String productName) {
        String fieldProductName;
        int fieldProductPrice;
        int fieldProductStock;
        String fieldSupplier;
        String fieldContactInformation;
        byte[] fieldImage;

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor cursor = sqLiteDatabase.query(TABLE_NAME, null,
                COLUMN_PRODUCTNAME + " = ?", new String[]{productName}, null, null, null);
        cursor.moveToFirst();

        fieldProductName = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME));
        fieldProductPrice = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));
        fieldProductStock = cursor.getInt(cursor.getColumnIndex(COLUMN_ONHAND));
        fieldSupplier = cursor.getString(cursor.getColumnIndex(COLUMN_SUPPLIER));
        fieldContactInformation = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT));
        fieldImage = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));

        Product result = new Product(fieldProductName, fieldProductStock, fieldProductPrice,
                fieldSupplier, fieldContactInformation, getBitmap(fieldImage));

        return result;

    }

    public void deleteSingleEntry (String productName) {
        SQLiteDatabase entry = getReadableDatabase();
        entry.delete(TABLE_NAME, COLUMN_PRODUCTNAME + " =?", new String[] {productName});
    }

    // Reads database, stores in Cursor object
    public ArrayList<Product> readProduct() {
        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        String query = "SELECT * FROM " + TABLE_NAME; //Select every column, select every row.

        Cursor cursor = sqLiteDatabase.rawQuery(query, null); //Cursor point to a location in results.
        cursor.moveToFirst(); //Move to the first row in results.
        ArrayList<Product> frontPage = new ArrayList<>();

        while (!cursor.isAfterLast()) {
            String nameStorage;
            int onHand;
            int productPrice;
            String supplier;
            String contact;
            byte[] image;

            productPrice = cursor.getInt(cursor.getColumnIndex(COLUMN_PRICE));
            onHand = cursor.getInt(cursor.getColumnIndex(COLUMN_ONHAND));
            nameStorage = cursor.getString(cursor.getColumnIndex(COLUMN_PRODUCTNAME));
            supplier = cursor.getString(cursor.getColumnIndex(COLUMN_SUPPLIER));
            contact = cursor.getString(cursor.getColumnIndex(COLUMN_CONTACT));
            image = cursor.getBlob(cursor.getColumnIndex(COLUMN_IMAGE));

            Product product = new Product(nameStorage, onHand, productPrice, supplier,
                    contact, getBitmap(image));
            frontPage.add(product);

            cursor.moveToNext();
        }
        cursor.close();
        return frontPage;
    }
}