package com.tech387.wokroutapp.data.storage.local.workout;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;

import com.tech387.wokroutapp.data.storage.local.Tag.Tag;

import java.util.List;

@Entity(tableName = "workout_table")
public class Workout {

    @ColumnInfo(name = "_id")
    @PrimaryKey
    private long mId;

    @ColumnInfo(name = "name")
    private String mName;

    @ColumnInfo(name = "description")
    private String mDescription;

    @ColumnInfo(name = "duration")
    private long mDuration;

    @Ignore
    private List<Tag> mTags;

    @Ignore
    private List<Tag> mEquipentTags;

    public Workout(long id, String name, String description, long duration) {
        mId = id;
        mName = name;
        mDescription = description;
        mDuration = duration;
    }

    public long getId() {
        return mId;
    }

    public void setId(long id) {
        mId = id;
    }

    public String getName() {
        return mName;
    }

    public void setName(String name) {
        mName = name;
    }

    public String getDescription() {
        return mDescription;
    }

    public void setDescription(String description) {
        mDescription = description;
    }

    public List<Tag> getTags() { return mTags; }

    public void setTags(List<Tag> tags) {
        mTags = tags;
    }

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long mDuration) {
        this.mDuration = mDuration;
    }

    public List<Tag> getEquipentTags() {
        return mEquipentTags;
    }

    public void setEquipentTags(List<Tag> mEquipentTags) {
        this.mEquipentTags = mEquipentTags;
    }

}