package com.example.newtest.presentation.view;

import com.arellomobile.mvp.MvpView;
import com.example.newtest.data.api.model.RootWeather;

public interface MainView extends MvpView {
    void onLocationChanged(RootWeather root);
    void error(String error);
    void loading();
    void unloading();
    void requestLocationPermission();
}
