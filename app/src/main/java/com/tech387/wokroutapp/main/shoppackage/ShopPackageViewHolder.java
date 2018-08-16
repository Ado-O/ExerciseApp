package com.tech387.wokroutapp.main.shoppackage;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;

import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackage;
import com.tech387.wokroutapp.databinding.ShopPackageListBinding;

public class ShopPackageViewHolder extends RecyclerView.ViewHolder {

    private ShopPackageListBinding mBinding;

    public ShopPackageViewHolder(@NonNull ShopPackageListBinding binding) {
        super(binding.getRoot());

        mBinding = binding;
    }

    public void setup(ShopPackage shopPackage) {
        mBinding.setShoppackage(shopPackage);
    }

}
