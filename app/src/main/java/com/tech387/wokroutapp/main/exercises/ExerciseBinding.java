package com.tech387.wokroutapp.main.exercises;

import android.databinding.BindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.tech387.wokroutapp.data.storage.local.Tag.Tag;

import java.util.List;

public class ExerciseBinding {

    private static final String TAG = ExerciseBinding.class.getSimpleName();

    /**
     * add get name tags from tags scope
     * @param textView -> send string in textView layout
     * @param tags -> tags from list of object Tag
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:exerciseTags"})
    public static void setText(TextView textView, List<Tag> tags) {
        //img
        String finalTags = "";

        for (int i = 0; i < tags.size(); i++) {
            finalTags += tags.get(i).getName();

            if (i < tags.size() - 1) {
                finalTags += ", ";
            }

        }

        textView.setText(finalTags);
    }

    /**
     * use library Glide
     * @param view -> imageVIew
     * @param img -> use string in this case string URL
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:loadImage"})
    public static void setImage(ImageView view, String img){
        //img
        Glide.with(view.getContext())
                .load(img)
                .into(view);
    }

    /**
     * checking adapter
     * @param recyclerView
     * @param items
     */
    @SuppressWarnings("unchecked")
    @BindingAdapter("app:exercise")
    public static void setExercise(RecyclerView recyclerView, List items) {
        RecycleViewAdapterOne adapter = (RecycleViewAdapterOne) recyclerView.getAdapter();
        if (adapter != null && items != null) {
            adapter.setExercise(items);
        }
    }

}

