package com.example.myapplication;

import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

//添加课程界面
public class AddActivity extends AppCompatActivity {
    private EditText etName, etTeacher, etDay, etTime, etSite;
    private Button btnSave;
    private MyDataBaseHelper db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addactivity);

        //将布局里的输入框和按钮绑定
        etName = findViewById(R.id.et_course_name);
        etTeacher = findViewById(R.id.et_course_teacher);
        etDay = findViewById(R.id.et_course_day);
        etTime = findViewById(R.id.et_course_time);
        etSite = findViewById(R.id.et_course_site);
        btnSave = findViewById(R.id.save_course_btn);

        db = MyDataBaseHelper.getInstance(this);

        //给保存按钮设置点击事件
        btnSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String courseName = etName.getText().toString().trim();
                String teacher = etTeacher.getText().toString().trim();
                String dayStr = etDay.getText().toString().trim();
                String timeStr = etTime.getText().toString().trim();
                String site = etSite.getText().toString().trim();

                //检验输入数据不为空
                if (courseName.isEmpty() || teacher.isEmpty() || dayStr.isEmpty() || timeStr.isEmpty() || site.isEmpty()) {
                    Toast.makeText(AddActivity.this, "请填写所有信息", Toast.LENGTH_SHORT).show();
                    return;
                }


                SQLiteDatabase sqldb = db.getWritableDatabase();
                ContentValues values = new ContentValues();
                values.put("ClassName", courseName);
                values.put("ClassTime", timeStr);
                values.put("ClassSite", site);
                values.put("ClassTeacher", teacher);

                long result = db.insertCourse(courseName, timeStr, site, teacher);
                if (result != -1) {
                    Toast.makeText(AddActivity.this, "课程添加成功", Toast.LENGTH_SHORT).show();
                    finish();
                } else {
                    Toast.makeText(AddActivity.this, "添加失败", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}