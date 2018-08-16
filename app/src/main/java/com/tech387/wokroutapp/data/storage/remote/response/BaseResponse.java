package com.tech387.wokroutapp.data.storage.remote.response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class BaseResponse implements Serializable {
    @SerializedName("status")
    @Expose
    private int mStatus;

    @SerializedName("response")
    @Expose
    private ResponseResponse mResponseResponse;

    public int getStatus() {
        return mStatus;
    }

    public void setStatus(int status) {
        mStatus = status;
    }


    public ResponseResponse getResponseResponse() {
        return mResponseResponse;
    }

    public void setResponseResponse(ResponseResponse responseResponse) {
        mResponseResponse = responseResponse;
    }
}
