package com.example.newtest.di;

import android.content.Context;

import com.example.newtest.presentation.view.MainActivity;
import com.example.newtest.data.repository.WeatherRepository;
import com.example.newtest.presentation.view.MainPresenter;
import com.example.newtest.presentation.view.MainPresenterImpl;
import com.example.newtest.presentation.location.GpsUtils;
import com.example.newtest.presentation.location.GpsUtilsImpl;
import com.example.newtest.presentation.location.LocationGps;
import com.example.newtest.presentation.location.LocationGpsImpl;
import com.example.newtest.presentation.permission.PermissionsHandler;
import com.example.newtest.presentation.permission.PermissionsHandlerAndroid;
import com.google.android.gms.location.FusedLocationProviderClient;
import dagger.Module;
import dagger.Provides;

@Module
public  class PresenterModule {
    @Provides
    @ActivityScope
    PermissionsHandler providePermissionsHandler(Context context, LocationGps locationGps, GpsUtils gpsUtils){
        return new PermissionsHandlerAndroid(context,locationGps,gpsUtils);
    }
    @Provides
    @ActivityScope
    public GpsUtils gpsUtils(MainActivity activity){
        return new GpsUtilsImpl(activity);
    }

    @Provides
    @ActivityScope
    LocationGps provideLocationGps(GpsUtils gpsUtils, FusedLocationProviderClient fusedLocationProviderClient){
        return new LocationGpsImpl(gpsUtils,fusedLocationProviderClient);
    }
    @Provides
    @ActivityScope
    MainPresenter provideMainPresenter(WeatherRepository repository, LocationGps locationGps){
        return  new MainPresenterImpl(repository, locationGps);
    }
}