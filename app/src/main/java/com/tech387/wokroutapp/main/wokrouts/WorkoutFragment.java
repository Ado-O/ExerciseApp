package com.tech387.wokroutapp.main.wokrouts;

import android.content.Context;
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
import com.tech387.wokroutapp.ViewModelFactory;
import com.tech387.wokroutapp.data.storage.local.workout.Workout;
import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.WorkoutRepository;
import com.tech387.wokroutapp.databinding.WorkoutFragBinding;

import java.util.List;

public class WorkoutFragment extends Fragment {

    Context mContext;
    private RecycleViewAdapterTwo mRecycleViewAdapterTwo;
    private WorkoutViewModel mWorkoutViewModel;
    private WorkoutFragBinding mBinding;

    public static WorkoutFragment newInstance() {
        return new WorkoutFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = WorkoutFragBinding.inflate(inflater, container, false);

        mContext = getActivity();

        mWorkoutViewModel = ViewModelFactory.obtainViewModel(getActivity(), WorkoutViewModel.class);
        mWorkoutViewModel.start();
        mBinding.setViewModel(mWorkoutViewModel);

        setupRvTwo();

        return mBinding.getRoot();
    }

    public void setupRvTwo() {

        mRecycleViewAdapterTwo = new RecycleViewAdapterTwo(mContext);
        mBinding.rvWorkout.setLayoutManager(new LinearLayoutManager(getActivity()));
        mBinding.rvWorkout.setAdapter(mRecycleViewAdapterTwo);

    }
}