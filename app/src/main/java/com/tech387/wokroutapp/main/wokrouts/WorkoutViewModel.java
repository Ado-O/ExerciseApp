package com.tech387.wokroutapp.main.wokrouts;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.databinding.ObservableArrayList;
import android.databinding.ObservableBoolean;
import android.databinding.ObservableList;
import android.support.annotation.NonNull;

import com.tech387.wokroutapp.SingleLiveEvent;
import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.WorkoutRepository;
import com.tech387.wokroutapp.data.storage.local.workout.Workout;

import java.util.List;

public class WorkoutViewModel extends AndroidViewModel {

    private WorkoutRepository mWorkoutRepository;
    private ContentRepository mContentRepository;

    private final SingleLiveEvent<Workout> mOpenWorkoutEvent = new SingleLiveEvent<>();

    public final ObservableList<Workout> mItem = new ObservableArrayList<>();

    public final ObservableBoolean mError = new ObservableBoolean(false);

    public WorkoutViewModel(@NonNull Application application,
                            ContentRepository contentRepository,
                            WorkoutRepository workoutRepository) {
        super(application);

        mWorkoutRepository = workoutRepository;
        mContentRepository = contentRepository;
    }


    public void start() {
        if (mItem.isEmpty()) {
            getWorkout();
        }
    }

    public void getWorkout() {

        mWorkoutRepository.getWorkout(new WorkoutRepository.GetWorkoutsCallback() {
            @Override
            public void onSuccess(List<Workout> workouts) {
                mItem.clear();
                mItem.addAll(workouts);
                mError.set(mItem.isEmpty());
            }

            @Override
            public void onError() {
                mError.set(true);
            }
        });

    }

    //getter
    public SingleLiveEvent<Workout> getOpenWorkoutEvent() {
        return mOpenWorkoutEvent;
    }
}
