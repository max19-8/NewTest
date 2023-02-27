package com.example.newtest.data.api.model;

import java.util.Objects;

public class Forecast {
    private final String date;

    public Forecast(String date, Parts parts, double feels_like) {
        this.date = date;
        this.parts = parts;
        this.feels_like = feels_like;
    }

    public String getDate() {
        return this.date;
    }

   private final Parts parts;
    public Parts getParts() {
        return this.parts;
    }

    private final double feels_like;
    public double getFeelsLike() {
        return this.feels_like;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Forecast forecast = (Forecast) o;
        return Double.compare(forecast.feels_like, feels_like) == 0 && Objects.equals(date, forecast.date) && Objects.equals(parts, forecast.parts);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, parts, feels_like);
    }
}
