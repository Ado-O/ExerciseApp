package com.tech387.wokroutapp.data.storage.local.exercise;

import com.tech387.wokroutapp.data.Exercise;
import com.tech387.wokroutapp.data.storage.convertor.RemoteToLocal;
import com.tech387.wokroutapp.data.storage.remote.response.ExerciseResponse;
import com.tech387.wokroutapp.util.AppExecutors;

import java.util.List;

public class ExerciseLocalDataSource {

    private static final String TAG = ExerciseLocalDataSource.class.getSimpleName();

    private static ExerciseLocalDataSource sInstance = null;

    private final ExerciseDao mExerciseDao;
    private final AppExecutors mAppExecutors;

    private ExerciseLocalDataSource(ExerciseDao exerciseDao, AppExecutors appExecutors) {
        mExerciseDao = exerciseDao;
        mAppExecutors = appExecutors;
    }

    public static ExerciseLocalDataSource getInstance(ExerciseDao exerciseDao, AppExecutors appExecutors) {
        if (sInstance == null) {
            sInstance = new ExerciseLocalDataSource(exerciseDao, appExecutors);
        }
        return sInstance;
    }

    /**
     * insert and get data from db
     */
    public void insertExercise(List<ExerciseResponse> exerciseResponses) {
        mExerciseDao.insert(RemoteToLocal.exerciseConverter(exerciseResponses));

        /**
         * in clearTags we send id from exercise_table
         * in insertExerciseTags we send id and Tags from exercise_table
         */
        for (ExerciseResponse e : exerciseResponses) {
            mExerciseDao.clearTags(e.getId());
            mExerciseDao.insertExerciseTags(RemoteToLocal.exerciseTagConverter(e.getId(), e.getTags()));
        }
    }

    /**
     * get all data from db
     * @param callback -> we need callback for send data
     */
    public void getExercises(final GetExerciseCallback callback) {
        mAppExecutors.diskIO().execute(new Runnable() {
            @Override
            public void run() {
                final List<Exercise> exercises = mExerciseDao.getExercise();

                /**
                 * in setTags we send id from tag_table
                 */
                for (Exercise e : exercises) {
                    e.setTags(mExerciseDao.getExerciseTags(e.getId()));
                }

                mAppExecutors.mainThread().execute(new Runnable() {
                    @Override
                    public void run() {
                        callback.onSuccess(exercises);
                    }
                });
            }
        });


    }


    public interface GetExerciseCallback {
        void onSuccess(List<Exercise> exercises);

        void onError();
    }


}



