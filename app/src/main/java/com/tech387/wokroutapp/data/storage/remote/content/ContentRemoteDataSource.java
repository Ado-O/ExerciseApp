package com.tech387.wokroutapp.data.storage.remote.content;



import com.tech387.wokroutapp.data.storage.remote.ServiceGenerator;
import com.tech387.wokroutapp.data.storage.remote.response.BaseResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ContentRemoteDataSource {

    private static final String TAG = ContentRemoteDataSource.class.getSimpleName();

    private static ContentRemoteDataSource sInstance;

    private ContentRemoteDataSource() {
    }

    public static ContentRemoteDataSource getInstance() {
        if (sInstance == null) {
            sInstance = new ContentRemoteDataSource();
        }
        return sInstance;
    }

    /**
     * Getting the content from the content url
     *
     * @param callback
     */
    public void getContent(final GetContentCallback callback) {
        ContentService contentService = ServiceGenerator.createService(ContentService.class);

        contentService
                .getContent("spartan.system", "pro", "0", "en")
                .enqueue(new Callback<BaseResponse>() {
                    @Override
                    public void onResponse(Call<BaseResponse> call, Response<BaseResponse> response) {

                        if (response.isSuccessful()) {

                            callback.onSuccess(response.body());
                        } else {
                            callback.onError();
                        }
                    }

                    @Override
                    public void onFailure(Call<BaseResponse> call, Throwable t) {
                        callback.onError();
                    }
                });
    }

    public interface GetContentCallback {
        void onSuccess(BaseResponse content);

        void onError();
    }

}
