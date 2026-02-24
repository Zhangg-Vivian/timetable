package com.example.myapplication;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

//主页界面
public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecycleAdapter adapter;
    private List<Course> dataList;


    //创建数据库相关
    private MyDataBaseHelper mdbHelper;


    //与菜单栏响应
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.add_item) {
            Toast.makeText(this, "添加课程", Toast.LENGTH_SHORT).show();
        } else if (item.getItemId() == R.id.remove_item) {
            Toast.makeText(this, "移除课程", Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //使菜单栏显示出来
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.HONEYCOMB) {
            getWindow().setFlags(
                    android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED,
                    android.view.WindowManager.LayoutParams.FLAG_HARDWARE_ACCELERATED
            );
        }
        // EdgeToEdge.enable(this); // 先注释掉，避免影响显示
        setContentView(R.layout.activity_homeactivity);
        mdbHelper = new MyDataBaseHelper(this, "CourseTable.db", 1);
        //绑定所有按钮
        Button addBtn = findViewById(R.id.button_jump);
        //找到 RecyclerView 控件
        recyclerView = findViewById(R.id.recyclerView);

        //初始化列表
        dataList = new ArrayList<>();


        //布局出八列，七天还有第一列时间
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 5);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);


        //创建并设置适配器
        adapter = new MyRecycleAdapter(this, dataList);
        recyclerView.setAdapter(adapter);


        //添加网格分割线
        recyclerView.addItemDecoration(new GridItemDecoration(this, 5));


        //初始化数据库帮助类
        mdbHelper = MyDataBaseHelper.getInstance(this);

        queryCourse();
        //调用网络请求
        simpleNetRequest();


        //设置点击事件，实现跳转
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 点击时先弹出提示，确认事件触发
                Toast.makeText(HomeActivity.this, "跳转到添加课程", Toast.LENGTH_SHORT).show();
                // 执行跳转
                Intent intent = new Intent(HomeActivity.this, AddActivity.class);
                startActivity(intent);
            }
        });


    }


    public void queryCourse() {
        if (mdbHelper == null || adapter == null) {
            return;
        }
        SQLiteDatabase db = mdbHelper.getWritableDatabase();
        //清空旧数据
        dataList.clear();
        Cursor cursor = db.query("Course", null, null, null, null, null, null);
        //把查到的数据取出来
        if (cursor != null) {
            if (cursor.moveToFirst()) {
                do {
                    String name = cursor.getString(cursor.getColumnIndexOrThrow("ClassName"));
                    String time = cursor.getString(cursor.getColumnIndexOrThrow("ClassTime"));
                    String site = cursor.getString(cursor.getColumnIndexOrThrow("ClassSite"));
                    String teacher = cursor.getString(cursor.getColumnIndexOrThrow("ClassTeacher"));
                    //添加到列表
                    dataList.add(new Course(name, time, site, teacher));
                } while (cursor.moveToNext());
            }
            cursor.close();
        }
        db.close();
        adapter.notifyDataSetChanged();
        Toast.makeText(this, "已加载" + dataList.size() + "门课程", Toast.LENGTH_SHORT).show();
    }

    //网络请求
    private void simpleNetRequest() {
        // 网络请求必须在子线程执行
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    // 访问任意可联网的地址
                    URL url = new URL("https://www.baidu.com");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    //GET 请求
                    conn.setRequestMethod("GET");
                    // 5秒超时
                    conn.setConnectTimeout(5000);
                    // 建立连接
                    conn.connect();


                    if (conn.getResponseCode() == 200) {
                        // 切回主线程更新UI（安卓规定）
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                dataList.add(new Course("网络课程", "10:00-11:00", "网络教室", "王老师"));
                                // 刷新显示
                                adapter.notifyDataSetChanged();
                            }
                        });
                    }
                    // 关闭连接
                    conn.disconnect();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }

    //删除课程
    public void removeClass(String name) {
        if (mdbHelper == null) {
            return;
        }
        SQLiteDatabase db = mdbHelper.getWritableDatabase();
        db.delete("Course", "ClassName=?", new String[]{name});
        db.close();
        //加一个提示
        Toast.makeText(this, "已删除【" + name + "】", Toast.LENGTH_SHORT).show();
        //刷新
        queryCourse();
    }

    //按照课程名字修改
    public void recomposeClass(String oldName, String newTime, String newSite, String newTeacher) {
        if (mdbHelper == null) {
            return;
        }
        SQLiteDatabase db = mdbHelper.getWritableDatabase();
        ContentValues cv = new ContentValues();
        cv.put("ClassTime", newTime);
        cv.put("ClassSite", newSite);
        cv.put("ClassTeacher", newTeacher);
        db.update("Course", cv, "ClassName = ?", new String[]{oldName});
        db.close();
        //加一个提示
        Toast.makeText(this, "已修改【" + oldName + "】的信息", Toast.LENGTH_SHORT).show();
        //刷新列表
        queryCourse();
    }

    @Override
    protected void onResume() {
        super.onResume();
        queryCourse();
    }
}