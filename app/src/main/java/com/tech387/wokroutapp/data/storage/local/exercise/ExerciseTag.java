package com.tech387.wokroutapp.data.storage.local.exercise;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "exercise_tag_table")
public class ExerciseTag {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "exercise_id")
    private long mExerciseId;

    @ColumnInfo(name = "tag_id")
    private long mTagId;

    public ExerciseTag(Integer id, long exerciseId, long tagId) {
        mId = id;
        mExerciseId = exerciseId;
        mTagId = tagId;
    }

    @Ignore
    public ExerciseTag(long exerciseId, long tagId) {
        mId = null;
        mExerciseId = exerciseId;
        mTagId = tagId;
    }


    public Integer getId() {
        return mId;
    }

    public void setId(Integer id) {
        mId = id;
    }

    public long getExerciseId() {
        return mExerciseId;
    }

    public void setExerciseId(long exerciseId) {
        mExerciseId = exerciseId;
    }

    public long getTagId() {
        return mTagId;
    }

    public void setTagId(long tagId) {
        mTagId = tagId;
    }
}
