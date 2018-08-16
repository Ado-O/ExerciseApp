package com.tech387.wokroutapp.main.wokrouts;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.util.Log;
import android.widget.TextView;

import com.tech387.wokroutapp.data.Tag;
import com.tech387.wokroutapp.data.Workout;
import com.tech387.wokroutapp.data.WorkoutTag;
import com.tech387.wokroutapp.data.storage.remote.response.WorkoutTagResponse;

import java.util.ArrayList;
import java.util.List;

public class WorkoutBinding {

    private static final String TAG = WorkoutBinding.class.getSimpleName();

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:workoutTags"})
    public static void setText(TextView textView, List<Tag> tags) {

        String text = "";

        for (int i = 0; i < tags.size(); i++) {
            text += tags.get(i).getName() + ", ";
        }

        textView.setText(text);
    }


    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:duration"})
    public static void setNumber(TextView textView, Long number) {

        int minutes = (int) ((number / (1000*60)) % 60);

        String addMinuit = minutes + " min";

        textView.setText(String.valueOf(addMinuit));
    }

}
