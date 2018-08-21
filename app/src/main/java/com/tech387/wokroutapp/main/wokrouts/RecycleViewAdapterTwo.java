package com.tech387.wokroutapp.main.wokrouts;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import com.tech387.wokroutapp.data.storage.local.workout.Workout;
import com.tech387.wokroutapp.databinding.WorkoutListBinding;

import java.util.ArrayList;
import java.util.List;

public class RecycleViewAdapterTwo extends RecyclerView.Adapter {

    private List<Workout> mList = new ArrayList<>();
    private LayoutInflater mInflater;

    public RecycleViewAdapterTwo(Context context) {
        mInflater = LayoutInflater.from(context);
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


    public void setWorkout(List<Workout> list) {
        mList.clear();
        mList.addAll(list);
        notifyDataSetChanged();
    }
}