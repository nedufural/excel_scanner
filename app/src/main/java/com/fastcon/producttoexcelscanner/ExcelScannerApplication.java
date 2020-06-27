package com.fastcon.producttoexcelscanner;

import android.app.Application;

import com.facebook.FacebookSdk;
import com.orhanobut.hawk.Hawk;

import timber.log.Timber;

public class ExcelScannerApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        Hawk.init(this).build();
        FacebookSdk.sdkInitialize(getApplicationContext());
        Timber.plant(new Timber.DebugTree());
    }
}
