package com.tech387.wokroutapp.data;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "workout_tag_table")
public class WorkoutTag {

    @ColumnInfo(name = "_id")
    @PrimaryKey(autoGenerate = true)
    private Integer mId;

    @ColumnInfo(name = "workout_id")
    private long mWorkoutId;

    @ColumnInfo(name = "tag_id")
    private long mTagId;

    @ColumnInfo(name = "type")
    private String mType;

    public WorkoutTag(Integer id, long workoutId, long tagId, String type) {
        mId = id;
        mWorkoutId = workoutId;
        mTagId = tagId;
        mType = type;
    }

    @Ignore
    public WorkoutTag(long workoutId, long tagId, String type) {
        mId = null;
        mWorkoutId = workoutId;
        mTagId = tagId;
        mType = type;
    }

    public Integer getId() {
        return mId;
    }

    public void setId(Integer mId) {
        this.mId = mId;
    }

    public long getWorkoutId() {
        return mWorkoutId;
    }

    public void setWorkoutId(long mWorkoutId) {
        this.mWorkoutId = mWorkoutId;
    }

    public long getTagId() {
        return mTagId;
    }

    public void setTagId(long mTagId) {
        this.mTagId = mTagId;
    }

    public String getType() {
        return mType;
    }

    public void setType(String mType) {
        this.mType = mType;
    }
}
