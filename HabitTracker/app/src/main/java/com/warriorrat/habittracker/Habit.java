package com.warriorrat.habittracker;

/**
 * Created by Jake on 6/30/2016.
 */
public class Habit {

    private int mId;
    private String mHabitName;


    public Habit(String habitName) {
        mHabitName = habitName;
    }

    public void setId(int id) {
        mId = id;
    }

    public void setHabitName(String habitName) {
        mHabitName = habitName;
    }

    public int getId() {
        return mId;
    }

    public String getHabitName() {
        return mHabitName;
    }
}
