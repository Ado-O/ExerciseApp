package com.tech387.wokroutapp.main.shoppackage;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackage;
import com.tech387.wokroutapp.databinding.ShopPackageListBinding;

import java.util.List;

public class RecycleViewAdapterThree extends RecyclerView.Adapter {


    private List<ShopPackage> mList;
    private LayoutInflater mInflater;

    public RecycleViewAdapterThree(Context context, List<ShopPackage> list) {
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ShopPackageViewHolder(ShopPackageListBinding.inflate(mInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((ShopPackageViewHolder)holder).setup(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}
