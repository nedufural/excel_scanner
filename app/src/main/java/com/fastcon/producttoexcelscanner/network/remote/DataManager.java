package com.fastcon.producttoexcelscanner.network.remote;


import static com.facebook.FacebookSdk.getApplicationContext;

public class DataManager {

        public static ApiService getApiService() {
            ApiClient apiClient = new ApiClient();
            return apiClient.getClient(getApplicationContext())
                    .create(ApiService.class);
        }
}