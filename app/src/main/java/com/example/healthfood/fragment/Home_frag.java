package com.example.healthfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.example.healthfood.R;
import com.example.healthfood.adapter.HomeVPAdatper;

import java.util.ArrayList;
import java.util.List;

public class Home_frag extends Fragment {

    //定义ViewPager组件，对应着布局文件frag_home.xml中的ViewPager
    private ViewPager viewPager;
    //定义一个列表，用来存放一系列即将填充到ViewPager中的ImageView组件
    private List<ImageView> imageViews=new ArrayList<>();
    //定义一个数组，用来存放多个需要轮播的图片文件的id
    private int[] imageIDs={R.drawable.guanggao1,R.drawable.guanggao2,R.drawable.guanggao3};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        //将res/layout文件夹中的碎片布局frag_home.xml填充到布局caitivity_main.xml中的main_framelayout组件中
        //参见MainActivity.java中的语句 transaction.replace(R.id.main_framelayout,new Home_frag());
        View view=inflater.inflate(R.layout.frag_home,null);
        initViewPager(view);//初始化ViewPager
        return view;
    }

    //初始化ViewPager
    private void initViewPager(View view) {
        //将frag_home.xml文件中id为home_viewPager的组件与本文件的viewPager进行关联
        viewPager=view.findViewById(R.id.home_viewPager);
        for (int i = 0; i < imageIDs.length; i++) {//初始化imageViews列表，循环imageIDs.length次
            ImageView imageView=new ImageView(getActivity());//新建一个新的ImageView控件
            imageView.setImageResource(imageIDs[i]);//设置图片来源于哪个文件
            imageView.setAdjustViewBounds(true);//可调整控件的宽高，以保持内部可绘制对象的原始宽高比例
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//图片缩放到ImageView大小，完全填充整个控件
            imageViews.add(imageView);//将此ImageView控件加入列表中
        }
        //设置ViewPager的适配器为新建的HomeVPAdatper对象，在新建该对象时把需要显示的ImageView列表传进去
        viewPager.setAdapter(new HomeVPAdatper(imageViews));
        //设置展示第一个ImageView
        viewPager.setCurrentItem(0);
    }


}