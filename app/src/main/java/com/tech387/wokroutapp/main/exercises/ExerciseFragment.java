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
import com.tech387.wokroutapp.ViewModelFactory;
import com.tech387.wokroutapp.data.storage.local.exercise.Exercise;
import com.tech387.wokroutapp.data.storage.ContentRepository;
import com.tech387.wokroutapp.data.storage.ExerciseRepository;
import com.tech387.wokroutapp.databinding.ExerciseFragBinding;
import com.tech387.wokroutapp.main.video.VideoActivity;
import com.tech387.wokroutapp.util.RecyclerViewClickListener;

import java.util.List;

public class ExerciseFragment extends Fragment implements RecyclerViewClickListener{


    Context mContext;
    private ExerciseFragBinding mBinding;
    private RecycleViewAdapterOne mRecycleViewAdapter;
    private ExerciseViewModel mExerciseViewModel;


    public static ExerciseFragment newInstance() {
        return new ExerciseFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = ExerciseFragBinding.inflate(inflater, container, false);

        mContext = getActivity();

        mExerciseViewModel = ViewModelFactory.obtainViewModel(getActivity(), ExerciseViewModel.class);
        mExerciseViewModel.start();

        mBinding.setViewModel(mExerciseViewModel);

        setupRv();

        return mBinding.getRoot();
    }

    public void setupRv() {


        mRecycleViewAdapter = new RecycleViewAdapterOne(
                mContext,
                ExerciseFragment.this);

        mBinding.rvExercise.setLayoutManager(new GridLayoutManager(mContext, 2));
        mBinding.rvExercise.setAdapter(mRecycleViewAdapter);

    }

    @Override
    public void recyclerViewListClicked(View v, Exercise exercise) {
        mExerciseViewModel.getOpenExerciseEvente().setValue(exercise);
    }

}
