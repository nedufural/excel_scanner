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
import com.orhanobut.hawk.Hawk;

import dmax.dialog.SpotsDialog;

public abstract class BaseActivity extends AppCompatActivity {


    AlertDialog dialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutID());

        FacebookSdk.sdkInitialize(getApplicationContext());

        dialog = new SpotsDialog.Builder().setContext(this).build();

        Hawk.init(this).build();
        initData();
        initEvents();

    }

    protected abstract int getLayoutID();

    public void toastMessage(String msg) {
        Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_LONG).show();
    }

    public void showProgress() {

        dialog.show();
    }

    public void hideProgress() {

        dialog.hide();
    }

    public void setDialogMessage(String message){
        dialog.setMessage(message);
    }

    protected abstract void initData();

    protected abstract void initEvents();

    protected abstract void initMiscEvents();




}
