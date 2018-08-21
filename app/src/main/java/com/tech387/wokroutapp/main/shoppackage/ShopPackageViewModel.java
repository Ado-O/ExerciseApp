package com.tech387.wokroutapp.main.shoppackage;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.tech387.wokroutapp.SingleLiveEvent;
import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.ShopPackageRespository;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackage;

import java.util.List;

public class ShopPackageViewModel extends AndroidViewModel {

    private ShopPackageRespository mShopPackageRespository;
    private ContentRepository mContentRepository;

    private final SingleLiveEvent<ShopPackage> mOpenShopPackageEvente = new SingleLiveEvent<>();

    public final ObservableList<ShopPackage> mItem = new ObservableArrayList<>();

    public final ObservableBoolean mError = new ObservableBoolean(false);

    public ShopPackageViewModel(@NonNull Application application,
                                ContentRepository contentRepository,
                                ShopPackageRespository shopPackageRespository) {
        super(application);

        mShopPackageRespository = shopPackageRespository;
        mContentRepository = contentRepository;
    }


    public void onStart(){

        if (mItem.isEmpty()) {
            getShopPackage();
        }
        }

        public void getShopPackage(){

            mShopPackageRespository.getShopPackages(new ShopPackageRespository.GetShopPackages() {
                @Override
                public void onSuccess(List<ShopPackage> shopPackages) {
                    mItem.clear();
                    mItem.addAll(shopPackages);
                    mError.set(mItem.isEmpty());
                }

                @Override
                public void onError() {

                }
            });
        }


    public SingleLiveEvent<ShopPackage> getOpenShopPackageEvente() {
        return mOpenShopPackageEvente;
    }


}
