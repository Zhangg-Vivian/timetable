package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

//主页界面
public class HomeActivity extends AppCompatActivity {

    //与菜单栏响应
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if(item.getItemId() == R.id.add_item) {
            Toast.makeText(this,"添加课程",Toast.LENGTH_SHORT).show();
        }
        else if(item.getItemId() == R.id.remove_item) {
            Toast.makeText(this,"移除课程",Toast.LENGTH_SHORT).show();
        }
        return true;
    }

    //使菜单栏显示出来
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        // EdgeToEdge.enable(this); // 先注释掉，避免影响显示
        setContentView(R.layout.activity_homeactivity);

        //找到第一个添加课程的按钮   button_jump
        Button addBtn =  findViewById(R.id.button_jump);
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
    }

}