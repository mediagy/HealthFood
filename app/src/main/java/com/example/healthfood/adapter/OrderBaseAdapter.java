package com.example.healthfood.adapter;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import com.example.healthfood.R;
import com.example.healthfood.fragment.ViewHolder;
import java.util.HashMap;
import java.util.List;

public class OrderBaseAdapter extends BaseAdapter {
    List<HashMap<String,Object>> data;//存放数据
    Context mContext;//上下文
    ViewHolder viewHolder;//用来标记组件，该组件显示收藏项目

    public OrderBaseAdapter(List<HashMap<String, Object>> data, Context mContext) {
        this.data = data;
        this.mContext = mContext;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            viewHolder=new ViewHolder();
            LayoutInflater inflater=LayoutInflater.from(mContext);
            convertView=inflater.inflate(R.layout.order_lv_mycollection_item,null);
            viewHolder.iv_pic=(ImageView)convertView.findViewById(R.id.iv_collection_image);//iv_collection_image
            viewHolder.tv_username=(TextView)convertView.findViewById(R.id.tv_collection_username);
            viewHolder.tv_content=(TextView)convertView.findViewById(R.id.tv_collection_comment);
            viewHolder.tv_time=(TextView)convertView.findViewById(R.id.tv_collection_date);
            convertView.setTag(viewHolder);
        }else{
            viewHolder = (ViewHolder) convertView.getTag();
        }

        viewHolder.iv_pic.setImageDrawable((Drawable) data.get(position).get("image"));
        viewHolder.tv_content.setText(data.get(position).get("comment").toString());
        viewHolder.tv_username.setText(data.get(position).get("name").toString());
        viewHolder.tv_time.setText(data.get(position).get("date").toString());
        return convertView;
    }
}
