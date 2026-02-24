package com.example.myapplication;

import android.content.Context;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class MyDataBaseHelper extends SQLiteOpenHelper {

    //创建建表语句
    public static final String CREATE_BOOK = "create table Course("
            + "id integer primary key autoincrement,"
            + "ClassName text,"
            + "ClassTime text,"
            + "ClassSite text,"
            + "ClassTeacher text);";


    private Context mContext;

    public MyDataBaseHelper(Context c, String s, int i) {
        super(c, s, null, i);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("drop table if exists Course");
        onCreate(db);
    }

    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, Context mContext) {
        super(context, name, null, version);
        this.mContext = context;
    }

    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version, @Nullable DatabaseErrorHandler errorHandler, Context mContext) {
        super(context, name, factory, version, errorHandler);
        this.mContext = context;
    }

    public MyDataBaseHelper(@Nullable Context context, @Nullable String name, int version, @NonNull SQLiteDatabase.OpenParams openParams, Context mContext) {
        super(context, name, null, version);
        this.mContext = context;
    }

    public class DBManager {
        private MyDataBaseHelper dbHelper;
        private SQLiteDatabase db;

        //再创建一个内部类
        public DBManager(Context context) {
            dbHelper = new MyDataBaseHelper(context, null, 0);
        }
    }


    //增加  增，删，查，改功能
    //插入课程

}
