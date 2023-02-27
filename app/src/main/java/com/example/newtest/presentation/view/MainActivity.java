package com.example.newtest.presentation.view;

import android.Manifest;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.example.newtest.App;
import com.example.newtest.R;
import com.example.newtest.data.api.model.RootWeather;
import com.example.newtest.di.MainActivityComponent;
import com.example.newtest.presentation.adapter.ForecastAdapter;
import com.example.newtest.presentation.adapter.ForecastDiffCallback;
import com.example.newtest.presentation.permission.PermissionsHandler;
import com.example.newtest.presentation.permission.RequestPermissionInterface;

import java.text.SimpleDateFormat;
import java.util.Locale;

import javax.inject.Inject;
public class MainActivity extends MvpAppCompatActivity implements  MainView , RequestPermissionInterface {
    View  factTemperatureItem;
    TextView tvCity,tvTemperature ,tvFeelsLikeTemperature ,tvDate,forecastInCity;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    @Inject
    PermissionsHandler permissionsHandler;
    @Inject
    @InjectPresenter
    MainPresenterImpl mainPresenter;
    @ProvidePresenter
    MainPresenterImpl providePresenter(){
        return  mainPresenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
      MainActivityComponent component = ((App)getApplication()).getAppComponent().activityComponentFactory().create(this);
        component.inject(this);
        permissionsHandler.setRequestPermissionInterface(this);
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
      mainPresenter.request();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
       mainPresenter.onRequestPermissionsResult(requestCode,grantResults);
   }
    @Override
    public void onLocationChanged(RootWeather root) {
        String date = new SimpleDateFormat("dd-MM-yyyy", Locale.ROOT).format(root.getNow_dt());
        forecastInCity.setText(getString(R.string.forecast_for_the_week,root.getGeoObject().getLocality().getName()));
        tvCity.setText(root.getGeoObject().getLocality().getName());
       tvDate.setText(date);
        tvTemperature.setText(getString(R.string.temp,String.valueOf(root.getFact().getTemp())));
        tvFeelsLikeTemperature.setText(getString(R.string.temp_feels_like,String.valueOf(root.getFact().getFeelsLike())));
        ForecastAdapter forecastAdapter = new ForecastAdapter(new ForecastDiffCallback());
        recyclerView.setAdapter(forecastAdapter);
        forecastAdapter.submitList(root.getForecasts());
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        mainPresenter.onActivityResult(requestCode,resultCode);
    }
    @Override
    public void error(String error) {
        forecastInCity.setVisibility(View.VISIBLE);
        forecastInCity.setText(error);
    }
    @Override
    public void loading() {
        createProgress();
    }

    @Override
    public void unloading() {
        progressDialog.dismiss();
        factTemperatureItem.setVisibility(View.VISIBLE);
        forecastInCity.setVisibility(View.VISIBLE);
        recyclerView.setVisibility(View.VISIBLE);
    }

    @Override
    public void requestLocationPermission() {
        mainPresenter.requestPermission(new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
    }
    private void createProgress(){
        progressDialog = new ProgressDialog(this);
        progressDialog.show();
        progressDialog.setContentView(R.layout.progress_dialog);
        progressDialog.getWindow().setBackgroundDrawableResource(android.R.color.transparent);
    }
    @Override
    public void requestPermission(String[] permissions, int requestCode) {
        ActivityCompat.requestPermissions(this, permissions, requestCode);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mainPresenter.clearDispose();
    }
    private void initView() {
        tvCity = findViewById(R.id.tv_location_city);
        tvTemperature = findViewById(R.id.tv_temperature);
        tvFeelsLikeTemperature = findViewById(R.id.tv_feels_like_temperature);
        tvDate = findViewById(R.id.tv_date);
        forecastInCity = findViewById(R.id.forecast_in_city);
        factTemperatureItem = findViewById(R.id.fact_temperature_item);
        recyclerView = findViewById(R.id.recycler_view_forecast);
        }
}