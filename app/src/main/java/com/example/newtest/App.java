package com.example.newtest;

import android.app.Application;
import android.content.Context;
import android.location.LocationManager;

import com.example.newtest.di.AppComponent;
import com.example.newtest.di.DaggerAppComponent;
import com.google.android.gms.location.LocationServices;

public class App extends Application {
    private AppComponent appComponent;
    @Override
    public void onCreate() {
        super.onCreate();
             appComponent = DaggerAppComponent.factory().create(
                this,
                LocationServices.getFusedLocationProviderClient(this),
                (LocationManager) getSystemService(Context.LOCATION_SERVICE));

    }
    public AppComponent getAppComponent() {
        return appComponent;
    }
}