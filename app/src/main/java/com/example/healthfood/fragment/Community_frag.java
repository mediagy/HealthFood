package com.example.healthfood.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import com.example.healthfood.R;
import com.example.healthfood.adapter.CommunityBaseAdapter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Community_frag extends Fragment {

    private int picIDs[] = {R.drawable.p1_gourmet,
            R.drawable.p2_gourmet,
            R.drawable.p3_gourmet,
            R.drawable.p4_gourmet,
            R.drawable.p5_gourmet,
            R.drawable.p6_gourmet,
            R.drawable.p7_gourmet,
            R.drawable.p8_gourmet};//每一项分享的图片
    private String[] contents = {"减肥干嘛 又不是吃不起",
            "这样的馒头 ，感觉能吃一筐",
            "给你一个爱上烘焙的理由",
            "不是我瘦不下来 是敌人太强",
            "一场咖啡与鲜花的比赛",
            "美食是灵魂伴侣",
            "吃货的幸福世界",
            "美得舍不得下咽"};//每一项分享内容
    private String[] times = {"2016年7月", "2016年9月",
            "2017年1月", "2017年2月",
            "2017年10月", "2018年5月",
            "2018年9月", "2018年10月"};//每一项分享时间
    private String[] usernames = {"叶德娴", "刘芸", "徐自贤 ", "丁志诚 ",
            "梁文道 ", "张笛 ", "杨若兮 ", "王丽达 "};//每一项的分享者
    private Integer[] counts = {101, 201, 301, 401, 501, 601, 701, 801};//每一项的点赞数
    private List<HashMap> list_hashmap = null;//用于存放多项数据
    CommunityBaseAdapter mAdapter;//适配器对象

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //调用填充器inflater的inflate方法用id为frag_community的布局文件填充view
        View view = inflater.inflate(R.layout.frag_community, null);
        initData();//初始化数据，准备好list_hashmap
        //找到view中id为community_listView的组件，与变量mlistview关联
        ListView mlistview = (ListView) view.findViewById(R.id.community_listView);
        //新建一个CommunityBaseAdapter类型的适配器对象mAdapter
        mAdapter = new CommunityBaseAdapter(list_hashmap, this.getActivity());
        //为mlistview设置数据:来自于适配器mAdapter
        mlistview.setAdapter(mAdapter);
        //返回view
        return view;
    }

    //初始化数据
    private void initData() {
        //新建一个ArrayList类型的列表list_hashmap
        list_hashmap = new ArrayList<>();
        //循环picIDs.length次，i分别取值为0,1，...,picIDs.length-1,往list_hashmap中添加picIDs.length个元素
        for (int i = 0; i < picIDs.length; i++) {
            //新建一个哈希表hashmap，键为String（字符串）类型，值为Object（对象）类型
            HashMap hashMap = new HashMap<String, Object>();
            hashMap.put("picID", picIDs[i]);//将一个键值对放入hashmap中，键为“picID”，值为picIDs中的第i+1个元素
            hashMap.put("content", contents[i]);//将一个键值对放入hashmap中，键为“content”，值为contents中的第i+1个元素
            hashMap.put("username", usernames[i]);//将一个键值对放入hashmap中，键为“username”，值为usernames中的第i+1个元素
            hashMap.put("time", times[i]);//将一个键值对放入hashmap中，键为“time”，值为times中的第i+1个元素
            hashMap.put("count", counts[i]);//将一个键值对放入hashmap中，键为“count”，值为counts中的第i+1个元素
            list_hashmap.add(hashMap);//将hashmap放入list_hashmap，成为其第i+1个元素
        }
    }

}











