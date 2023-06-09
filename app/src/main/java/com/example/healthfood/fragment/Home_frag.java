package com.example.healthfood.fragment;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import com.example.healthfood.R;
import com.example.healthfood.adapter.HomeVPAdatper;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Home_frag extends Fragment {

    //定义ViewPager组件，对应着布局文件frag_home.xml中的ViewPager
    private ViewPager viewPager;
    //定义一个列表，用来存放一系列即将填充到ViewPager中的ImageView组件
    private List<ImageView> imageViews=new ArrayList<>();
    //定义一个数组，用来存放多个需要轮播的图片文件的id
    private int[] imageIDs={R.drawable.guanggao1,R.drawable.guanggao2,R.drawable.guanggao3};
    //定义LinearLayout组件，对应布局文件frag_home.xml中的LinearLayout
    private LinearLayout ll_tv_dot;
    //定义一个线性布局参数
    private LinearLayout.LayoutParams layoutParams;
    //定义一个TextView，用来显示一个小圆点
    private TextView tv_dot;
    //定义一个列表，用来存放一系列的TextView
    private List<TextView> tv_dots=new ArrayList<>();
    //定义一个线程变量mThread，用以开启一个并行任务，定时发送消息
    private Thread mThread;
    //定义一个Handler变量，用来处理消息
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(@NonNull Message msg) {//此处重写父类的handleMessage方法，定义处理消息的流程
//            super.handleMessage(msg);
            switch (msg.what){//判断消息中的what参数值
                case 1://如果消息中的what参数值为数值1，则将viewPager组件的当前控件变动一下，即显示下一张图片
                    viewPager.setCurrentItem((viewPager.getCurrentItem()+1)%imageIDs.length);
                    break;
            }
        }
    };
    //定义GridView组件，对应着布局文件frag_home.xml中的GridView
    private GridView gridView;
    //定义一个字符串数组，用来存放首页下方网格中需要显示的图片标题
    private String[] iconTitles={"新品驾到", "食趣", "食品安全", "产品溯源", "健康养生", "产品展示"};
    //定义一个整型数组，用来存放首页下方网格中需要显示的图片文件的id
    private int[] iconIDs={R.drawable.no1, R.drawable.no2, R.drawable.no3,
            R.drawable.no4, R.drawable.no5, R.drawable.no6};

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);

        //将res/layout文件夹中的碎片布局frag_home.xml填充到布局caitivity_main.xml中的main_framelayout组件中
        //参见MainActivity.java中的语句 transaction.replace(R.id.main_framelayout,new Home_frag());
        View view=inflater.inflate(R.layout.frag_home,null);
        initViewPager(view);//初始化ViewPager
        initGridView(view);//初始化GridView
        return view;
    }
    //初始化GridView
    private void initGridView(View view) {
        //将frag_home.xml文件中id为home_gridView的组件与本文件的gridView进行关联
        gridView=view.findViewById(R.id.home_gridView);
        //定义一个列表items，成员类型为HashMap（哈希表，键为字符串类型，值为对象类型）
        List<HashMap<String,Object>> items=new ArrayList();
        for (int i = 0; i < iconIDs.length; i++) {
            HashMap<String,Object> hashMap=new HashMap<>();//新建一个Hashmap对象
            //在hashMap中放入一个键值对，键为“iconID”，值为数组iconID中的第i+1个值
            hashMap.put("iconID",iconIDs[i]);
            //在hashMap中放入一个键值对，键为“title”，值为数组iconTitles中的第i+1个值
            hashMap.put("title",iconTitles[i]);
            items.add(hashMap);//在列表items中加入前面创建好的hashMap
        }
        //创建一个SimpleAdapter对象
        SimpleAdapter adapter=new SimpleAdapter(getActivity(),
                items,//列表items，里面存放着多个哈希表，哈希表中有需要显示的图片及标题
                R.layout.home_gridview_item,//此为布局文件homg_gridview.xml
                new String[]{"iconID","title"},//items里成员hashMap中的两个键
                new int[]{R.id.home_gridView_iv_icon,R.id.home_gridView_tv_title});//布局文件homg_gridview_item.xml中两控件的id
        gridView.setAdapter(adapter);//为gridView设置适配器为adapter
        //为gridView设置选项点击监听器
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position){
                    case 0:
                        Toast.makeText(getActivity(),"新品驾到", Toast.LENGTH_LONG).show();
                        break;
                    case 1:
                        Toast.makeText(getActivity(),"食趣",Toast.LENGTH_LONG).show();
                        break;
                    case 2:
                        Toast.makeText(getActivity(),"食品安全",Toast.LENGTH_LONG).show();
                        break;
                    case 3:
                        Toast.makeText(getActivity(),"产品溯源",Toast.LENGTH_LONG).show();
                        break;
                    case 4:
                        Toast.makeText(getActivity(),"健康养生",Toast.LENGTH_LONG).show();
                        break;
                    case 5:
                        Toast.makeText(getActivity(),"产品展示",Toast.LENGTH_LONG).show();
                        break;
                }
            }
        });
    }
    //初始化ViewPager
    private void initViewPager(View view) {
        //将frag_home.xml文件中id为home_viewPager的组件与本文件的viewPager进行关联
        viewPager=view.findViewById(R.id.home_viewPager);
        //将frag_home.xml文件中id为home_ll_dot的组件与本文件的ll_tv_dot进行关联
        ll_tv_dot=view.findViewById(R.id.home_ll_dot);
        layoutParams=new LinearLayout.LayoutParams(20,20);//定义布局的宽和高都为20
        layoutParams.setMargins(5,10,5,10);//定义布局的左、上、右、下方填充空白的大小

        for (int i = 0; i < imageIDs.length; i++) {//初始化imageViews列表，循环imageIDs.length次
            ImageView imageView=new ImageView(getActivity());//新建一个新的ImageView控件
            imageView.setImageResource(imageIDs[i]);//设置图片来源于哪个文件
            imageView.setAdjustViewBounds(true);//可调整控件的宽高，以保持内部可绘制对象的原始宽高比例
            imageView.setScaleType(ImageView.ScaleType.FIT_XY);//图片缩放到ImageView大小，完全填充整个控件
            imageViews.add(imageView);//将此ImageView控件加入列表中
            tv_dot=new TextView(getActivity());//新建一个新的TextView控件
            //把TextView控件的背景设为tv_dot_selector.xml中设置的样式
            tv_dot.setBackgroundResource(R.drawable.tv_dot_selector);
            ll_tv_dot.addView(tv_dot,layoutParams);//将tv_dot加入到线性布局ll_tv_dot中，用以显示出来
            tv_dots.add(tv_dot);//将tv_dot加入到列表tv_dots中，好与列表imageViews中的ImageView控件一一对应
        }
        //设置ViewPager的适配器为新建的HomeVPAdatper对象，在新建该对象时把需要显示的ImageView列表传进去
        viewPager.setAdapter(new HomeVPAdatper(imageViews));
        //设置展示第一个ImageView
        viewPager.setCurrentItem(0);
        //将第一个TextView（小圆点）设为被选中的状态
        tv_dots.get(0).setSelected(true);
        //为viewPager控件添加页面滑动相应事件，用以在改变首页上方图片时也改变小圆点的被选中状态
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {//当viewPager中某一页被选中（被显示出来）时，回调此方法
                //将所有小圆点都设为未选中状态
                for (int i = 0; i < imageIDs.length; i++) {
                    tv_dots.get(i).setSelected(false);
                }
                //将当前图片对应的小圆点设为选中状态
                tv_dots.get(position).setSelected(true);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }
    @Override
    public void onResume() {//Home_frag类恢复到前台运行时，此方法被调用
        //为线程对象mThread赋值，覆写父类的run方法
        mThread=new Thread(){
            @Override
            public void run() {//设置此线程的任务流程：每隔若干秒，就向主线程发送一个what值为1的消息
                while (!Thread.interrupted()){//如果本线程未被中断，则循环执行{}中的语句
                    try {
                        Thread.sleep(3000);//等待3秒
                        handler.sendEmptyMessage(1);//发送一个消息，what参数为数值1
                    }catch (InterruptedException e){//如果本线程被中断
                        break;//跳出while循环
                    }
                }
            }
        };
        mThread.start();//开启mThread线程，即开启一个并行任务
        super.onResume();
    }

    @Override
    public void onStop() {//Home_frag类转入后台，停止运行时，此方法被调用
        mThread.interrupt();//中断mThread线程
        super.onStop();
    }
}