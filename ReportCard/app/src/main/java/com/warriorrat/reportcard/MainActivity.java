package com.warriorrat.reportcard;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    int score = 100;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        grade(score);
        mathClass.setComment("good kid");
    }

    ReportCard mathClass = new ReportCard();

    public int grade (int score) {
        if (score >= 90) {
            return mathClass.getGradeA();
        } else if (score >= 80) {
            return mathClass.getGradeB();
        } else if (score >= 70) {
            return mathClass.getGradeC();
        } else if (score >= 60) {
            return mathClass.getGradeD();
        } else {
            return mathClass.getGradeF();
        }
    }

}
