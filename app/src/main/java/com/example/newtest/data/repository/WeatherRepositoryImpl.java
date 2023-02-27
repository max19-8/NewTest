package com.example.newtest.data.repository;

import com.example.newtest.data.api.ApiService;
import com.example.newtest.data.api.model.RootWeather;

import javax.inject.Inject;

import io.reactivex.Single;

public class WeatherRepositoryImpl  implements  WeatherRepository{
    private final ApiService apiService;
    @Inject
    public WeatherRepositoryImpl(ApiService apiService) {
        this.apiService = apiService;
    }

    public Single<RootWeather> getWeather(String lat, String lon){
      return   apiService.loadRepo(lat, lon);
    }
}
