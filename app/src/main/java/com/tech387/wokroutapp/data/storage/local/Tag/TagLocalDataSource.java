package com.tech387.wokroutapp.data.storage.local.Tag;

import com.tech387.wokroutapp.data.Tag;
import com.tech387.wokroutapp.data.storage.convertor.RemoteToLocal;
import com.tech387.wokroutapp.data.storage.remote.response.TagResponse;
import com.tech387.wokroutapp.util.AppExecutors;

import java.util.List;

public class TagLocalDataSource {
    private static TagLocalDataSource sInstance = null;

    private final TagDao mTagDao;
    private final AppExecutors mAppExecutors;

    private TagLocalDataSource(TagDao tagDao, AppExecutors appExecutors) {
        mTagDao = tagDao;
        mAppExecutors = appExecutors;
    }

    public static TagLocalDataSource getInstance(TagDao tagDao, AppExecutors appExecutors) {
        if (sInstance == null) {
            sInstance = new TagLocalDataSource(tagDao, appExecutors);
        }
        return sInstance;
    }

    public void insertTag(List<TagResponse> tagResponses) {
        mTagDao.insert(
                RemoteToLocal.tagConverter(tagResponses)
        );
    }

    public void getTags(final GetTagCallback callback) {
        mAppExecutors.diskIO().execute(
                new Runnable() {
                    @Override
                    public void run() {
                        final List<Tag> tags= mTagDao.getTags();

                        mAppExecutors.mainThread().execute(
                                new Runnable() {
                                    @Override
                                    public void run() {
                                        callback.onSuccess(tags);
                                    }
                                });
                    }
                });
    }

    public interface GetTagCallback {
        void onSuccess(List<Tag> tags);

        void onError();
    }
}
