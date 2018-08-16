package com.tech387.wokroutapp.data.storage.local.workout;

import android.util.Log;

import com.tech387.wokroutapp.data.Workout;
import com.tech387.wokroutapp.data.WorkoutTag;
import com.tech387.wokroutapp.data.storage.convertor.RemoteToLocal;
import com.tech387.wokroutapp.data.storage.remote.response.WorkoutResponse;
import com.tech387.wokroutapp.data.storage.remote.response.WorkoutTagResponse;

import com.tech387.wokroutapp.util.AppExecutors;

import java.util.ArrayList;
import java.util.List;

public class WorkoutLocalDataSource {

    private static final String TAG = WorkoutLocalDataSource.class.getSimpleName();

    private static WorkoutLocalDataSource sInstance = null;

    private final WorkoutDao mWorkoutDao;
    private final AppExecutors mAppExecutors;

    private WorkoutLocalDataSource(WorkoutDao workoutDao, AppExecutors appExecutors) {
        mWorkoutDao = workoutDao;
        mAppExecutors = appExecutors;
    }

    public static WorkoutLocalDataSource getInstance(WorkoutDao workoutDao, AppExecutors appExecutors) {
        if (sInstance == null) {
            sInstance = new WorkoutLocalDataSource(workoutDao, appExecutors);
        }
        return sInstance;
    }

    public void insertWorkouts(List<WorkoutResponse> workoutResponses) {
        mWorkoutDao.insert(RemoteToLocal.workoutConverter(workoutResponses));



       for (WorkoutResponse w : workoutResponses){

           mWorkoutDao.clearTags(w.getId());
           mWorkoutDao.insertWorkoutTags(RemoteToLocal.workoutTagsConverter(
                   w.getId(),
                   w.getTags()
           ));

       }

    }

    public void getWorkouts(final GetWorkoutsCallback callback) {
        mAppExecutors.diskIO().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        final List<Workout> workouts = mWorkoutDao.getWorkouts();

                        for (Workout w : workouts) {

                            w.setTags(mWorkoutDao.getWorkoutTags(
                                    w.getId(),
                                    "R"
                            ));

                            w.setEquipentTags(mWorkoutDao.getWorkoutTags(
                                    w.getId(),
                                    "E"
                            ));
                        }


                            mAppExecutors.mainThread().execute(
                                    new Runnable() {
                                        @Override
                                        public void run() {
                                            callback.onSuccess(workouts);
                                        }
                                    });
                        }
                    });
    }

    public interface GetWorkoutsCallback {
        void onSuccess(List<Workout> workouts);

        void onError();
    }

}

