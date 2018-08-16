package com.tech387.wokroutapp.data.storage;

import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackage;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackageLocalDataSource;

import java.util.List;

public class ShopPackageRespository {

    public static final String TAG = ShopPackageRespository.class.getSimpleName();

    private static ShopPackageRespository sInstance = null;

    private final ShopPackageLocalDataSource mShopPackageLocalDataSource;

    public ShopPackageRespository(ShopPackageLocalDataSource shopPackageLocalDataSource) {
        mShopPackageLocalDataSource = shopPackageLocalDataSource;
    }

    public static ShopPackageRespository getInstance(ShopPackageLocalDataSource shopPackageLocalDataSource) {
        if (sInstance == null) {
            sInstance = new ShopPackageRespository(shopPackageLocalDataSource);
        }
        return sInstance;
    }

    public void getShopPackages(final GetExerciseCallback callback) {
        mShopPackageLocalDataSource.getShopPackage(new ShopPackageLocalDataSource.GetExerciseCallback() {
            @Override
            public void onSuccess(List<ShopPackage> shopPackages) {

                callback.onSuccess(shopPackages);
            }

            @Override
            public void onError() {
                callback.onError();
            }
        });
    }

    public interface GetExerciseCallback {
        void onSuccess(List<ShopPackage> shopPackages);

        void onError();
    }
}
