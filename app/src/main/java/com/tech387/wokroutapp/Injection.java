package com.tech387.wokroutapp;

import android.content.Context;

import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.ExerciseRepository;
import com.tech387.wokroutapp.data.storage.WorkoutRepository;
import com.tech387.wokroutapp.data.storage.local.AppDatabase;
import com.tech387.wokroutapp.data.storage.local.Tag.TagLocalDataSource;
import com.tech387.wokroutapp.data.storage.local.exercise.ExerciseLocalDataSource;
import com.tech387.wokroutapp.data.storage.local.workout.WorkoutLocalDataSource;
import com.tech387.wokroutapp.data.storage.remote.content.ContentRemoteDataSource;
import com.tech387.wokroutapp.util.AppExecutors;

public class Injection {

    public static AppDatabase provideAppDatabase(Context context) {
        return AppDatabase.getInstance(context.getApplicationContext());
    }

    public static AppExecutors provideAppExecutors() {
        return new AppExecutors();
    }

    public static ContentRemoteDataSource provideContentRemoteDataSource() {
        return ContentRemoteDataSource.getInstance();
    }

    public static ExerciseLocalDataSource provideExerciseLocalDataSource(Context context) {
        return ExerciseLocalDataSource.getInstance(
                provideAppDatabase(context.getApplicationContext()).getExerciseDao(),
                provideAppExecutors()
        );
    }

    public static WorkoutLocalDataSource provideWorkoutLocalDataSource(Context context) {
        return WorkoutLocalDataSource.getInstance(
                provideAppDatabase(context.getApplicationContext()).getWorkoutDao(),
                provideAppExecutors()
        );
    }

    public static TagLocalDataSource provideTagLocalDataSOurce(Context context){
        return TagLocalDataSource.getInstance(
                provideAppDatabase(context.getApplicationContext()).getTagDao(),
                provideAppExecutors()
        );
    }
    public static ContentRepository provideContentRepository(Context context) {
        return ContentRepository.getsInstance(
                provideAppExecutors(),
                provideContentRemoteDataSource(),
                provideExerciseLocalDataSource(context.getApplicationContext()),
                provideWorkoutLocalDataSource(context.getApplicationContext()),
                provideTagLocalDataSOurce(context.getApplicationContext())
        );
    }

    public static ExerciseRepository provideExerciseRepository(Context context) {
        return ExerciseRepository.getInstance(
                provideExerciseLocalDataSource(context.getApplicationContext())
        );
    }

    public static WorkoutRepository provideWorkoutRepository(Context context){
        return WorkoutRepository.getInstance(
                provideWorkoutLocalDataSource(context.getApplicationContext())
        );
    }

}
