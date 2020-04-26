package com.fastcon.producttoexcelscanner.base;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.Nullable;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.FacebookSdk;
import com.fastcon.producttoexcelscanner.R;

import dmax.dialog.SpotsDialog;

public abstract class BaseActivity extends AppCompatActivity {

    ProgressBar progressBar;
    AlertDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());
        FacebookSdk.sdkInitialize(getApplicationContext());
        dialog = new SpotsDialog.Builder().setContext(this).build();
        progressBar = findViewById(R.id.progress_bar);
        initData();
        initEvents();
    }

    protected abstract int getLayoutID();

    public void toastMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
        dialog.show();
    }

    public void hideProgress() {
        progressBar.setVisibility(View.INVISIBLE);
        dialog.hide();
    }

    protected abstract void initData();

    protected abstract void initEvents();


}
