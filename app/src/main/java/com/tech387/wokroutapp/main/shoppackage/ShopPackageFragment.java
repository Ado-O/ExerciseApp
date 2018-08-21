package com.tech387.wokroutapp.main.shoppackage;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech387.wokroutapp.Injection;
import com.tech387.wokroutapp.R;
import com.tech387.wokroutapp.ViewModelFactory;
import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.ShopPackageRespository;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackage;
import com.tech387.wokroutapp.databinding.ShopPackageFragBinding;

import java.util.ArrayList;
import java.util.List;

public class ShopPackageFragment extends Fragment {

    private Context mContext;
    private ShopPackageFragBinding mBinding;
    private RecycleViewAdapterThree mRecycleViewAdapterThree;
    private ShopPackageViewModel mShopPackageViewModel;

    public static ShopPackageFragment newInstance() {
        return new ShopPackageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ShopPackageFragBinding.inflate(inflater, container, false);

        mContext = getActivity();

        mShopPackageViewModel = ViewModelFactory.obtainViewModel(getActivity(), ShopPackageViewModel.class);
        mShopPackageViewModel.onStart();
        mBinding.setViewModel(mShopPackageViewModel);

        setupRvThree();

        return mBinding.getRoot();
    }


    public void setupRvThree() {


        //create adapter and take list
        mRecycleViewAdapterThree = new RecycleViewAdapterThree(mContext);
        mBinding.rvPackage.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvPackage.setAdapter(mRecycleViewAdapterThree);


    }
}
