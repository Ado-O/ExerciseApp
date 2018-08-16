package com.tech387.wokroutapp.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;
import java.util.List;

public class ExerciseResponse implements Serializable{

    @SerializedName("id")
    @Expose
    private long id;

    @SerializedName("name")
    @Expose
    private String name;

    @SerializedName("raw_name")
    @Expose
    private String rawName;

    @SerializedName("hardness")
    @Expose
    private String hardness;

    @SerializedName("thumbnail")
    @Expose
    private String thumbnail;

    @SerializedName("formats")
    @Expose
    private List<FormatResponse> formats = null;

    @SerializedName("muscles_involved")
    @Expose
    private String musclesInvolved;

    @SerializedName("version")
    @Expose
    private String version;

    //add in list all tag which are located
    @SerializedName("tags")
    @Expose
    private List<Integer> tags = null;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRawName() {
        return rawName;
    }

    public void setRawName(String rawName) {
        this.rawName = rawName;
    }

    public String getHardness() {
        return hardness;
    }

    public void setHardness(String hardness) {
        this.hardness = hardness;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public List<FormatResponse> getFormats() {
        return formats;
    }

    public void setFormats(List<FormatResponse> formats) {
        this.formats = formats;
    }

    public String getMusclesInvolved() {
        return musclesInvolved;
    }

    public void setMusclesInvolved(String musclesInvolved) {
        this.musclesInvolved = musclesInvolved;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public List<Integer> getTags() {
        return tags;
    }

    public void setTags(List<Integer> tags) {
        this.tags = tags;
    }

}
