package com.example.newtest.presentation.permission;

import androidx.annotation.NonNull;

public interface PermissionsHandler {
     void setRequestPermissionInterface(RequestPermissionInterface requestPermissionInterface);
     void onRequestPermissionsResult(int requestCode,@NonNull int[] grantResults);
     void requestPermission(String[] permissions, int requestCode);
     void activityResult(int requestCode, int resultCode);
}
