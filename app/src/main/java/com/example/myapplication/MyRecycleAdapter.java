package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyRecycleAdapter extends RecyclerView.Adapter<MyRecycleAdapter.MyViewHolder> {

    private final List<Course> mDataList;
    private final Context mContext;

    public MyRecycleAdapter(Context context, List<Course> dataList) {
        this.mContext = context;
        this.mDataList = dataList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext)
                .inflate(R.layout.item_recycler, parent, false);
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {
        Course course = mDataList.get(position);
        holder.tvTitle.setText(course.getName());
        holder.tvContent.setText(course.getTime() + " | " + course.getSite());

        holder.itemView.setOnClickListener(v -> {
            Toast.makeText(holder.itemView.getContext(), "点击了：" + course.getName(), Toast.LENGTH_SHORT).show();
        });
    }

    @Override
    public int getItemCount() {
        return mDataList == null ? 0 : mDataList.size();
    }

    // 内部类 MyViewHolder：必须定义在适配器内部，与泛型引用对应
    static class MyViewHolder extends RecyclerView.ViewHolder {
        TextView tvTitle;
        TextView tvContent;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvContent = itemView.findViewById(R.id.tv_content);
        }
    }
}
