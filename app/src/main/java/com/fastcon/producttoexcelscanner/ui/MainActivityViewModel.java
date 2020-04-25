package com.fastcon.producttoexcelscanner.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fastcon.producttoexcelscanner.network.DataManager;
import com.fastcon.producttoexcelscanner.data.InsertResponse;


import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivityViewModel extends ViewModel {

    final MutableLiveData<InsertResponse> inserted =new  MutableLiveData<InsertResponse>();
    final MutableLiveData<InsertResponse> _inserted = inserted;


    public  void enterBarcode(String barcode){
        Call<InsertResponse> call  =
        DataManager.getApiService().enterBarcode(barcode);
        call.enqueue(new Callback<InsertResponse>() {
            @Override
            public void onResponse(@NotNull Call<InsertResponse> call, @NotNull Response<InsertResponse> response) {
                if(response.body() != null){
                   inserted.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<InsertResponse> call, Throwable t) {
                System.out.println("Error"+t.getMessage());
                t.getMessage();
            }
        });
    }
}
