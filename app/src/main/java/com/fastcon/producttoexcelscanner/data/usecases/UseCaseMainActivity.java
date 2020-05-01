package com.fastcon.producttoexcelscanner.data.usecases;

import android.app.Activity;

public interface UseCaseMainActivity {
    Boolean isNetworkAvailable(Activity activity);
    void startScan(Activity activity);

}
