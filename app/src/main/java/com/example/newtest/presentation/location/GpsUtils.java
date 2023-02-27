package com.example.newtest.presentation.location;

public interface GpsUtils {
     void turnGPSOn(GpsUtilsImpl.onGpsListener onGpsListener);
     boolean isGPSEnabled();
}
