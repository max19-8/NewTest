package com.example.newtest.data.api;

import com.example.newtest.data.api.model.RootWeather;

import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Query;

public interface ApiService {
    @Headers("X-Yandex-API-Key: 20a38ee0-202d-42a9-955e-8a59ad1d09cc")
    @GET("/v2/forecast?&limit=7&lang=ru_RU&extra=false")
    Single<RootWeather> loadRepo(@Query("lat") String lot, @Query("lon") String lon);
    }