package com.tech387.wokroutapp.data.storage;

import android.util.Log;

import com.tech387.wokroutapp.data.storage.local.Tag.TagLocalDataSource;
import com.tech387.wokroutapp.data.storage.local.exercise.ExerciseLocalDataSource;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackageLocalDataSource;
import com.tech387.wokroutapp.data.storage.local.workout.WorkoutLocalDataSource;
import com.tech387.wokroutapp.data.storage.remote.content.ContentRemoteDataSource;
import com.tech387.wokroutapp.data.storage.remote.response.BaseResponse;
import com.tech387.wokroutapp.util.AppExecutors;

public class ContentRepository {

    public static final String TAG = ContentRepository.class.getSimpleName();

    private static ContentRepository sInstance = null;

    private final AppExecutors mAppExecutors;

    private final ContentRemoteDataSource mContentRemoteDataSource;
    private final ExerciseLocalDataSource mExerciseLocalDataSource;
    private final WorkoutLocalDataSource mWorkoutLocalDataSource;
    private final TagLocalDataSource mTagLocalDataSource;
    private final ShopPackageLocalDataSource mShopPackageLocalDataSource;

    public ContentRepository(
            AppExecutors appExecutors,
            ContentRemoteDataSource contentRemoteDataSource,
            ExerciseLocalDataSource exerciseLocalDataSource,
            WorkoutLocalDataSource workoutLocalDataSource,
            TagLocalDataSource tagLocalDataSource,
            ShopPackageLocalDataSource shopPackageLocalDataSource) {
        mContentRemoteDataSource = contentRemoteDataSource;
        mExerciseLocalDataSource = exerciseLocalDataSource;
        mAppExecutors = appExecutors;
        mWorkoutLocalDataSource = workoutLocalDataSource;
        mTagLocalDataSource = tagLocalDataSource;
        mShopPackageLocalDataSource = shopPackageLocalDataSource;
    }

    public static ContentRepository getsInstance(
            AppExecutors appExecutors,
            ContentRemoteDataSource contentRemoteDataSource,
            ExerciseLocalDataSource exerciseLocalDataSource,
            WorkoutLocalDataSource workoutLocalDataSource,
            TagLocalDataSource tagLocalDataSource,
            ShopPackageLocalDataSource shopPackageLocalDataSource) {
        if (sInstance == null)
            sInstance = new ContentRepository(
                    appExecutors,
                    contentRemoteDataSource,
                    exerciseLocalDataSource,
                    workoutLocalDataSource,
                    tagLocalDataSource,
                    shopPackageLocalDataSource);

        return sInstance;
    }

    public void getContent(){

        mContentRemoteDataSource.getContent(new ContentRemoteDataSource.GetContentCallback() {
            @Override
            public void onSuccess(final BaseResponse content) {
                mAppExecutors.diskIO().execute(new Runnable() {
                    @Override
                    public void run() {

                        mExerciseLocalDataSource.insertExercise
                                (content.getResponseResponse().getExercise());

                        mWorkoutLocalDataSource.insertWorkouts
                                (content.getResponseResponse().getWorkouts());

                        mTagLocalDataSource.insertTag
                                (content.getResponseResponse().getmTag());

                        mShopPackageLocalDataSource.insert
                                        (content.getResponseResponse().getmPackages());

                    }
                });
            }

            @Override
            public void onError() {
                Log.e(TAG, "OnError");

            }
        });
    }

}
