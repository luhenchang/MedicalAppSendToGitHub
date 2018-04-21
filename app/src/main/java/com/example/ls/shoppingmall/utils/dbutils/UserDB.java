package com.example.ls.shoppingmall.utils.dbutils;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import org.apache.commons.collections.map.HashedMap;
import org.xutils.DbManager;
import org.xutils.ex.DbException;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by 路很长~ on 2017/12/7.
 */

public class UserDB implements UserServiceInterface {
    private DbOpenHelper helper = null;

    public UserDB(Context context) {
        //这里面去创建数据库和升级降级数据库
        helper = new DbOpenHelper(context);
    }

    /**
     * 这里去添加用户的消息
     *
     * @param params 储存参数
     * @return
     */
    @Override
    public boolean addUser(Object[] params) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            /*ID integer,UserID varchar(64),UserNickName varchar(64),UserName varchar(64),UserHeadImg varchar(64),
            UserPhone varchar(64),UserPassword varchar(64),UserToken varchar(64),UserSex varchar(64),UserWeight varchar(64),
            UserHeight varchar(64));
*/
            String sql = "insert into user(ID,UserID,UserNickName,UserName,UserHeadImg,UserPhone,UserPassword,UserToken,UserSex,UserWeight,UserHeight,UserAge) values(?,?,?,?,?,?,?,?,?,?,?,?)";
            db = helper.getWritableDatabase();
            db.execSQL(sql, params);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return flag;
    }

    /**
     * 在用户退出的时候根据条件来删除用户信息
     *
     * @return
     */
    @Override
    public boolean deleteUser(Object[] params) {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            String sql = "delete from user where ID=?";
            db = helper.getWritableDatabase();
            db.execSQL(sql, params);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return flag;
    }

    @Override
    public boolean updataUser(ContentValues values, String whereClause, String[] whereArgs) {
        boolean flag = false;
        SQLiteDatabase db = null;
        int cont = 0;
        try {
            db = helper.getWritableDatabase();
            cont = db.update("user", values, whereClause, whereArgs);
            flag = (cont > 0 ? true : false);
            // flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }
        return flag;
    }


    /**
     * 根据id来查询用户所有数据
     *
     * @param selectionArgs
     * @return
     */
    @Override
    public Map<String, Object> getUserMessage(String[] selectionArgs) {
        Map<String, Object> map = new HashMap<String, Object>();
        SQLiteDatabase db = null;
        try {
            String sql = "select * from user where ID=?";
            db = helper.getReadableDatabase();
            Cursor cursor = db.rawQuery(sql, selectionArgs);
            int colums = cursor.getColumnCount();
            while (cursor.moveToNext()) {
                for (int i = 0; i < colums; i++) {
                    String cols_name = cursor.getColumnName(i);
                    String cols_vlaue = cursor.getString(cursor.getColumnIndex(cols_name));
                    if (cols_vlaue == null) {
                        cols_vlaue = "";
                    }
                    map.put(cols_name, cols_vlaue);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }
        }

        return map;
    }

    @Override
    public boolean deletUser_id1() {
        boolean flag = false;
        SQLiteDatabase db = null;
        try {
            /*直接删除第一个*/
            String sql = "delete from user where ID = 1";
            db = helper.getWritableDatabase();
            db.execSQL(sql);
            flag = true;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (db != null) {
                db.close();
            }

        }
        return flag;
    }
}
