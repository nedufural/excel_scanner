package com.fastcon.producttoexcelscanner.base;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public abstract class BaseRecyclerViewAdapter extends RecyclerView.Adapter<BaseRecyclerViewAdapter.BaseViewHolder> {

    protected int layout_id;
    protected List<?> dataList = new ArrayList<>();
    private Context context;
    private View itemView;

    public BaseRecyclerViewAdapter(Context context) {
        this.context = context;
    }

    @NonNull
    @Override
    public BaseViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(layout_id, viewGroup, false);
        return new BaseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaseViewHolder viewHolder, int position) {
        onBindViewHold(position, dataList.get(position));
    }

    public abstract View getView(View view);

    @Override
    public int getItemCount() {
        return dataList.size();
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public int getItemViewType(int position) {
        return position;
    }

    public abstract void onBindViewHold(int position, Object itemView);

    class BaseViewHolder extends RecyclerView.ViewHolder {


        BaseViewHolder(@NonNull View itemView) {
            super(itemView);
            BaseRecyclerViewAdapter.this.itemView = itemView;
            getView(itemView);
        }

    }

    protected <T extends View> T bind(int id) {
        return itemView.findViewById(id);
    }
}