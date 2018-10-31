package com.warriorrat.firearmquiz;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.RadioButton;

public class QuestionThree extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question_three);
    }

    //This method lets the app know what to do when one of the Radio buttons is checked
    public void onRadioButtonClicked(View view) {
        boolean checked = ((RadioButton) view).isChecked();

        switch (view.getId()) {
            case R.id.Rad92Fs:
                if (checked)
                break;
            case R.id.RadGlock:
                if (checked)
                break;
            case R.id.Rad1911:
                if (checked)
                    MainActivity.score++;
                break;
            case R.id.RadDesert:
                if (checked)
                break;
        }
    }

    //Sends the user to the Fourth question
    public void goToQuestionFour(View view) {
        Intent questionFour = new Intent(this, QuestionFour.class);
        startActivity(questionFour);
        finish();
    }
}
