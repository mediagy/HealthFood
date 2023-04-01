package com.example.healthfood;

import androidx.appcompat.app.AppCompatActivity;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private RadioGroup mRG;//单选按钮组
    private RadioButton mRB1, mRB2, mRB3, mRB4;//四个单选按钮
    private Resources res;//资源对象
    private Drawable icon_home_true, icon_home_false,
            icon_community_true, icon_community_false,
            icon_order_true, icon_order_false,
            icon_me_true, icon_me_false;//八张按钮图片
    private int fontColor_true, fontColor_false;//两种颜色

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();//将布局文件中的单选按钮组和四个单选按钮与本文件中的mRG，mRB1,mRB2,mRB3,mRB4关联起来
        initData();//找到资源文件中定义的8张图片和设置的文字颜色（选中时为#468500，未选中时为#60000000）
        navigation();//实现“导航”功能
    }

    private void initData() {
        res = getResources();//获得Resources资源对象，通过它进一步获得存于系统中的资源
        //找到位于res/mipmap文件夹中的图片icon_home_true.png
        icon_home_true = res.getDrawable(R.drawable.icon_home_true, null);
        //找到位于res/mipmap文件夹中的图片icon_home_false.png
        icon_home_false = res.getDrawable(R.drawable.icon_home_false, null);
        //找到位于res/mipmap文件夹中的图片icon_community_true.png
        icon_community_true = res.getDrawable(R.drawable.icon_community_true, null);
        //找到位于res/mipmap文件夹中的图片icon_community_false.png
        icon_community_false = res.getDrawable(R.drawable.icon_community_false, null);
        //找到位于res/mipmap文件夹中的图片icon_order_true.png
        icon_order_true = res.getDrawable(R.drawable.icon_order_true, null);
        //找到位于res/mipmap文件夹中的图片icon_order_false.png
        icon_order_false = res.getDrawable(R.drawable.icon_order_false, null);
        //找到位于res/mipmap文件夹中的图片icon_me_true.png
        icon_me_true = res.getDrawable(R.drawable.icon_me_true, null);
        //找到位于res/mipmap文件夹中的图片icon_me_false.png
        icon_me_false = res.getDrawable(R.drawable.icon_me_false, null);
        //找到res/values文件夹中colors.xml文件中定义的颜色navigation_false
        fontColor_true = res.getColor(R.color.public_green, null);
        //找到res/values文件夹中colors.xml文件中定义的颜色public_green
        fontColor_false = res.getColor(R.color.navigation_false, null);
     }

    private void initView() {
        //找到布局文件中的单选按钮组
        mRG = (RadioGroup) findViewById(R.id.main_radoiGroup);
        //找到布局文件中的对应着“首页”的单选按钮
        mRB1 = (RadioButton) findViewById(R.id.rb_main_home);
        //找到布局文件中的对应着“吃货驾到”的单选按钮
        mRB2 = (RadioButton) findViewById(R.id.rb_main_community);
        //找到布局文件中的对应着“我的订单”的单选按钮
        mRB3 = (RadioButton) findViewById(R.id.rb_main_order);
        //找到布局文件中的对应着“个人中心”的单选按钮
        mRB4 = (RadioButton) findViewById(R.id.rb_main_me);
    }

    private void navigation() {
        mRG.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                setAllColor();//将按钮文本设为灰色
                setAllImage();//将按钮图片改为灰色图片
                switch (checkedId) {
                    case R.id.rb_main_home:
                        //设置文本颜色为fontColor_true,即绿色
                        mRB1.setTextColor(fontColor_true);
                        //设置按钮上方的图片为icon_home_true，绿色图片
                        mRB1.setCompoundDrawablesWithIntrinsicBounds(null, icon_home_true, null, null);
                        //弹窗提示
                        Toast.makeText(MainActivity.this, "首页", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rb_main_community:
                        mRB2.setTextColor(fontColor_true);
                        mRB2.setCompoundDrawablesWithIntrinsicBounds(null, icon_community_true, null, null);
                        Toast.makeText(MainActivity.this, "吃货驾到", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rb_main_order:
                        mRB3.setTextColor(fontColor_true);
                        mRB3.setCompoundDrawablesWithIntrinsicBounds(null, icon_order_true, null, null);
                        Toast.makeText(MainActivity.this, "我的订单", Toast.LENGTH_LONG).show();
                        break;
                    case R.id.rb_main_me:
                        mRB4.setTextColor(fontColor_true);
                        mRB4.setCompoundDrawablesWithIntrinsicBounds(null, icon_me_true, null, null);
                        Toast.makeText(MainActivity.this, "个人中心", Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }

    private void setAllColor() {//将所有按钮文本设为灰色
        mRB1.setTextColor(fontColor_false);
        mRB2.setTextColor(fontColor_false);
        mRB3.setTextColor(fontColor_false);
        mRB4.setTextColor(fontColor_false);
    }

    private void setAllImage() {//将所有按钮图片改为灰色图片
        mRB1.setCompoundDrawablesWithIntrinsicBounds(null, icon_home_false, null, null);
        mRB2.setCompoundDrawablesWithIntrinsicBounds(null, icon_community_false, null, null);
        mRB3.setCompoundDrawablesWithIntrinsicBounds(null, icon_order_false, null, null);
        mRB4.setCompoundDrawablesWithIntrinsicBounds(null, icon_me_false, null, null);
    }

}
