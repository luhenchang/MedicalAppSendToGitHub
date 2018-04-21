package com.example.ls.shoppingmall.utils.dbutils;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by 路很长~ on 2017/12/7.
 */

public class DbOpenHelper extends SQLiteOpenHelper {
    private static String name = "medical.db";
    private static int version = 1;

    public DbOpenHelper(Context context) {
        super(context, name, null, version);
        // TODO Auto-generated constructor stub
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        /**
         * 这个方法
         * 1、在第一次打开数据库的时候才会走
         * 2、在清除数据之后再次运行-->打开数据库，这个方法会走
         * 3、没有清除数据，不会走这个方法
         * 4、数据库升级的时候这个方法不会走
         */
        /*        Object[] obj_parames = new Object[]{"1", "123456", "路很长", "http://com.image.2.png", "13512219573", userPhone, "Tokens"};
           String sql = "insert into user(ID,UserID,UserNickName,UserHeadImg,UserPhone,UserPassword,UserToken)";

*/
        // TODO Auto-generated method stub
        String sql = "create table IF NOT EXISTS user(ID integer,UserID varchar(64),UserNickName varchar(64),UserName varchar(64),UserHeadImg varchar(64),UserPhone varchar(64),UserPassword varchar(64),UserToken varchar(64),UserSex varchar(64),UserWeight varchar(64),UserHeight varchar(64),UserAge varchar(64))";
        db.execSQL(sql);
        String sql1 = "create table IF NOT EXISTS message(id integer primary key autoincrement,Msg_Id integer,Msg_Type varchar(64),Msg_Content varchar(255),Msg_ItemId integer,Msg_Time varchar(64))";
        db.execSQL(sql1);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        /**
         * 1、第一次创建数据库的时候，这个方法不会走
         * 2、清除数据后再次运行(相当于第一次创建)这个方法不会走
         * 3、数据库已经存在，而且版本升高的时候，这个方法才会调用
         */
        db.execSQL("drop if table exists user");
        db.execSQL("drop if table exists message");
        onCreate(db);
    }
}
