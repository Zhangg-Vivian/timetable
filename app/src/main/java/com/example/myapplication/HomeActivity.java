package com.example.myapplication;

import android.content.Intent;
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

import java.util.ArrayList;
import java.util.List;

//主页界面
public class HomeActivity extends AppCompatActivity {
    private RecyclerView recyclerView;
    private MyRecycleAdapter adapter;
    private List<ItemData> dataList;


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
        // EdgeToEdge.enable(this); // 先注释掉，避免影响显示
        setContentView(R.layout.activity_homeactivity);

        //找到第一个添加课程的按钮   button_jump
        Button addBtn = findViewById(R.id.button_jump);
        //第二个删除课程的按钮    remove_button
        Button removeBtn = findViewById(R.id.remove_button);

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

        //找到 RecyclerView 控件
        recyclerView = findViewById(R.id.recyclerView);
        //准备测试数据
        initData();

        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 8);
        gridLayoutManager.setOrientation(GridLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(gridLayoutManager);

        //添加网格分割线
        recyclerView.addItemDecoration(new GridItemDecoration(this, 8));

        //创建并设置适配器
        adapter = new MyRecycleAdapter(this, dataList);
        recyclerView.setAdapter(adapter);

        //初始化数据库帮助类
        mdbHelper = new MyDataBaseHelper(this, "CourseTable.db", 1);
        SQLiteDatabase db = mdbHelper.getWritableDatabase();
    }


    // 初始化课程表数据
    private void initData() {
        dataList = new ArrayList<>();
        // 第一行：星期标题（周一到周日）
        dataList.add(new ItemData("", ""));
        dataList.add(new ItemData("周一", ""));
        dataList.add(new ItemData("周二", ""));
        dataList.add(new ItemData("周三", ""));
        dataList.add(new ItemData("周四", ""));
        dataList.add(new ItemData("周五", ""));
        dataList.add(new ItemData("周六", ""));
        dataList.add(new ItemData("周日", ""));

        // 第二行：第一节课程
        dataList.add(new ItemData("9:00-9:40", ""));
        dataList.add(new ItemData("语文", ""));
        dataList.add(new ItemData("数学", ""));
        dataList.add(new ItemData("英语", ""));
        dataList.add(new ItemData("物理", ""));
        dataList.add(new ItemData("化学", ""));
        dataList.add(new ItemData("", ""));
        dataList.add(new ItemData("", ""));

        // 第三行：第二节课程
        dataList.add(new ItemData("10:00-10:40", ""));
        dataList.add(new ItemData("数学", ""));
        dataList.add(new ItemData("语文", ""));
        dataList.add(new ItemData("物理", ""));
        dataList.add(new ItemData("英语", ""));
        dataList.add(new ItemData("生物", ""));
        dataList.add(new ItemData("", ""));
        dataList.add(new ItemData("", ""));

        // 第四行：第三节课程
        dataList.add(new ItemData("11:00-11:40", ""));
        dataList.add(new ItemData("英语", ""));
        dataList.add(new ItemData("物理", ""));
        dataList.add(new ItemData("数学", ""));
        dataList.add(new ItemData("语文", ""));
        dataList.add(new ItemData("历史", ""));
        dataList.add(new ItemData("", ""));
        dataList.add(new ItemData("", ""));
    }

}