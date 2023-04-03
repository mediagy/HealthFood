package com.example.healthfood.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthfood.R;

public class PersonalCenter_frag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //R.layout.frag_personalcenter是指res/layout文件夹中的frag_personalcenter.xml布局文件
        View view=inflater.inflate(R.layout.frag_personalcenter,null);
        return view;
    }
}
