package com.example.myapplication;

// 导入必须的包（确保无缺失）

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * 课程表网状布局的RecyclerView适配器
 * 完全适配item_recycler.xml的4个TextView ID，保留网状结构
 */
public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

    // 上下文和课程数据列表（保留你原有逻辑）
    private final Context mContext;
    private final List<Course> mDataList;

    // 构造方法（保留你原有传参逻辑）
    public MyRecycleAdapter(Context context, List<Course> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
    }

    // 创建ViewHolder（加载你的网状布局item_recycler.xml）
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        // 加载你自己的网状布局文件，不改动布局结构
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(itemView);
    }

    // 绑定数据到网状布局的 TextView
    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        // 获取当前位置的课程数据
        Course course = mDataList.get(position);

        // 给网状布局的4个TextView赋值（ID完全匹配你的布局）
        holder.tvCourseName.setText(course.getName());     // 课程名
        holder.tvCourseTime.setText(course.getTime());     // 时间
        holder.tvCourseSite.setText(course.getSite() + " - " + course.getTeacher());
        //格子放不下，隐藏老师，将地点与老师放在一起
        holder.tvCourseTeacher.setVisibility(View.GONE);

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(mContext, "点击课程：" + course.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    // 获取列表长度
    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    // ViewHolder：绑定你网状布局的所有TextView ID（100%匹配）
    static class MyViewHolder extends RecyclerView.ViewHolder {
        // 变量名对应你网状布局的4个TextView ID
        TextView tvCourseName;
        TextView tvCourseTime;
        TextView tvCourseSite;
        TextView tvCourseTeacher;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvCourseName = (TextView) itemView.findViewById(R.id.tv_course_name);
            tvCourseTime = (TextView) itemView.findViewById(R.id.tv_course_time);
            tvCourseSite = (TextView) itemView.findViewById(R.id.tv_course_site);
            tvCourseTeacher = (TextView) itemView.findViewById(R.id.tv_course_teacher);
        }
    }
}
