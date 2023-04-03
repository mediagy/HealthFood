package com.example.healthfood.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthfood.LoginActivity;
import com.example.healthfood.R;

public class PersonalCenter_frag extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //R.layout.frag_personalcenter是指res/layout文件夹中的frag_personalcenter.xml布局文件
        View view=inflater.inflate(R.layout.frag_personalcenter,null);
        jumplogin(view);//跳转到LoginActivity中
        return view;
    }

    //跳转到LoginActivity中
    public void jumplogin(View v) {
        //找到布局文件frag_personalcenter.xml中的TextView“待君登录”，与变量tv_login关联
        TextView tv_login = v.findViewById(R.id.tv_jumplogin);
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //创建一个Intent类型变量intent（显式“意图”），准备从当前Activity跳转到LoginActivity（登录界面）
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                //开始新的Activity
                startActivity(intent);
            }
        });
    }
}
