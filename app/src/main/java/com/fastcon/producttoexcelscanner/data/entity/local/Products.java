package com.fastcon.producttoexcelscanner.data.entity.local;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "products_table")
public class Products {
    @PrimaryKey(autoGenerate = false)
    @NonNull
    private String barcode;
    private String productName;
    private String description;
    private String volume;

    public Products(String barcode, String productName, String description, String volume) {
        this.barcode = barcode;
        this.productName = productName;
        this.description = description;
        this.volume = volume;
    }

    public String getBarcode() {
        return barcode;
    }

    public void setBarcode(String barcode) {
        this.barcode = barcode;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getVolume() {
        return volume;
    }

    public void setVolume(String volume) {
        this.volume = volume;
    }
}
