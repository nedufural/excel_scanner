package com.fastcon.producttoexcelscanner.data.entity;

import com.google.gson.annotations.SerializedName;

public class InsertDataResponse {
    @SerializedName("message")
    String message;
    @SerializedName("date")
    String date;
    @SerializedName("barcode")
    String barcode;
    @SerializedName("id")
    String id;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
