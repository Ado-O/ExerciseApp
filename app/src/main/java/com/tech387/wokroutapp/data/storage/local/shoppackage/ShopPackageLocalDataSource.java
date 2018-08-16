package com.tech387.wokroutapp.data.storage.local.shoppackage;

import com.tech387.wokroutapp.data.storage.convertor.RemoteToLocal;
import com.tech387.wokroutapp.data.storage.remote.response.ExerciseResponse;
import com.tech387.wokroutapp.data.storage.remote.response.PackagesResponse;
import com.tech387.wokroutapp.util.AppExecutors;

import java.util.List;

public class ShopPackageLocalDataSource {

    private static ShopPackageLocalDataSource sInstance = null;

    private final ShopPackageDao mShopPackageDao;
    private final AppExecutors mAppExecutors;

    private ShopPackageLocalDataSource(ShopPackageDao shopPackageDao, AppExecutors appExecutors) {
        mShopPackageDao = shopPackageDao;
        mAppExecutors = appExecutors;
    }

    public static ShopPackageLocalDataSource getInstance(ShopPackageDao shopPackageDao, AppExecutors appExecutors) {
        if (sInstance == null) {
            sInstance = new ShopPackageLocalDataSource(shopPackageDao, appExecutors);
        }
        return sInstance;
    }

    public void insert(List<PackagesResponse> packagesResponses) {
        mShopPackageDao.insert(RemoteToLocal.shopPackagesConverter(packagesResponses));

        for (PackagesResponse e : packagesResponses) {
            mShopPackageDao.clearTags(e.getId());
            mShopPackageDao.insertShopPackageTags(RemoteToLocal.shopPackageTagConverter(e.getId(), e.getTags()));
        }
    }


    public void getShopPackage(final GetExerciseCallback callback) {
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<ShopPackage> shopPackages = mShopPackageDao.getShopPackage();

                for (ShopPackage e : shopPackages) {
                    e.setTags(mShopPackageDao.getShopPackageTag(e.getId()));
                }

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(shopPackages);
                    }
                });
            }
        });


    }


    public interface GetExerciseCallback {
        void onSuccess(List<ShopPackage> shopPackages);

        void onError();
    }


}
