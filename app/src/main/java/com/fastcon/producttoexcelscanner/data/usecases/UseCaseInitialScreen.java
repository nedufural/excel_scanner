package com.fastcon.producttoexcelscanner.data.usecases;

public interface UseCaseInitialScreen {
    String getExcelLinkUrl();

    String getExcelSheetName();

    void setExcelLinkUrl(String link);

    void setExcelSheetName(String excelSheetName);

}
