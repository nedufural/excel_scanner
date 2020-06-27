package com.fastcon.producttoexcelscanner.commons;

import com.fastcon.producttoexcelscanner.data.entity.remote.RetrieveDataResponse;
import com.orhanobut.hawk.Hawk;

import java.util.List;

/**
 * These saves all data in shared preference. hawk lib provides encryption
 */
public class PrefsUtils {

    static void clearExcelLink() {
        Hawk.delete("excel_link");
    }

    public static void setExcelLink(String link) {
        Hawk.put("excel_link", link);
    }

    public static String getExcelLink() {

        return Hawk.get("excel_link", "");
    }

    static void clearSpreadSheetName() {
        Hawk.delete("spread_sheet_name");
    }

    public static void setSpreadSheetName(String spread_sheet_name) {
        Hawk.put("spread_sheet_name", spread_sheet_name);
    }

    public static String getSpreadSheetName() {

        return Hawk.get("spread_sheet_name", "");
    }

    public static void setJson(List<RetrieveDataResponse.Item> json) {
        Hawk.put("json", json);
    }

    public static List<RetrieveDataResponse.Item>  getJson() {

        return Hawk.get("json",null);
    }

}