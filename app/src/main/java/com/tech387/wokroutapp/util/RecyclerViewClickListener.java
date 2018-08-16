package com.tech387.wokroutapp.util;

import android.view.View;

import com.tech387.wokroutapp.data.storage.local.exercise.Exercise;

public interface RecyclerViewClickListener {

    public void recyclerViewListClicked(View v, Exercise exercise);

}
