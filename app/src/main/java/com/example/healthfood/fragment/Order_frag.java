package com.example.healthfood.fragment;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SimpleAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthfood.R;
import com.example.healthfood.adapter.OrderBaseAdapter;
import com.example.healthfood.dao.DBOpenHelper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class Order_frag extends Fragment {
    private int images[] = {R.drawable.po1_hetao, R.drawable.po2_mi,
            R.drawable.po3_jidan, R.drawable.po4_huasheng,
            R.drawable.po5_huajiao};
    private String[] prices = {"￥50   元/公斤 ", "￥10   元/公斤 ", "￥25   元/公斤 ", "￥30  元/公斤 ", "￥100   元/公斤 "};
    private String[] addresses = {"云南大理", "惠州惠东", "惠州农门", "河源和平", "四川汶川"};
    private String[] names = {"核桃", "大米", "鸡蛋", "花生 ", "花椒"};
    ListView mlv_order,mlv_collection;//布局文件中的两个ListView组件
    List orders;//订单列表
    List collections;//收藏列表
    DBOpenHelper dbOpenHelper;//数据库辅助对象
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
//        return super.onCreateView(inflater, container, savedInstanceState);
        //将res/layout文件夹中的碎片布局frag_order.xml填充到布局caitivity_main.xml中的main_framelayout组件中
        //参见MainActivity.java中的语句 transaction.replace(R.id.main_framelayout,new Order_frag());
        View view=inflater.inflate(R.layout.frag_order,null);
        initView(view);
        initData();
        showOrder();
        showCollection();
        return view;
    }

    private void showCollection() {
        getCollection();
        OrderBaseAdapter adapter=new OrderBaseAdapter(collections,this.getActivity());
        mlv_collection.setAdapter(adapter);
    }

    private void showOrder() {
        SimpleAdapter adapter=new SimpleAdapter(this.getActivity(),
                orders,
                R.layout.order_lv_myorder_item,
                new String[]{"image","price","address","name"},
                new int[]{R.id.iv_order_pic,R.id.tv_order_price,R.id.tv_order_address,R.id.tv_order_name});
        mlv_order.setAdapter(adapter);
    }

    private void initData() {
        orders=new ArrayList<HashMap>();
        for (int i = 0; i < images.length; i++) {
            HashMap hm=new HashMap();
            hm.put("image",new Integer(images[i]));
            hm.put("price",prices[i]);
            hm.put("address",addresses[i]);
            hm.put("name",names[i]);
            orders.add(hm);
        }
    }

    private void initView(View view) {
        mlv_order=view.findViewById(R.id.order_lv_MyOrder);
        mlv_collection=view.findViewById(R.id.order_lv_MyCollection);
    }

    private void getCollection(){
        //新建一个列表，用来存放收藏内容
        collections=new ArrayList<HashMap<String,Object>>();
        //新建一个数据库辅助对象
        dbOpenHelper=new DBOpenHelper(this.getActivity(), "collection.db", null, 1);
        //获得一个可读的数据库
        SQLiteDatabase mDB=dbOpenHelper.getReadableDatabase();
        //新建一个cursor（光标）对象，获取表collection中的全部数据
        Cursor cursor=mDB.query("collection",null,null,null,
                null,null,null);
        //如果从表中查询出来的数据条数大于0
        if(cursor!=null&&cursor.getCount()>0){
            //逐条处理
            while(cursor.moveToNext()){
                //新建一个HashMap对象
                HashMap<String,Object> hm=new HashMap<>();

                //从cursor中获取“name"字段的值
                String name=cursor.getString(cursor.getColumnIndex("name"));
                //将其放入hm，键为“name”
                hm.put("name",name);

                String comment=cursor.getString(cursor.getColumnIndex("comment"));
                hm.put("comment",comment);

                String date=cursor.getString(cursor.getColumnIndex("date"));
                hm.put("date",date);

                byte[] bytes=cursor.getBlob(cursor.getColumnIndex("image"));
                Bitmap bitmap= BitmapFactory.decodeByteArray(bytes,0,bytes.length);
                Drawable drawable=new BitmapDrawable(null,bitmap);
                hm.put("image",drawable);

                //将hm放入列表对象colletions中
                collections.add(hm);
            }
        }

    }

}
