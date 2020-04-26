package com.fastcon.producttoexcelscanner.ui;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fastcon.producttoexcelscanner.network.DataManager;
import com.fastcon.producttoexcelscanner.data.InsertResponse;


import org.jetbrains.annotations.NotNull;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class MainActivityViewModel extends ViewModel {

    private static MainActivityViewModel mainActivityViewModelInstance = null;


    private final MutableLiveData<InsertResponse> inserted =new  MutableLiveData<InsertResponse>();
    final MutableLiveData<InsertResponse> _inserted = inserted;


    void enterBarcode(String barcode){
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
            public void onFailure(@NotNull Call<InsertResponse> call, @NotNull Throwable t) {
                System.out.println("Error"+t.getMessage());
                t.getMessage();
            }
        });
    }
}
