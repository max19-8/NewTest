package com.example.newtest.presentation.location;

public interface LocationGps {
     void getCurrentLocation();
     void getDefaultLocation();

     void setLocListenerInterface(LocListenerInterface locListenerInterface);
}
