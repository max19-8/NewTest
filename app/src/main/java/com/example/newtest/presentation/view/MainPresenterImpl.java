package com.example.newtest.presentation.view;

import androidx.annotation.NonNull;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.example.newtest.data.api.model.RootWeather;
import com.example.newtest.data.repository.WeatherRepository;
import com.example.newtest.presentation.location.LocListenerInterface;
import com.example.newtest.presentation.location.LocationGps;
import com.example.newtest.presentation.permission.PermissionsHandler;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class MainPresenterImpl extends MvpPresenter<MainView> implements MainPresenter, LocListenerInterface {
    private final WeatherRepository repository;
    MainView view;
    @Inject
    PermissionsHandler permissionsHandler;
    @Inject
    public MainPresenterImpl(WeatherRepository repository,
                             @NonNull LocationGps locationGps) {
        this.repository = repository;
        locationGps.setLocListenerInterface(this);
        view = getViewState();
    }
    @Override
    public void request() {
        view.loading();
        getViewState().requestLocationPermission();
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode) {
        permissionsHandler.activityResult(requestCode, resultCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults) {
        permissionsHandler.onRequestPermissionsResult(requestCode, grantResults);
    }
    private final CompositeDisposable disposable = new CompositeDisposable();

    @Override
    public void onLocationChanged(double lat, double lon) {
        disposable.add(repository.getWeather(String.valueOf(lat), String.valueOf(lon))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new DisposableSingleObserver<RootWeather>() {
                    @Override
                    public void onSuccess(RootWeather root) {
                        if (root != null) {
                            view.onLocationChanged(root);
                            view.unloading();
                        }
                    }
                    @Override
                    public void onError(Throwable e) {
                        view.unloading();
                        view.error(e.getLocalizedMessage());
                    }
                }));
    }
    @Override
    public void requestPermission(String[] permissions, int requestCode) {
        permissionsHandler.requestPermission(permissions, requestCode);
    }

    @Override
    public void clearDispose() {
        disposable.clear();
        disposable.dispose();
    }
}