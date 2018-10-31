package com.warriorrat.firearmquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class Final extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_final);

        displayScore();
        displayMaxScore();
    }

    //A method that calls the score variable from the Main Activity class and displays it
    //to the user. Checks to ensure the score cannot go over the maximum score
    public void displayScore() {
        if (MainActivity.score > MainActivity.maxScore) {
            MainActivity.score = MainActivity.maxScore;
        }
        TextView scoreTest = (TextView) findViewById(R.id.scoreNumber);
        scoreTest.setText(String.valueOf(MainActivity.score));
    }

    public void displayMaxScore() {
        TextView scoreMaxTest = (TextView) findViewById(R.id.possibleScore);
        scoreMaxTest.setText(String.valueOf(MainActivity.maxScore));
    }

    //Sends user back to the start screen
    public void goToMainActivity(View view) {
        Intent Main = new Intent(this, MainActivity.class);
        startActivity(Main);
        finish();
    }
}
