package com.fastcon.producttoexcelscanner.ui.main;

import android.app.Activity;
import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

import com.fastcon.producttoexcelscanner.R;
import com.fastcon.producttoexcelscanner.data.usecases.UseCaseMainActivity;
import com.google.zxing.integration.android.IntentIntegrator;

public class MainActivityContract implements UseCaseMainActivity {

    private static MainActivityContract mainActivityContractInstance = null;

    public static MainActivityContract getInstance() {
        if (mainActivityContractInstance == null) {
            mainActivityContractInstance = new MainActivityContract();
        }
        return mainActivityContractInstance;
    }

    @Override
    public void startScan(Activity activity) {
        IntentIntegrator integrator = new IntentIntegrator(activity);
        integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
        integrator.setPrompt(activity.getString(R.string.scan_prompt));
        //integrator.setCameraId(0);  // Use a specific camera of the device
        integrator.setBeepEnabled(true);
        integrator.setOrientationLocked(true);
        integrator.setPrompt(activity.getString(R.string.capture_qrcode_prompt));
        integrator.initiateScan();
    }


    @Override
    public Boolean isNetworkAvailable(Activity activity) {
        ConnectivityManager connectivityManager = (ConnectivityManager) activity
                .getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetworkInfo = null;
        if (connectivityManager != null) {
            activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        }
        return activeNetworkInfo != null && activeNetworkInfo.isConnected();
    }

    interface MainActivityContractSub {

        void checkPermissions();

    }
}
