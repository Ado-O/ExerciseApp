package com.tech387.wokroutapp.data.storage.remote.content;

import com.tech387.wokroutapp.data.storage.remote.response.BaseResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ContentService {
    /**
     * piece of URL
     */
    @GET("mobile")
    Call<BaseResponse> getContent(
            @Query("app") String app,
            @Query("type") String type,
            @Query("version") String version,
            @Query("lang") String lang
    );
}
