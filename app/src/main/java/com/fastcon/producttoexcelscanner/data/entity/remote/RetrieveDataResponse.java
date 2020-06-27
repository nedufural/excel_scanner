package com.fastcon.producttoexcelscanner.data.entity.remote;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class RetrieveDataResponse {

    @SerializedName("items")
    @Expose
    private List<Item> items = null;


    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    public class Item {

        @SerializedName("Barcode")
        @Expose
        private String barcode;
        @SerializedName("Product Name")
        @Expose
        private String productName;
        @SerializedName("Description")
        @Expose
        private String description;
        @SerializedName("Volume")
        @Expose
        private String volume;

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
}