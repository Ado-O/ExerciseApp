package com.tech387.wokroutapp.data.storage.local.exercise;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;

import com.tech387.wokroutapp.data.storage.local.Tag.Tag;

import java.util.List;

@Dao
public interface ExerciseDao {

    //onCOnfilctStrategy we we identify the duplicate data
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insert(List<Exercise> exercise);

    //in this line we teak everting from exercise table
    @Query("SELECT * FROM exercise_table")
    List<Exercise> getExercise();

    /**
     * from exercise_tag_table compare
     * tag_table.id with id exercise_tag_table.tag_id
     * but we send exercise_tag_table.exercise_id
     * and put select tag_table in List getExerciseTags
     *
     * @param exerciseId -> paramet when we get id from exercise_table
     * @return
     *
     * */
    @Query("SELECT tag_table.* FROM tag_table INNER JOIN exercise_tag_table ON " +
            "tag_table._id= exercise_tag_table.tag_id WHERE exercise_tag_table.exercise_id = :exerciseId")
    List<Tag> getExerciseTags(long exerciseId);

    /**
     * clearTags() function works: from table exercise_tag compare exercise_id and
     * eliminate the same
     * <p>
     * insertExerciseTags() function works: add excersie_id in ExerciseTag
     */
    @Insert
    void insertExerciseTags(List<ExerciseTag> exerciseTags);

    //  call clearTags before insertExerciseTags
    @Query("DELETE FROM exercise_tag_table WHERE exercise_id=:exerciseId")
    void clearTags(long exerciseId);
}
