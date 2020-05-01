package com.fastcon.producttoexcelscanner.ui.main;

import android.Manifest;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.fastcon.producttoexcelscanner.R;
import com.fastcon.producttoexcelscanner.base.BaseActivity;
import com.fastcon.producttoexcelscanner.base.DividerItemDecorator;
import com.fastcon.producttoexcelscanner.data.entity.JsonToExcel;
import com.fastcon.producttoexcelscanner.ui.main.adapter.MainActivityAdapter;
import com.fastcon.producttoexcelscanner.ui.main.view_model.MainActivityViewModel;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.MultiplePermissionsReport;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.multi.MultiplePermissionsListener;

import org.json.JSONException;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

import static com.fastcon.producttoexcelscanner.commons.PrefsUtils.getJson;
import static com.fastcon.producttoexcelscanner.commons.PrefsUtils.setJson;
import static com.fastcon.producttoexcelscanner.data.entity.JsonToExcel.jsonToExcel;

public class MainActivity extends BaseActivity implements MainActivityContract.MainActivityContractSub {

    MainActivityViewModel mainActivityViewModel;
    Boolean isConnected;
    Button listButton, scan;
    RecyclerView productList;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        isConnected = MainActivityContract.getInstance().isNetworkAvailable(this);
        mainActivityViewModel = ViewModelProviders.of(this).get(MainActivityViewModel.class);
        setDialogMessage(getString(R.string.please_wait));
        listButton = findViewById(R.id.list_button);
        productList = findViewById(R.id.product_list);
        scan = findViewById(R.id.scan_button);

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        getSupportActionBar().setTitle("Main Page");

    }

    @Override
    protected void initEvents() {
        if (isConnected) {
            checkPermissions();

        } else {
            toastMessage(getString(R.string.check_connected));
            finish();
        }
        scan.setOnClickListener(v -> {
            MainActivityContract.getInstance().startScan(MainActivity.this);
        });
        initMiscEvents();
    }

    @Override
    protected void initMiscEvents() {
        listButton.setOnClickListener(v -> {
            showProgress();

            mainActivityViewModel.getProductList();
            mainActivityViewModel._retrieve.observe(this, items -> {

                MainActivityAdapter adapter = new MainActivityAdapter(this, items);
                LinearLayoutManager layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);

                productList.setLayoutManager(layoutManager);
                DividerItemDecorator dividerItemDecoration = new
                        DividerItemDecorator(ContextCompat.getDrawable(this, R.drawable.decoration_line));
                productList.addItemDecoration(dividerItemDecoration);
                productList.setAdapter(adapter);
                hideProgress();
            });
        });
    }


    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);

        if (result != null) {
            if (result.getContents() != null) {
                showProgress();
                mainActivityViewModel.enterBarcode(result.getContents());
                mainActivityViewModel._inserted.observe(this, insertDataResponse -> {
                            toastMessage(getString(R.string.successful_added));
                            hideProgress();
                        }
                );
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }

    @Override
    public void checkPermissions() {
        Dexter.withContext(this)
                .withPermissions(Manifest.permission.CAMERA,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                .withListener(new MultiplePermissionsListener() {
                    @Override
                    public void onPermissionsChecked(MultiplePermissionsReport report) {

                    }

                    @Override
                    public void onPermissionRationaleShouldBeShown(List<PermissionRequest> permissions, PermissionToken token) {/* ... */}
                }).check();
    }


    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save_file:
                showProgress();
                try {
                    jsonToExcel(getJson());
                    hideProgress();
                } catch (Exception e) {
                    e.printStackTrace();
                }
        }
        return super.onOptionsItemSelected(item);
    }
}
