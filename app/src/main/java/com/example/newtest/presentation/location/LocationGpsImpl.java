package com.example.newtest.presentation.location;

import static com.google.android.gms.location.Priority.PRIORITY_HIGH_ACCURACY;

import android.annotation.SuppressLint;
import android.location.Location;
import android.util.Log;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.tasks.Task;


public class LocationGpsImpl  implements LocationGps {
    private LocListenerInterface locListenerInterface;
    private final GpsUtils gpsUtils;
    public void setLocListenerInterface(LocListenerInterface locListenerInterface) {
        this.locListenerInterface = locListenerInterface;
    }
    private final FusedLocationProviderClient fusedLocationClient;
    public LocationGpsImpl(GpsUtils gpsUtils, FusedLocationProviderClient fusedLocationClient) {
        this.gpsUtils = gpsUtils;
        this.fusedLocationClient = fusedLocationClient;

    }
    @SuppressLint("MissingPermission")
    @Override
    public void getCurrentLocation() {
            Task<Location> locationTask =  fusedLocationClient.getCurrentLocation(PRIORITY_HIGH_ACCURACY,null);
            if (!gpsUtils.isGPSEnabled()) {
                getDefaultLocation();
                Log.d("onLocationChanged", "getDefaultLocation");
            }
            locationTask.addOnSuccessListener( location -> {
                Log.d("onLocationChanged","addOnSuccessListener");
                if (location != null){
                    Log.d("onLocationChanged", String.valueOf(location));
                    locListenerInterface.onLocationChanged(location.getLatitude(),location.getLongitude());
                    Log.d("onLocationChanged", "onLocationChanged");
                }
            });
            locationTask.addOnFailureListener(exception -> Log.d("onLocationChange", String.valueOf(exception.getLocalizedMessage()))
            );
    }
    @Override
    public void getDefaultLocation(){
        locListenerInterface.onLocationChanged(	55.7522,37.6156);
    }
}