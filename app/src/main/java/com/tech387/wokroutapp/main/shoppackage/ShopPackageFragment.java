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
import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.ShopPackageRespository;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackage;

import java.util.ArrayList;
import java.util.List;

public class ShopPackageFragment extends Fragment{

    private Context mContext;
    private RecyclerView mRecyclerView;
    private RecycleViewAdapterThree mRecycleViewAdapterThree;
    private ContentRepository mContentRepository;
    private ShopPackageRespository mShopPackageRespository;

    public static ShopPackageFragment newInstance() {
        return new ShopPackageFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.shop_package_frag, container, false);

        mContext = getActivity();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_package);

        setupRvThree();

        return view;
    }


    public void setupRvThree(){

        mContentRepository = Injection.provideContentRepository(mContext);
        mContentRepository.getContent();

        mShopPackageRespository = Injection.provideShopPackageRepository(mContext);
        mShopPackageRespository.getShopPackages(new ShopPackageRespository.GetExerciseCallback() {
            @Override
            public void onSuccess(List<ShopPackage> shopPackages) {

                //create adapter and take list
                mRecycleViewAdapterThree = new RecycleViewAdapterThree(mContext, shopPackages);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mRecycleViewAdapterThree);

            }

            @Override
            public void onError() {

            }
        });

    }
}
