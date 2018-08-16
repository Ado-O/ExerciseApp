package com.tech387.wokroutapp.main.wokrouts;

import android.databinding.BindingAdapter;
import android.support.design.chip.Chip;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.tech387.wokroutapp.R;
import com.tech387.wokroutapp.data.storage.local.Tag.Tag;
import com.tech387.wokroutapp.data.storage.local.workout.Workout;

import java.util.List;

public class WorkoutBinding {

    private static final String TAG = WorkoutBinding.class.getSimpleName();

    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:workoutTags"})
    public static void setText(TextView textView, List<Tag> tags) {

        String text = "";

        for (int i = 0; i < tags.size(); i++) {
            text += tags.get(i).getName() + " ";
        }

        textView.setText(text);
    }


    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:duration"})
    public static void setNumber(TextView textView, Long number) {

        int minutes = (int) ((number / (1000 * 60)) % 60);

        String addMinuit = minutes + " min";

        textView.setText(String.valueOf(addMinuit));
    }


    @SuppressWarnings("unchecked")
    @BindingAdapter({"app:tags"})
    public static void setLayout(LinearLayout linearLayout, List tags) {

        linearLayout.removeAllViews();

        if (tags != null) {
            LayoutInflater inflater = LayoutInflater.from(linearLayout.getContext());
           for (Tag tag :((List<Tag>)tags)) {

                TextView view = (TextView) inflater.inflate(
                        R.layout.tag, linearLayout, false
                );

                view.setText(tag.getName());
                linearLayout.addView(view);
            }
        }

    }

}
