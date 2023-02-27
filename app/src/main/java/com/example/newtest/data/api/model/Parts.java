package com.example.newtest.data.api.model;

public class Parts {

    public Parts(Evening evening, Day day) {
        this.evening = evening;
        this.day = day;
    }
    private final Evening evening;
    public Evening getEvening() {
        return this.evening;
    }
    private final Day day;
    public Day getDay() {
        return this.day;
    }
}