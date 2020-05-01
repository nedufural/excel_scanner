package com.fastcon.producttoexcelscanner.ui.main.adapter;

import android.content.Context;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.fastcon.producttoexcelscanner.R;
import com.fastcon.producttoexcelscanner.base.BaseRecyclerViewAdapter;
import com.fastcon.producttoexcelscanner.data.entity.RetrieveDataResponse;

import java.util.ArrayList;

public class MainActivityAdapter extends BaseRecyclerViewAdapter {
    private TextView barcode, productName;

    public MainActivityAdapter(Context context, ArrayList<RetrieveDataResponse.Item> arrayList) {
        super(context);
        dataList = arrayList;
        layout_id = R.layout.product_list_items;
    }

    @Override
    public View getView(View view) {
        barcode = bind(R.id.barcode);
        productName = bind(R.id.product_name);
        return view;
    }

    @Override
    public void onBindViewHold(int position, Object itemView) {

        RetrieveDataResponse.Item text=(RetrieveDataResponse.Item) itemView;
        barcode.setText(text.getBarcode());
        productName.setText(text.getProductName());
    }
}


