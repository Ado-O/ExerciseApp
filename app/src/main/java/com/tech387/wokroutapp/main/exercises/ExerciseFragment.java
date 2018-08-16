package com.tech387.wokroutapp.main.exercises;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech387.wokroutapp.Injection;
import com.tech387.wokroutapp.R;
import com.tech387.wokroutapp.data.Exercise;
import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.ExerciseRepository;
import com.tech387.wokroutapp.main.video.VideoActivity;
import com.tech387.wokroutapp.util.RecyclerViewClickListener;

import java.util.List;

public class ExerciseFragment extends Fragment implements RecyclerViewClickListener{

    private static final String TAG = ExerciseFragment.class.getSimpleName();

    Context mContext;
    private RecyclerView mRecyclerView;
    private RecycleViewAdapterOne mRecycleViewAdapter;
    private ExerciseRepository mExerciseRepository;
    private ContentRepository mContentRepository;

    public static ExerciseFragment newInstance() {
        return new ExerciseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.exercise_frag, container, false);

        mContext = getActivity();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_exercise);

        setupRv();

        return view;
    }

    public void setupRv() {

        mContentRepository = Injection.provideContentRepository(mContext);
        mContentRepository.getContent();

        mExerciseRepository = Injection.provideExerciseRepository(mContext);
        mExerciseRepository.getExercises(new ExerciseRepository.GetExerciseCallback() {
            @Override
            public void onSuccess(List<Exercise> exercises) {

                mRecycleViewAdapter = new RecycleViewAdapterOne(mContext, exercises, ExerciseFragment.this);
                mRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 2));
                mRecyclerView.setAdapter(mRecycleViewAdapter);

            }

            @Override
            public void onError() {

            }
        });

    }

    @Override
    public void recyclerViewListClicked(View v, Exercise exercise) {
        Intent intent = new Intent(mContext, VideoActivity.class);
        intent.putExtra("course", exercise.getMuscle());
        intent.putExtra("courseTitle", exercise.getTitle());
        intent.putExtra("courseVideo", exercise.getVideo());
        mContext.startActivity(intent);

    }

}
