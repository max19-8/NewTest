package com.example.newtest.presentation.view;

import androidx.annotation.NonNull;

public interface MainPresenter {
     void request();

     void  onActivityResult(int requestCode, int resultCode);

     void   onRequestPermissionsResult( int requestCode,@NonNull int[] grantResults);

     void requestPermission( String[] permissions, int requestCode);

     void  clearDispose();
}
