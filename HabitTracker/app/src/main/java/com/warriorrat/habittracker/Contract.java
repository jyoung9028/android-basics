package com.warriorrat.habittracker;

/**
 * Created by Jake on 7/1/2016.
 */
public class Contract {

    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "habits.db";

    public static class HabitTable {
        public static final String TABLE_NAME = "habits";
        public static final String COLUMN_ID = "mId";
        public static final String COLUMN_HABITNAME = "mHabitName";
    }
}
