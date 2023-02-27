package com.example.newtest.data.repository;

import com.example.newtest.data.api.model.RootWeather;

import io.reactivex.Single;

public interface WeatherRepository {
     Single<RootWeather> getWeather(String lat, String lon);
}
