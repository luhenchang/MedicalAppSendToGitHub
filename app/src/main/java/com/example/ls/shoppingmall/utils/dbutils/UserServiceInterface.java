package com.example.ls.shoppingmall.utils.dbutils;

import android.content.ContentValues;

import java.util.Map;

/**
 * Created by 路很长~ on 2017/12/7.
 */

public interface UserServiceInterface {
    //用于添加用户的信息到数据库里面
    public boolean addUser(Object[] params);

    //用于杀出用户的信息根据条件
    public boolean deleteUser(Object[] params);
    //更新用户信息
    public boolean updataUser(ContentValues values,String whereClause,String[] whereArgs);
    //用于获取用户的所有信息
    public Map<String, Object> getUserMessage(String[] selectionArgs);

    //根据id=1来删除用户的信息
    public boolean deletUser_id1();
}
