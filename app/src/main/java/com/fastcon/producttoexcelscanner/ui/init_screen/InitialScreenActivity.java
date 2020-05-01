package com.fastcon.producttoexcelscanner.ui.init_screen;

import android.content.Intent;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.fastcon.producttoexcelscanner.R;
import com.fastcon.producttoexcelscanner.base.BaseActivity;
import com.fastcon.producttoexcelscanner.commons.Extensions;
import com.fastcon.producttoexcelscanner.commons.PrefsUtils;
import com.fastcon.producttoexcelscanner.data.usecases.UseCaseInitialScreen;
import com.fastcon.producttoexcelscanner.ui.main.MainActivity;

import static com.fastcon.producttoexcelscanner.commons.Utils.AVG_DELAY;

public class InitialScreenActivity extends BaseActivity implements UseCaseInitialScreen, InitialScreenContract {

    Button scan, addLink, save;
    EditText link, spreadSheetName;
    String excelLink, sheetName;

    @Override
    protected int getLayoutID() {
        return R.layout.activity_splash;
    }

    @Override
    protected void initData() {
        scan = findViewById(R.id.scan);
        addLink = findViewById(R.id.link_name);
        save = findViewById(R.id.save);
        link = findViewById(R.id.link);
        spreadSheetName = findViewById(R.id.sheet_name);
        setDialogMessage(getString(R.string.please_wait));
    }

    @Override
    protected void initEvents() {
        if (getExcelLinkUrl() == null || getExcelSheetName() == null) {

            disableViews();
        }
        scan.setOnClickListener(v -> {

            startActivity(new Intent(this, MainActivity.class));

        });
        addLink.setOnClickListener(v -> {

            showViews();

        });
        save.setOnClickListener(v -> {


            Extensions.hideSoftKeyboard(this, link);

            excelLink = link.getText().toString();
            sheetName = spreadSheetName.getText().toString();

            if (excelLink.equals("") || sheetName.equals("")) {

                toastMessage(getString(R.string.empty_message));

            } else {
                setExcelLinkUrl(excelLink);
                setExcelSheetName(sheetName);

                initMiscEvents();

                hideViews();
            }
        });
    }

    @Override
    protected void initMiscEvents() {
        showProgress();
        new Handler().postDelayed(() -> {
            if (getExcelLinkUrl() != null && getExcelSheetName() != null) {
                toastMessage(getString(R.string.saved_link));
                hideProgress();
            } else {
                toastMessage(getString(R.string.error_saving_link));
                hideProgress();
            }
        }, AVG_DELAY);
    }

    @Override
    public String getExcelLinkUrl() {
        return PrefsUtils.getExcelLink();
    }

    @Override
    public String getExcelSheetName() {
        return PrefsUtils.getSpreadSheetName();
    }

    @Override
    public void setExcelLinkUrl(String link) {
        PrefsUtils.setExcelLink(link);
    }

    @Override
    public void setExcelSheetName(String excelSheetName) {
        PrefsUtils.setSpreadSheetName(excelSheetName);
    }

    @Override
    public void hideViews() {
        link.setVisibility(View.GONE);
        spreadSheetName.setVisibility(View.GONE);
        save.setVisibility(View.GONE);
    }

    @Override
    public void showViews() {
        link.setVisibility(View.VISIBLE);
        spreadSheetName.setVisibility(View.VISIBLE);
        save.setVisibility(View.VISIBLE);
    }


    @Override
    public void disableViews() {
        scan.setEnabled(false);
    }
}
