package com.example.healthfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthfood.R;

public class Community_frag extends Fragment {


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //调用填充器inflater的inflate方法用id为frag_community的布局文件填充view
        View view = inflater.inflate(R.layout.frag_community, null);
        //返回view
        return view;
    }

}
