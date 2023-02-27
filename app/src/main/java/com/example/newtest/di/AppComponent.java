package com.example.newtest.di;

import android.content.Context;
import android.location.LocationManager;

import com.google.android.gms.location.FusedLocationProviderClient;

import javax.inject.Singleton;

import dagger.BindsInstance;
import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, DataModule.class})
public interface AppComponent {
    MainActivityComponent.Factory activityComponentFactory();

    @Component.Factory
    interface ApplicationComponentFactory {
        AppComponent create(@BindsInstance Context context,
                            @BindsInstance FusedLocationProviderClient provider,
                            @BindsInstance LocationManager locationManager);
    }
}