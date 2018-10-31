package com.warriorrat.firearmquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * This App is designed to give a short quiz on some firearms
 */

public class MainActivity extends AppCompatActivity {

    //Variables used throughout the app to keep track of score and max possible score
    public static int score = 0;
    public static int maxScore = 6;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    //Sends the user to the first Question
    public void goToQuestionOne (View view) {
        Intent questionOne = new Intent(this, QuestionOne.class);
        startActivity(questionOne);
        finish();
    }
}
