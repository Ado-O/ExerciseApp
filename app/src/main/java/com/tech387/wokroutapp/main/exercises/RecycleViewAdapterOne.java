package com.tech387.wokroutapp.main.exercises;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tech387.wokroutapp.data.storage.local.exercise.Exercise;
import com.tech387.wokroutapp.databinding.ExerciseListBinding;
import com.tech387.wokroutapp.util.RecyclerViewClickListener;

import java.util.List;

public class RecycleViewAdapterOne extends RecyclerView.Adapter {


    private List<Exercise> mList;
    private LayoutInflater mInflater;
    private RecyclerViewClickListener mListener;
    private Context mContext;

    public RecycleViewAdapterOne(Context context, List<Exercise> list, RecyclerViewClickListener listener) {
        mInflater = LayoutInflater.from(context);
        mList = list;
        mListener = listener;

        mContext = context;

    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ExerciseViewHolder(ExerciseListBinding.inflate(mInflater, parent, false), mListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((ExerciseViewHolder) holder).setup(mList.get(position));

    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}

