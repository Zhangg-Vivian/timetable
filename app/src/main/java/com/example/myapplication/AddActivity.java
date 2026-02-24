package com.example.myapplication;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AppCompatActivity;

//添加课程界面
public class AddActivity extends AppCompatActivity {
    private EditText etName, etTeacher, etDay, etTime, etSite;
    private Button btnSave;
    private MyDataBaseHelper dbh;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);

        //将布局里的输入框和按钮绑定


    }
}