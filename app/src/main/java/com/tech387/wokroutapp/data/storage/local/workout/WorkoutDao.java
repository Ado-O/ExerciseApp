package com.tech387.wokroutapp.data.storage.local.workout;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tech387.wokroutapp.data.Tag;
import com.tech387.wokroutapp.data.Workout;
import com.tech387.wokroutapp.data.WorkoutTag;

import java.util.List;

@Dao
public interface WorkoutDao {

    //onCOnfilctStrategy we we identify the duplicate data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Workout> workouts);

    //in this line we teak everting from workout table
    @Query("SELECT * FROM workout_table")
    List<Workout> getWorkouts();

    @Query("SELECT tag_table.* FROM tag_table INNER JOIN workout_tag_table ON " +
            "tag_table._id= workout_tag_table.tag_id WHERE workout_tag_table.workout_id = :workoutId " +
            "AND workout_tag_table.type =:type")
    List<Tag> getWorkoutTags(long workoutId, String type);

    @Insert
    void insertWorkoutTags(List<WorkoutTag> workoutTags);

    //  call clearTags before insertExerciseTags
    @Query("DELETE FROM workout_tag_table WHERE workout_id=:workoutId")
    void clearTags(long workoutId);
}
