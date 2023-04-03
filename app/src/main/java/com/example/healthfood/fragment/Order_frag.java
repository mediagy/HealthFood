package com.example.healthfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthfood.R;

public class Order_frag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //将res/layout文件夹中的碎片布局frag_order.xml填充到布局caitivity_main.xml中的main_framelayout组件中
        //参见MainActivity.java中的语句 transaction.replace(R.id.main_framelayout,new Order_frag());
        View view = inflater.inflate(R.layout.frag_order, null);
        return view;
    }


}
