package com.fastcon.producttoexcelscanner.ui.main.adapter;

import androidx.recyclerview.widget.DiffUtil;

import com.fastcon.producttoexcelscanner.data.entity.local.Products;
import com.fastcon.producttoexcelscanner.data.entity.remote.RetrieveDataResponse;

import java.util.List;

public class MainActivityDiffUtil extends DiffUtil.Callback {
    private final List<RetrieveDataResponse.Item> mOldProductsList;
    private final List<RetrieveDataResponse.Item> mNewProductsList;

    public MainActivityDiffUtil(List<RetrieveDataResponse.Item> mOldProductsList, List<RetrieveDataResponse.Item> mNewProductsList) {
        this.mNewProductsList = mNewProductsList;
        this.mOldProductsList = mOldProductsList;
    }

    @Override
    public int getOldListSize() {
        return mOldProductsList.size();
    }

    @Override
    public int getNewListSize() {
        return mNewProductsList.size();
    }

    @Override
    public boolean areItemsTheSame(int oldItemPosition, int newItemPosition) {
        return mOldProductsList.get(oldItemPosition).getBarcode().equals(mNewProductsList.get(
                newItemPosition).getBarcode());
    }

    @Override
    public boolean areContentsTheSame(int oldItemPosition, int newItemPosition) {
        final RetrieveDataResponse.Item oldProducts = mOldProductsList.get(oldItemPosition);
        final RetrieveDataResponse.Item newProducts = mNewProductsList.get(newItemPosition);

        return oldProducts.getProductName().equals(newProducts.getProductName());
    }


}
