package com.fastcon.producttoexcelscanner.ui;

import android.Manifest;
import android.content.Intent;
import android.view.View;
import android.widget.TextView;

import androidx.lifecycle.ViewModelProviders;

import com.fastcon.producttoexcelscanner.R;
import com.fastcon.producttoexcelscanner.base.BaseActivity;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MainActivity extends BaseActivity {

    MainActivityViewModel mainActivityViewModel;
    Boolean isConnected;
    TextView textView;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        textView = findViewById(R.id.text);
        isConnected = MainActivityContract.getInstance().isNetworkAvailable(this);
    }

    @Override
    protected void initEvents() {
        if (isConnected) {

            textView.setVisibility(View.VISIBLE);
            Dexter.withContext(this)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                            MainActivityContract.getInstance().startScan(MainActivity.this);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        }
                    })
                    .check();


            mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


        } else {
            toastMessage(getString(R.string.check_connected));
            textView.setVisibility(View.VISIBLE);
            textView.setText(getString(R.string.check_connected));
        }
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        showProgress();
        if (result != null) {
            if (result.getContents() != null) {
                mainActivityViewModel.enterBarcode(result.getContents());
                mainActivityViewModel._inserted.observe(this, x -> {
                            toastMessage("Successfully added ");
                            MainActivityContract.getInstance().startScan(MainActivity.this);
                        }
                );
                hideProgress();
            }
        } else {

            MainActivityContract.getInstance().startScan(MainActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MainActivityContract.getInstance().startScan(MainActivity.this);
    }

    @Override
    public void onPointerCaptureChanged(boolean hasCapture) {

    }
}