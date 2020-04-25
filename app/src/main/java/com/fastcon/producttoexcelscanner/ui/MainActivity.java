package com.fastcon.producttoexcelscanner.ui;

import android.Manifest;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.facebook.FacebookSdk;
import com.fastcon.producttoexcelscanner.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import dmax.dialog.SpotsDialog;

public class MainActivity extends AppCompatActivity {
    MainActivityViewModel mainActivityViewModel;
    MainActivityContract mainActivityContract;
    AlertDialog dialog;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mainActivityContract = new MainActivityContract();
        dialog = new SpotsDialog.Builder().setContext(this).build();
        ProgressBar progressBar = findViewById(R.id.progress_bar);
        TextView textView = findViewById(R.id.text);
        Boolean isConnected = mainActivityContract.isNetworkAvailable(this);
        if(isConnected) {
            progressBar.setVisibility(View.VISIBLE);
            textView.setVisibility(View.VISIBLE);
            Dexter.withContext(this)
                    .withPermission(Manifest.permission.CAMERA)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {

                            mainActivityContract.startScan(MainActivity.this);
                        }

                        @Override
                        public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                        }

                        @Override
                        public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {

                        }
                    })
                    .check();

            FacebookSdk.sdkInitialize(getApplicationContext());

            mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);


            dialog.show();

        }
        else{
            Toast.makeText(this, getString(R.string.check_connected), Toast.LENGTH_LONG).show();
            textView.setVisibility(View.VISIBLE);
            textView.setText(getString(R.string.check_connected));
        }

    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        dialog.show();
        if (result != null) {
            if (result.getContents() != null) {
                mainActivityViewModel.enterBarcode(result.getContents());
                mainActivityViewModel._inserted.observe(this, x -> {
                            Toast.makeText(this, "Successfully added "+x.getBarcode(), Toast.LENGTH_LONG).show();
                            mainActivityContract.startScan(MainActivity.this);
                        }
                );
                dialog.dismiss();
            }
        } else {

            mainActivityContract.startScan(MainActivity.this);
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mainActivityContract.startScan(MainActivity.this);
    }
}