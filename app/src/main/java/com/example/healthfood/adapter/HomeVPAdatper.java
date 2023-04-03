package com.example.healthfood.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class HomeVPAdatper extends PagerAdapter {
    List<ImageView> imageViews;//用来存储“首页”上部需要轮播的那些ImageView

    //构造函数，用参数imageViews来给本实例中的imageViews变量赋值
    public HomeVPAdatper(List<ImageView> imageViews) {
        this.imageViews = imageViews;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
//        return super.instantiateItem(container, position);
        container.addView(imageViews.get(position));//添加imageViews列表中的第position+1个ImageView（图片框）
        return imageViews.get(position);//返回一个Object对象，也就是position位置添加上的ImageView（图片框）
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
//        super.destroyItem(container, position, object);
        container.removeView(imageViews.get(position));//移除imageViews列表中的第position+1个图片框
    }

    @Override
    public int getCount() {
        return imageViews.size();//返回imageViews列表中图片框的个数
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;//判断view和object是不是一个对象
    }
}
