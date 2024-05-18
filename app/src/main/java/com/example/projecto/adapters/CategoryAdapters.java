package com.example.projecto.adapters;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.projecto.R;
import com.example.projecto.activities.ViewAllActivity;
import com.example.projecto.models.CategoryModel;

import java.util.List;

public class CategoryAdapters extends RecyclerView.Adapter<CategoryAdapters.ViewHolder> {

    Context context;
    List<CategoryModel> categoryModelList;

    public CategoryAdapters(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    @NonNull
    @Override
    public CategoryAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryAdapters.ViewHolder holder, int position) {
        Glide.with(context).load(categoryModelList.get(position).getImg_url()).into(holder.catImg);
        holder.name.setText(categoryModelList.get(position).getName());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ViewAllActivity.class);
                intent.putExtra("type",categoryModelList.get(holder.getAdapterPosition()).getType());
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView catImg;
        TextView name;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            catImg = itemView.findViewById(R.id.cat_img);
            name = itemView.findViewById(R.id.cat_name);
        }
    }
}
