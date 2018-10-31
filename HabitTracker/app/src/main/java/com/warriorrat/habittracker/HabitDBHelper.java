package com.warriorrat.habittracker;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.widget.Toast;

/**
 * Created by Jake on 6/30/2016.
 */
public class HabitDBHelper extends SQLiteOpenHelper {

    Context context;

    //Constructor.
    public HabitDBHelper(Context context) {
        super(context, Contract.DATABASE_NAME, null, Contract.DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String query = "CREATE TABLE " + Contract.HabitTable.TABLE_NAME + "(" +
                Contract.HabitTable.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT," +
                Contract.HabitTable.COLUMN_HABITNAME + " TEXT" +
                ");";
        sqLiteDatabase.execSQL(query);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        String query = "DROP TABLE IF EXISTS " + Contract.HabitTable.TABLE_NAME;
        sqLiteDatabase.execSQL(query);
        onCreate(sqLiteDatabase);
    }

    //Add a new row to the database.
    public void addProduct(Habit product) {
        ContentValues values = new ContentValues();
        values.put(Contract.HabitTable.COLUMN_HABITNAME, product.getHabitName()); //Where, what. (Not writing to database yet.)
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        sqLiteDatabase.insert(Contract.HabitTable.TABLE_NAME, null, values); //Writing to database now.
        sqLiteDatabase.close();
    }

    // Delete entire database, return true if done successfully

    public boolean deleteDatabase() {
        return context.deleteDatabase(Contract.DATABASE_NAME);
    }

    // Drop the table
    public void deleteAllEntries() {
        SQLiteDatabase byeFelicia = getWritableDatabase();
        String query = "DROP TABLE IF EXISTS " + Contract.HabitTable.TABLE_NAME;
        byeFelicia.execSQL(query);
        onCreate(byeFelicia);
    }

    public Cursor readSingleEntry(int id) {

        SQLiteDatabase sqLiteDatabase = getReadableDatabase();
        Cursor result = sqLiteDatabase.query(Contract.HabitTable.TABLE_NAME, null,
                Contract.HabitTable.COLUMN_ID + "=" + id, null, null, null, null);
        return result;
    }

    public void updateEntry(int id, String newName) {
        ContentValues dataToInsert = new ContentValues();
        dataToInsert.put(Contract.HabitTable.COLUMN_HABITNAME, newName);
        String where = Contract.HabitTable.COLUMN_ID + "=" + id;
        try {
            SQLiteDatabase db = getWritableDatabase();
            db.update(Contract.HabitTable.TABLE_NAME, dataToInsert, where, null);
        } catch (Exception e) {
            Toast.makeText(context, "No entry with given ID", Toast.LENGTH_SHORT).show();
        }
    }

    //Print out the database as a string.
    public String databaseToString() {
        String dbString = "";
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String query = "SELECT * FROM " + Contract.HabitTable.TABLE_NAME + " WHERE 1"; //Select every column, select every row.

        Cursor cursor = sqLiteDatabase.rawQuery(query, null); //Cursor point to a location in results.
        cursor.moveToFirst(); //Move to the first row in results.
        while (!cursor.isAfterLast()) {
            if (cursor.getString(cursor.getColumnIndex("mHabitName")) != null) {
                dbString += cursor.getString(cursor.getColumnIndex("mHabitName"));
                dbString += "\n";
            }
            cursor.moveToNext();
        }
        cursor.close();

        sqLiteDatabase.close();
        return dbString;
    }

}