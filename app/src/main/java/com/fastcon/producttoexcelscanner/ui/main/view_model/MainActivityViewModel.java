package com.fastcon.producttoexcelscanner.ui.main.view_model;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.fastcon.producttoexcelscanner.commons.PrefsUtils;
import com.fastcon.producttoexcelscanner.data.entity.remote.InsertDataResponse;
import com.fastcon.producttoexcelscanner.data.entity.remote.RetrieveDataResponse;
import com.fastcon.producttoexcelscanner.network.remote.DataManager;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.fastcon.producttoexcelscanner.commons.PrefsUtils.setJson;
import static com.fastcon.producttoexcelscanner.commons.Utils.TAG_LIST;

public class MainActivityViewModel extends ViewModel implements MainActivityViewModelContract {

    private final MutableLiveData<InsertDataResponse> inserted = new MutableLiveData<InsertDataResponse>();
    public final MutableLiveData<InsertDataResponse> _inserted = inserted;

    private final MutableLiveData<ArrayList<RetrieveDataResponse.Item>> retrieve = new MutableLiveData<ArrayList<RetrieveDataResponse.Item>>();
    public final MutableLiveData<ArrayList<RetrieveDataResponse.Item>> _retrieve = retrieve;

    ArrayList<RetrieveDataResponse.Item> data = new ArrayList<>();

    public void enterBarcode(String barcode) {
        Call<InsertDataResponse> call =
                DataManager.getApiService().enterBarcode(barcode, getExcelLinkUrl(), getExcelSheetName());
        call.enqueue(new Callback<InsertDataResponse>() {
            @Override
            public void onResponse(@NotNull Call<InsertDataResponse> call, @NotNull Response<InsertDataResponse> response) {
                if (Objects.requireNonNull(response.body()).toString().contains("success")) {
                    inserted.postValue(response.body());
                } else {
                    inserted.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NotNull Call<InsertDataResponse> call, @NotNull Throwable t) {
                Log.e("Error Inserting Data: ", t.toString());
            }
        });
    }

    @Override
    public String getExcelLinkUrl() {
        return PrefsUtils.getExcelLink();
    }

    @Override
    public String getExcelSheetName() {
        return PrefsUtils.getExcelLink();
    }

    @Override
    public void getProductList() {
        Call<RetrieveDataResponse> call =
                DataManager.getApiService().getExcelData();
        call.enqueue(new Callback<RetrieveDataResponse>() {
            @Override
            public void onResponse(@NotNull Call<RetrieveDataResponse> call, @NotNull Response<RetrieveDataResponse> response) {

                assert response.body() != null;
                if (!response.body().toString().isEmpty()) {
                    data.addAll(response.body().getItems());
                    for (int i = 0; i < response.body().getItems().size(); i++) {
                        try {
                            setJson(response.body().getItems());
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }

                    retrieve.postValue(data);

                } else {
                    Log.i(TAG_LIST, "empty response");
                }
            }

            @Override
            public void onFailure(@NotNull Call<RetrieveDataResponse> call, @NotNull Throwable t) {
                System.out.println("Error" + t.getMessage());
                t.getMessage();
            }
        });
    }
}
