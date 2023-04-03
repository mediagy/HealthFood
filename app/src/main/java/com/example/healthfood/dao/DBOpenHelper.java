package com.example.healthfood.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DBOpenHelper extends SQLiteOpenHelper {

    //构造函数，参数1为上下文对象，参数2为数据库名称，参数3用于创建cursor（光标）对象，默认设为null，参数4为数据库的版本
    public DBOpenHelper(@Nullable Context context, @Nullable String name,
                        @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    //在创建数据库时会调用此方法，一般在这个方法中生成数据表
    @Override
    public void onCreate(SQLiteDatabase db) {
        String create_table = "create table if not exists collection" +//如果collection不存在，就创建该表
                "(_id integer primary key autoincrement," +//主键，整型，自动增长
                "name text," +//姓名，文本型
                "comment text," +//评论内容，文本型
                "date datetime," +//日期，日期型
                "image BLOB)";//图片，BLOB型（大量二进制数据）
        db.execSQL(create_table);//执行SQL命令
    }

    //当数据库需要升级时，Android会自动调用此方法，一般在这个方法中删除数据表，并建立新的数据表，并根据需要进行其他操作
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
