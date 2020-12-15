package com.mobile.internet.getinformation.getcourse.jsonobjeck.getcourseprivatbank;

public class Course {
    private double courseVal;
    private String currency;

    public Course() {
    }

    public Course(double courseVal, String currency) {
        this.courseVal = courseVal;
        this.currency = currency;
    }

    public double getCourseVal() {
        return courseVal;
    }

    public void setCourseVal(double courseVal) {
        this.courseVal = courseVal;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
