package com.example.healthfood.fragment;

import android.app.AlertDialog;
import android.content.ContentValues;
import android.content.DialogInterface;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.healthfood.R;
import com.example.healthfood.adapter.CommunityBaseAdapter;
import com.example.healthfood.dao.DBOpenHelper;

import java.io.ByteArrayOutputStream;
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
    private DBOpenHelper openHelper;//数据库辅助对象

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
        //为mlistview注册上下文菜单
        this.registerForContextMenu(mlistview);
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

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        //增加三个菜单项："微信分享"、"收藏"、"删除"
        menu.add(0, 1, Menu.NONE, "微信分享");
        menu.add(0, 2, Menu.NONE, "收藏");
        menu.add(0, 3, Menu.NONE, "删除");
        //调用父类的同名方法
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    public void dialog() {
        //新建一个对话框mDialog
        AlertDialog.Builder mDialog = new AlertDialog.Builder(getActivity());
        //设置对话框mDialog的标题
        mDialog.setTitle("确认");
        //设置对话框mDialog的图标
        mDialog.setIcon(android.R.drawable.ic_dialog_alert);
        //设置对话框mDialog的信息提示
        mDialog.setMessage("您确认要在微信分享");
        //设置对话框mDialog的“否定”按钮
        mDialog.setNegativeButton("取消", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "您不分享到微信了！", Toast.LENGTH_LONG).show();
            }
        });
        //设置对话框mDialog的“肯定”按钮
        mDialog.setPositiveButton("确定", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getActivity(), "微信分享成功", Toast.LENGTH_LONG).show();
            }
        });
        mDialog.show();
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        //获得上下文菜单的信息
        final AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        switch (item.getItemId()) {//判断点击的是上下文菜单中的哪一个
            case 1:
                //显示一个弹出框
                Toast.makeText(getActivity(), "微信分享", Toast.LENGTH_LONG).show();
                //调用dialog（）方法，显示一个对话框
                dialog();
                break;
            case 2:
                //将当前选中的项目收藏进数据库
                collect(info.position);
                break;
            case 3:
                //显示一个弹出框
                Toast.makeText(getActivity(), "删除", Toast.LENGTH_LONG).show();
                //从list_hashmap列表中删去第position+1个元素
                list_hashmap.remove(info.position);
                //通知适配器：数据已更新，需要重新绘制
                mAdapter.notifyDataSetChanged();
                break;
        }
        return super.onContextItemSelected(item);
    }

    private byte[] PicToBytes(Drawable drawable) {//将drawable对象转成字节数组，以保存到数据库中
        //如果drawable为空，则直接返回null
        if (drawable == null) return null;
        //将drawable转成BitmapDrawable类型
        BitmapDrawable bitmapDrawable = (BitmapDrawable) drawable;
        //从BitmapDrawable转成Bitmap
        Bitmap bitmap = bitmapDrawable.getBitmap();
        //新建一个字节数组输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        //将bitmap以PNG格式压缩输出到字节数组输出流
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, byteArrayOutputStream);
        //返回字节数组输出流中的字节数组
        return byteArrayOutputStream.toByteArray();
    }

    public void collect(int select_index) {
        //新建一个数据库辅助对象
        openHelper = new DBOpenHelper(this.getActivity(), "collection.db", null, 1);
        //获得一个可写的数据库
        SQLiteDatabase mDB = openHelper.getWritableDatabase();
        //新建一个ContentValues对象
        ContentValues contentValues = new ContentValues();

        //获取list_hashmap中第select_index+1个元素中的各个键值对，分别将值存入ContentValues对象中
        contentValues.put("name", list_hashmap.get(select_index).get("username").toString());
        contentValues.put("comment", list_hashmap.get(select_index).get("content").toString());
        contentValues.put("date", list_hashmap.get(select_index).get("time").toString());

        //由于图片不能直接存入数据库，就需要将对应的图片资源转换成二进制数字串，再存入
        //获取list_hashmap中第select_index+1个元素中对应键为"picID"的那个值，该值为图片资源的ID
        int pidID = (int) list_hashmap.get(select_index).get("picID");
        //根据图片资源的ID，获取Drawable对象
        Drawable drawable = this.getResources().getDrawable(pidID, null);
        //将Drawable对象转变为字节数组
        byte[] bytes = PicToBytes(drawable);
        //将字节数组存入contentValues中
        contentValues.put("image", bytes);

        //将contentValues中存入的数据插入数据库的表“collection”中
        long i = mDB.insert("collection", null, contentValues);
        if (i > 0) {//如果插入成功
            Toast.makeText(this.getActivity(), "亲！已收藏！", Toast.LENGTH_LONG).show();
        } else {//如果插入失败
            Toast.makeText(this.getActivity(), "亲！收藏失败！", Toast.LENGTH_LONG).show();
        }
        mDB.close();
    }
}
