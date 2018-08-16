package com.tech387.wokroutapp.main.other;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.tech387.wokroutapp.R;

public class ThreeFragment extends Fragment{


    public static ThreeFragment newInstance() {
        return new ThreeFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.main_three_frag, container, false);

        return view;
    }
}
