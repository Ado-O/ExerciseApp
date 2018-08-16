package com.tech387.wokroutapp.main.exercises;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import com.tech387.wokroutapp.databinding.ExerciseListBinding;

import com.tech387.wokroutapp.data.Exercise;
import com.tech387.wokroutapp.util.RecyclerViewClickListener;

public class ExerciseViewHolder extends RecyclerView.ViewHolder {

    private RecyclerViewClickListener mListener;
    private ExerciseListBinding mBinding;

    public ExerciseViewHolder(@NonNull ExerciseListBinding binding, final RecyclerViewClickListener listener) {
        super(binding.getRoot());

        mBinding = binding;
        mBinding.setListener(listener);
    }

    public void setup(Exercise exercise) {

        mBinding.setExercise(exercise);

    }

}