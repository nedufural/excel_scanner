package com.fastcon.producttoexcelscanner.network;


import static com.facebook.FacebookSdk.getApplicationContext;

public class DataManager {

        public static ApiService getApiService() {
            ApiClient apiClient = new ApiClient();
            return apiClient.getClient(getApplicationContext())
                    .create(ApiService.class);
        }
}