package com.tech387.wokroutapp.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class WorkoutResponse implements Serializable{

    @SerializedName("id")
    @Expose
    private long mId;

    @SerializedName("name")
    @Expose
    private String mName;

    @SerializedName("description")
    @Expose
    private String mDescription;

    @SerializedName("duration")
    @Expose
    private long mDuration;

    @SerializedName("tags")
    @Expose
    private List<WorkoutTagResponse> tags = null;

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

    public long getDuration() {
        return mDuration;
    }

    public void setDuration(long duration) {
        mDuration = duration;
    }

    public List<WorkoutTagResponse> getTags() {
        return tags;
    }

    public void setTags(List<WorkoutTagResponse> tags) {
        this.tags = tags;
    }
}
