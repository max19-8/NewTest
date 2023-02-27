package com.example.newtest.presentation.location;

import android.app.Activity;
import android.content.Context;
import android.content.IntentSender;
import android.location.LocationManager;
import android.util.Log;

import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.ResolvableApiException;
import com.google.android.gms.location.LocationRequest;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.location.LocationSettingsRequest;
import com.google.android.gms.location.LocationSettingsStatusCodes;
import com.google.android.gms.location.Priority;
import com.google.android.gms.location.SettingsClient;


public class GpsUtilsImpl implements GpsUtils {

    private final Context context;
    private final SettingsClient mSettingsClient;
    private final LocationSettingsRequest mLocationSettingsRequest;

    public GpsUtilsImpl(Context context) {
    this.context = context;
        mSettingsClient = LocationServices.getSettingsClient(context);
        LocationRequest.Builder builder = new LocationRequest.Builder(Priority.PRIORITY_HIGH_ACCURACY);
        builder.setWaitForAccurateLocation(false);
        LocationRequest  locationRequest = builder
                .build();
                LocationSettingsRequest.Builder builderSettings = new LocationSettingsRequest.Builder()
                .addLocationRequest(locationRequest);
        mLocationSettingsRequest = builderSettings.build();
        builderSettings.setAlwaysShow(true);
    }
    @Override
    public void turnGPSOn(onGpsListener onGpsListener) {
            mSettingsClient
                    .checkLocationSettings(mLocationSettingsRequest)
                    .addOnSuccessListener((Activity) context, locationSettingsResponse -> {
                        Log.d("onLocationChanged", "onSuccess");
                        onGpsListener.getLocation();
                    })
                    .addOnFailureListener((Activity) context, e -> {
                        int statusCode = ((ApiException) e).getStatusCode();
                        switch (statusCode) {
                            case LocationSettingsStatusCodes.RESOLUTION_REQUIRED:
                                try {
                                    Log.d("onLocationChanged", "onFailure");
                                    ResolvableApiException rae = (ResolvableApiException) e;
                                    rae.startResolutionForResult((Activity) context, 200);
                                } catch (IntentSender.SendIntentException sie) {
                                    Log.d("onLocationChanged", "PendingIntent unable to execute request.");
                                }
                                break;
                            case LocationSettingsStatusCodes.SETTINGS_CHANGE_UNAVAILABLE:
                                String errorMessage = "Location settings are inadequate, and cannot be fixed here. Fix in Settings.";
                                Log.d("onLocationChanged", errorMessage);
                        }
                    });
    }
    @Override
    public boolean isGPSEnabled() {
        boolean isEnabled = false;
        LocationManager   locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        isEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        return isEnabled;
    }
    public interface onGpsListener {
        void getLocation();
    }
}