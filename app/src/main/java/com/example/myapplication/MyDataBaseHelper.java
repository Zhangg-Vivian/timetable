package com.example.myapplication;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.DatabaseErrorHandler;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class MyDataBaseHelper extends SQLiteOpenHelper {


    public MyDataBaseHelper(Context context) {
        super(context, "CourseDB", null, 1);
    }

    public Context getmContext() {
        return mContext;
    }

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

    //插入数据课程到数据库
    public long insertCourse(String name, String time, String site, String teacher) {
        SQLiteDatabase db = getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ClassName", name);
        cv.put("ClassTime", time);
        cv.put("ClassSite", site);
        cv.put("ClassTeacher", teacher);
        long result = db.insert("Course", null, cv);
        db.close();
        return result;
    }

    private static MyDataBaseHelper instance;

    public static synchronized MyDataBaseHelper getInstance(Context context) {
        if (instance == null) {
            instance = new MyDataBaseHelper(context.getApplicationContext());
        }
        return instance;
    }

    // 查询所有课程，返回一个List<Course>
    public List<Course> getAllCourses() {
        List<Course> courseList = new ArrayList<>();
        SQLiteDatabase db = getReadableDatabase();
        Cursor cursor = db.query("Course", null, null, null, null, null, null);

        if (cursor != null) {
            while (cursor.moveToNext()) {
                String name = cursor.getString(cursor.getColumnIndexOrThrow("ClassName"));
                String time = cursor.getString(cursor.getColumnIndexOrThrow("ClassTime"));
                String site = cursor.getString(cursor.getColumnIndexOrThrow("ClassSite"));
                String teacher = cursor.getString(cursor.getColumnIndexOrThrow("ClassTeacher"));
                courseList.add(new Course(name, time, site, teacher));
            }
            cursor.close();
        }
        db.close();
        return courseList;
    }

}
