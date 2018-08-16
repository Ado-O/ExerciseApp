package com.tech387.wokroutapp.main.wokrouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tech387.wokroutapp.data.Workout;
import com.tech387.wokroutapp.databinding.WorkoutListBinding;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterTwo extends RecyclerView.Adapter {

    private List<Workout> mList;
    private LayoutInflater mInflater;

    public RecycleViewAdapterTwo(Context context, List<Workout> list) {
        mInflater = LayoutInflater.from(context);
        mList = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new WorkoutViewHolder(WorkoutListBinding.inflate(mInflater, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((WorkoutViewHolder)holder).setup(mList.get(position));
    }

    @Override
    public int getItemCount() {
        return mList.size();
    }
}