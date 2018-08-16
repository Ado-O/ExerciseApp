package com.tech387.wokroutapp.data.storage.local;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;

import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackage;
import com.tech387.wokroutapp.data.storage.local.exercise.Exercise;
import com.tech387.wokroutapp.data.storage.local.exercise.ExerciseTag;
import com.tech387.wokroutapp.data.storage.local.Tag.Tag;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackageDao;
import com.tech387.wokroutapp.data.storage.local.shoppackage.ShopPackageTag;
import com.tech387.wokroutapp.data.storage.local.workout.Workout;
import com.tech387.wokroutapp.data.storage.local.workout.WorkoutTag;
import com.tech387.wokroutapp.data.storage.local.Tag.TagDao;
import com.tech387.wokroutapp.data.storage.local.exercise.ExerciseDao;
import com.tech387.wokroutapp.data.storage.local.workout.WorkoutDao;

@Database(entities = {
        Exercise.class,
        Workout.class,
        Tag.class,
        ExerciseTag.class,
        WorkoutTag.class,
        ShopPackage.class,
        ShopPackageTag.class
}, version = 8, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    private static AppDatabase INSTANCE;

    public abstract ExerciseDao getExerciseDao();

    public abstract WorkoutDao getWorkoutDao();

    public abstract TagDao getTagDao();

    public abstract ShopPackageDao getShopPackage();

    public static final Object sLock = new Object();

    public static AppDatabase getInstance(Context context) {
        synchronized (sLock) {
            if (INSTANCE == null) {
                INSTANCE = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, "app.db").build();
            }
            return INSTANCE;
        }
    }
}