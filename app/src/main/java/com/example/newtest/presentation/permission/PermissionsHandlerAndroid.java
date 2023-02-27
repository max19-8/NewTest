package com.example.newtest.presentation.permission;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.core.app.ActivityCompat;

import com.example.newtest.presentation.location.GpsUtils;
import com.example.newtest.presentation.location.LocationGps;

public class PermissionsHandlerAndroid implements PermissionsHandler {
    Context context;
    LocationGps locationGps;
    GpsUtils gpsUtils;

    public PermissionsHandlerAndroid(Context context, LocationGps locationGps, GpsUtils gpsUtils) {
        this.context = context;
        this.locationGps = locationGps;
        this.gpsUtils = gpsUtils;
    }
    private RequestPermissionInterface requestPermissionInterface;

    @Override
    public void setRequestPermissionInterface(RequestPermissionInterface requestPermissionInterface) {
        this.requestPermissionInterface = requestPermissionInterface;
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull int[] grantResults){
        if (requestCode == 111 && grantResults.length > 0) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                requestPermission( new String[]{android.Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION}, 111);
                Log.d("onLocationChanged", "onRequestPermissionsResult");
            } else {
                locationGps.getDefaultLocation();
                Log.d("onLocationChanged", "getDefaultLocationelseelseelse");
            }
        }
    }
    @Override
    public void requestPermission(String[] permissions, int requestCode) {
        if (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                (ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED)) {
            requestPermissionInterface.requestPermission(permissions, requestCode);
            Log.d("onLocationChanged", "ActivityCompat.checkSelfPermission");
        } else if (gpsUtils.isGPSEnabled()) {
            locationGps.getCurrentLocation();
            Log.d("onLocationChanged", "else if(gpsUtils.isGPSEnabled()");
        } else {
            Log.d("onLocationChanged", "getDefaultLocation2222");
            gpsUtils.turnGPSOn(() -> locationGps.getCurrentLocation());
        }
    }
    @Override
    public void activityResult(int requestCode, int resultCode) {
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 200) {
                locationGps.getCurrentLocation();
                Log.d("onLocationChanged", "requestCode == 200");
            }
        }else {
            locationGps.getDefaultLocation();
            Log.d("onLocationChanged", "else200");
        }
    }
}