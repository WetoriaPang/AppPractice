package com.jeffrey.apppractice.view.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.jeffrey.apppractice.R;
import com.jeffrey.apppractice.view.BaseFragment;

/**
 * Created by pangjiaqi on 2017/4/15.
 */

public class MessageFragment extends BaseFragment {

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_message_layout, container, false);
        return view;
    }
}
