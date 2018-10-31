package com.warriorrat.reportcard;

/**
 * Created by Jake on 6/26/2016.
 */
public class ReportCard {

    private int mA;
    private int mB;
    private int mC;
    private int mD;
    private int mF;
    private String mComment;

    public ReportCard() {
        mA = Integer.parseInt("A");
        mB = Integer.parseInt("B");
        mC = Integer.parseInt("C");
        mD = Integer.parseInt("D");
        mF = Integer.parseInt("F");
        mComment = "";

    }

    public int getGradeA() {
        return mA;
    }

    public int getGradeB() {
        return mB;
    }

    public int getGradeC() {
        return mC;
    }

    public int getGradeD() {
        return mD;
    }

    public int getGradeF() {
        return mF;
    }

    public void setComment(String mComment) {
        this.mComment = mComment;
    }

    @Override
    public String toString() {
        return "ReportCard{" +
                "mA=" + mA +
                ", mB=" + mB +
                ", mC=" + mC +
                ", mD=" + mD +
                ", mF=" + mF +
                ", mComment='" + mComment + '\'' +
                '}';
    }
}
