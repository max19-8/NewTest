package com.example.newtest.data.api.model;

public class Fact {
    public Fact(int temp, int feels_like) {
        this.temp = temp;
        this.feels_like = feels_like;
    }
    private final int temp;
    public int getTemp() {
        return this.temp;
    }
    private final int feels_like;
    public int getFeelsLike() {
        return this.feels_like;
    }
}
