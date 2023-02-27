package com.example.newtest.data.api.model;

public class Evening{
    public Evening(int temp_avg, int feels_like) {
        this.temp_avg = temp_avg;
        this.feels_like = feels_like;
    }
    private final int temp_avg;
    public int getTempAvg() {
        return this.temp_avg;
    }
    private final int feels_like;
    public int getTempFeelsLike() {
        return this.feels_like;
    }
}