package com.warriorrat.firearmquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class QuestionTwo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_two);
    }

    //This method lets the app know what to do when one of the Radio buttons is checked
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.ar15RadButton:
                if (checked)
                break;
            case R.id.RadGlock:
                if (checked)
                    MainActivity.score++;
                break;
            case R.id.Rad1911:
                if (checked)
                break;
            case R.id.RadDesert:
                if (checked)
                break;
        }
    }

    //Sends the user to the Third Question
    public void goToQuestionThree(View view) {
        Intent questionThree = new Intent(this, QuestionThree.class);
        startActivity(questionThree);
        finish();
    }
}
