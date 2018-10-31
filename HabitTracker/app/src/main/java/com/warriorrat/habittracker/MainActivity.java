package com.warriorrat.habittracker;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // TODO: define and setup database schema (table and columns)
    // TODO: create 4 methods that insert, read, update, delete from database

    EditText userInput;
    EditText columnIdUpdate;
    TextView userText;
    HabitDBHelper workingClass;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        userInput = (EditText) findViewById(R.id.userInput);
        columnIdUpdate = (EditText) findViewById(R.id.updateInput);
        userText = (TextView) findViewById(R.id.userText);
        workingClass = new HabitDBHelper(this);

        printHabits();
    }

    // Add habit to button click
    public void addButtonClicked(View view) {
        Habit product = new Habit(userInput.getText().toString());
        workingClass.addProduct(product);
        printHabits();
    }

    // Drop the table (Delete all entries)
    public void deleteButtonClicked(View view) {
        workingClass.deleteAllEntries();
        printHabits();
    }

    // Retrieves string and places it on screen
    public void printHabits() {
        String dbString = workingClass.databaseToString();
        userText.setText(dbString);
        userInput.setText("");
    }

    // Deletes the database
    public void deleteDB(View view) {
        workingClass.deleteDatabase();
        printHabits();
    }

    public void readSingleCursor(View view) {
        int read;
        try {
            read = Integer.parseInt(columnIdUpdate.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Id should be an integer", Toast.LENGTH_SHORT).show();
            return;
        }
        Cursor result = workingClass.readSingleEntry(read);
        if (result.moveToFirst() == false) {
            Toast.makeText(MainActivity.this, "No records with given ID", Toast.LENGTH_SHORT).show();
            return;
        }

        String columnName;
        int columnId;

        columnName = result.getString(result.getColumnIndex(Contract.HabitTable.COLUMN_HABITNAME));
        columnId = result.getInt(result.getColumnIndex(Contract.HabitTable.COLUMN_ID));

        result.close();

        userText.setText(columnName + " " + columnId);
    }

    public void updateButtonClick(View v) {
        int read;
        String habitName = userInput.getText().toString();

        if (habitName.trim().equals("")) {
            Toast.makeText(MainActivity.this, "Name is empty", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            read = Integer.parseInt(userInput.getText().toString());
        } catch (NumberFormatException e) {
            Toast.makeText(MainActivity.this, "Id should be an integer", Toast.LENGTH_SHORT).show();
            return;
        }
        workingClass.updateEntry(read, habitName);
        printHabits();
    }
}
