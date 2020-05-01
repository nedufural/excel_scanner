package com.fastcon.producttoexcelscanner.network;

import com.fastcon.producttoexcelscanner.data.entity.InsertDataResponse;
import com.fastcon.producttoexcelscanner.data.entity.RetrieveDataResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("AKfycbw6u9GYMPku76o5lyzEScWhhlqjYky9YEzhchZ2aEjbHsG3GhY/exec?action=addCode")
    Call<InsertDataResponse> enterBarcode(@Field("barcodes") String barcode, @Field("address") String link, @Field("name") String sheetName);

    @GET("AKfycbw6u9GYMPku76o5lyzEScWhhlqjYky9YEzhchZ2aEjbHsG3GhY/exec?action=getItems")
    Call<RetrieveDataResponse> getExcelData();

}
