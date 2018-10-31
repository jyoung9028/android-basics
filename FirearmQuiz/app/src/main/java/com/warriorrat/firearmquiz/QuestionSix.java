package com.warriorrat.firearmquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;

public class QuestionSix extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_six);
    }

    //This method checks to see if the text input is correct
    public void submitAnswer(View view) {
        EditText answer = (EditText) findViewById(R.id.answer_field);

        if (answer.getText().toString().equals("Shall not be infringed")) {
            MainActivity.score++;
        } else if (answer.getText().toString().equals("shall not be infringed")) {
            MainActivity.score++;
        }
        }

    //Sends the user to the Final screen so they can see their score
    public void goToFinal(View view) {
        Intent Final = new Intent(this, Final.class);
        startActivity(Final);
        finish();
    }
}
