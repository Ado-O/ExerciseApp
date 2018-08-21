package com.tech387.wokroutapp.main.shoppackage;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;

import java.util.List;

public class ShopPackageBinding {

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:shop"})
    public static void setPackage(RecyclerView recyclerView, List items) {
        RecycleViewAdapterThree adapter = (RecycleViewAdapterThree) recyclerView.getAdapter();
        if (adapter != null && items != null) {
            adapter.setShopPackage(items);
        }
    }

}
