package com.tech387.wokroutapp.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ResponseResponse implements Serializable {

    @SerializedName("exercises")
    @Expose
    private List<ExerciseResponse> mExercise;

    @SerializedName("workouts")
    @Expose
    private List<WorkoutResponse> mWorkouts;

    @SerializedName("tags")
    @Expose
    private List<TagResponse> mTag;

    @SerializedName("packages")
    @Expose
    private List<PackagesResponse> mPackages;

    public List<ExerciseResponse> getExercise() {
        return mExercise;
    }

    public void setExercise(List<ExerciseResponse> exercise) {
        mExercise = exercise;
    }

    public List<WorkoutResponse> getWorkouts() {
        return mWorkouts;
    }

    public void setWorkouts(List<WorkoutResponse> workouts) {
        mWorkouts = workouts;
    }

    public List<TagResponse> getmTag() {
        return mTag;
    }

    public void setmTag(List<TagResponse> mTag) {
        this.mTag = mTag;
    }

    public List<PackagesResponse> getmPackages() {
        return mPackages;
    }

    public void setmPackages(List<PackagesResponse> mPackages) {
        this.mPackages = mPackages;
    }

}
