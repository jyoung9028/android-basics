package com.warriorrat.firearmquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.CheckBox;

public class QuestionFive extends AppCompatActivity {

    CheckBox ar15;
    CheckBox ak47;
    CheckBox boxR700;
    CheckBox boxUSP;
    CheckBox boxFS;
    CheckBox boxP226;
    CheckBox boxH1911;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_five);

        ar15 = (CheckBox) findViewById(R.id.boxAR);
        ak47 = (CheckBox) findViewById(R.id.boxAK);
        boxR700 = (CheckBox) findViewById(R.id.boxR700);
        boxUSP = (CheckBox) findViewById(R.id.boxUSP);
        boxFS = (CheckBox) findViewById(R.id.boxFS);
        boxP226 = (CheckBox) findViewById(R.id.boxP226);
        boxH1911 = (CheckBox) findViewById(R.id.boxH1911);
    }

    //This method lets the app know what to do when the check boxes are checked
    public void submitAnswer(View view) {

        if (ar15.isChecked() && ak47.isChecked() && boxR700.isChecked() && !boxUSP.isChecked()
                && !boxFS.isChecked() && !boxP226.isChecked() && !boxH1911.isChecked()) {
            MainActivity.score++;
        }
        }

    //Sends the user to the Sixth question
    public void goToQuestionSix(View view) {
        Intent questionSix = new Intent(this, QuestionSix.class);
        startActivity(questionSix);
        finish();
    }
}
