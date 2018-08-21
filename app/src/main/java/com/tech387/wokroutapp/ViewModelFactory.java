package com.tech387.wokroutapp;

import android.annotation.SuppressLint;
import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.ViewModel;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.support.annotation.VisibleForTesting;
import android.support.v4.app.FragmentActivity;

import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.ExerciseRepository;
import com.tech387.wokroutapp.data.storage.ShopPackageRespository;
import com.tech387.wokroutapp.data.storage.WorkoutRepository;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackage;
import com.tech387.wokroutapp.main.exercises.ExerciseViewModel;
import com.tech387.wokroutapp.main.shoppackage.ShopPackageViewHolder;
import com.tech387.wokroutapp.main.shoppackage.ShopPackageViewModel;
import com.tech387.wokroutapp.main.wokrouts.WorkoutViewModel;

public class ViewModelFactory extends ViewModelProvider.NewInstanceFactory {
    @SuppressLint("StaticFieldLeak")
    private static volatile ViewModelFactory INSTANCE;

    private final Application mApplication;


    private final ContentRepository mContentRepository;
    private final ExerciseRepository mExerciseRepository;
    private final WorkoutRepository mWorkoutRepository;
    private final ShopPackageRespository mShopPackageRespository;

    public static ViewModelFactory getInstance(Application application) {


        if (INSTANCE == null) {
            synchronized (ViewModelFactory.class) {
                if (INSTANCE == null) {
                    INSTANCE = new ViewModelFactory(
                            application,
                            Injection.provideContentRepository(application),
                            Injection.provideExerciseRepository(application),
                            Injection.provideWorkoutRepository(application),
                            Injection.provideShopPackageRepository(application));
                }
            }
        }
        return INSTANCE;
    }

    @VisibleForTesting
    public static void destroyInstance() {
        INSTANCE = null;
    }

    private ViewModelFactory(Application application,
                             ContentRepository mContentRepository,
                             ExerciseRepository mExerciseRepository,
                             WorkoutRepository mWorkoutRepository,
                             ShopPackageRespository mShopPackageRespository) {
        mApplication = application;
        this.mContentRepository = mContentRepository;
        this.mExerciseRepository = mExerciseRepository;
        this.mWorkoutRepository = mWorkoutRepository;
        this.mShopPackageRespository = mShopPackageRespository;

    }

    @Override
    public <T extends ViewModel> T create(Class<T> modelClass) {
        if (modelClass.isAssignableFrom(ExerciseViewModel.class)) {
            return (T) new ExerciseViewModel(
                    mApplication,
                    mContentRepository,
                    mExerciseRepository);
        }else if (modelClass.isAssignableFrom(WorkoutViewModel.class)) {
            return (T) new WorkoutViewModel(
                    mApplication,
                    mContentRepository,
                    mWorkoutRepository);
        }else if(modelClass.isAssignableFrom(ShopPackageViewModel.class)) {
            return (T) new ShopPackageViewModel(
                    mApplication,
                    mContentRepository,
                    mShopPackageRespository);
        }

        throw new IllegalArgumentException("Unknown ViewModel class: " + modelClass.getName());
    }


    public static <T extends AndroidViewModel> T obtainViewModel(FragmentActivity activity, Class<T> modelClass) {
        // Use a Factory to inject dependencies into the ViewModel
        ViewModelFactory factory = ViewModelFactory.getInstance(activity.getApplication());

        T viewModel =
                ViewModelProviders.of(activity, factory).get(modelClass);

        return viewModel;
    }
}