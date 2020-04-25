package com.fastcon.producttoexcelscanner.network;

import com.fastcon.producttoexcelscanner.data.InsertResponse;

import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiService {
    @FormUrlEncoded
    @POST("AKfycbwXNe0cj6KIbOLuET7HDr9m98lWUTqUv07sKTrp1a3m1DeFLzQx/exec?action=addCode")
    Call<InsertResponse> enterBarcode(@Field("barcodes") String barcode);

}
