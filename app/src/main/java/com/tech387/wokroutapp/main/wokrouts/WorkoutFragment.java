package com.tech387.wokroutapp.main.wokrouts;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech387.wokroutapp.Injection;
import com.tech387.wokroutapp.R;
import com.tech387.wokroutapp.data.Workout;
import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.WorkoutRepository;
import com.tech387.wokroutapp.databinding.WorkoutListBinding;

import java.util.List;

public class WorkoutFragment extends Fragment {

    Context mContext;
    private RecyclerView mRecyclerView;
    private RecycleViewAdapterTwo mRecycleViewAdapterTwo;
    private WorkoutRepository mWorkoutRepository;
    private ContentRepository mContentRepository;

    public static WorkoutFragment newInstance() {
        return new WorkoutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.workout_frag, container, false);
        //create context
        mContext = getActivity();

        mRecyclerView = (RecyclerView) view.findViewById(R.id.rv_workout);

        setupRvTwo();

        return view;
    }

    public void setupRvTwo() {

        mContentRepository = Injection.provideContentRepository(mContext);
        mContentRepository.getContent();

        mWorkoutRepository = Injection.provideWorkoutRepository(mContext);
        mWorkoutRepository.getWorkout(new WorkoutRepository.GetWorkoutsCallback() {
            @Override
            public void onSuccess(List<Workout> workouts) {

                //create adapter and take list
                mRecycleViewAdapterTwo = new RecycleViewAdapterTwo(mContext, workouts);
                mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
                mRecyclerView.setAdapter(mRecycleViewAdapterTwo);
            }

            @Override
            public void onError() {

            }
        });
    }
}