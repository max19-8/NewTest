package com.example.newtest.di;

import com.example.newtest.data.repository.WeatherRepository;
import com.example.newtest.data.repository.WeatherRepositoryImpl;

import javax.inject.Singleton;

import dagger.Binds;
import dagger.Module;

@Module
public interface DataModule {
    @Binds
    @Singleton
    WeatherRepository provideWeatherRepository(WeatherRepositoryImpl impl);
}
