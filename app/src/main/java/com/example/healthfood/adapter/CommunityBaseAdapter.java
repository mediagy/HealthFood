package com.example.healthfood.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import com.example.healthfood.R;
import com.example.healthfood.fragment.ViewHolder;

import java.util.HashMap;
import java.util.List;

public class CommunityBaseAdapter extends BaseAdapter {
    List<HashMap> data;//需要显示出来的数据
    Context context;//上下文（此处指MainActivity）
    ViewHolder viewHolder;//

    //构造函数，参数1为需要显示出来的数据，参数2为上下文（此处指MainActivity）
    public CommunityBaseAdapter(List<HashMap> data, Context context) {
        this.data = data;
        this.context = context;
    }

    @Override
    public int getCount() {
        return data.size();
    }//返回数据的条数，即列表中需显示的项目个数

    @Override
    public Object getItem(int position) {
        return null;
    }//此方法暂不使用，代码可保持不变

    @Override
    public long getItemId(int position) {
        return position;
    }//返回当前点击的项目的序号（从0开始）

    //将每个项目按布局要求填充好
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView==null){//如果convertView为空
            viewHolder=new ViewHolder();//新建一个ViewHolder对象
            LayoutInflater mLayoutInflater= LayoutInflater.from(context);//获取一个布局填充器对象
            //用布局填充器把布局文件community_listview_item.xml中的控件填入convertView中
            convertView=mLayoutInflater.inflate(R.layout.community_listview_item,null);
            //将community_listview_item.xml中的6个控件与viewHolder中的6个成员一一关联
            viewHolder.iv_pic=convertView.findViewById(R.id.community_listView_iv_pic);
            viewHolder.tv_content=convertView.findViewById(R.id.community_listView_tv_content);
            viewHolder.tv_username=convertView.findViewById(R.id.community_listView_tv_username);
            viewHolder.tv_time=convertView.findViewById(R.id.community_listView_tv_time);
            viewHolder.iv_thumbup=convertView.findViewById(R.id.community_listView_iv_thumbup);
            viewHolder.tv_count=convertView.findViewById(R.id.community_listView_tv_count);
            //为convertView设置一个标记：viewHolder
            convertView.setTag(viewHolder);
        }else{
            //获取convertView的标记，赋给viewHolder
            viewHolder= (ViewHolder) convertView.getTag();
        }
        //取得列表data中的第position+1个元素，分别根据不同的key（键）取得对应的object（值），
        //根据这些object（值）设置对应的组件
        viewHolder.iv_pic.setImageResource((Integer) data.get(position).get("picID"));
        viewHolder.tv_content.setText((String)data.get(position).get("content"));
        viewHolder.tv_username.setText((String)data.get(position).get("username"));
        viewHolder.tv_time.setText((String)data.get(position).get("time"));
        viewHolder.tv_count.setText((Integer) data.get(position).get("count")+"");
        //为iv_thumbup组件设置点击事件监听器，点击后改变data列表中对应元素的“count”键值对，将值加1
        // 并通知适配器：数据已更新
        viewHolder.iv_thumbup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //取得列表data中的第position+1个元素，分别根据key（键）"count"取得对应的object（值），存入变量n中
                Integer n=(Integer)data.get(position).get("count");
                //如果n中存的值不为空，就将其值加1，否则将其赋值为0
                if(n!=null) n++;else n=new Integer(0);
                //将n值放入data的第position个元素中，键为“count”
                data.get(position).put("count",n);
                //通知适配器：数据已更新，需要重新绘制
                CommunityBaseAdapter.this.notifyDataSetChanged();
            }
        });
        return convertView;//返回convertView
    }
}
