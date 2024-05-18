package com.example.projecto.adapters;

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
import com.example.projecto.activities.DetailActivity;
import com.example.projecto.activities.ViewAllActivity;
import com.example.projecto.models.ViewAllModel;

import java.io.Serializable;
import java.util.List;

public class ViewAllAdapters extends RecyclerView.Adapter<ViewAllAdapters.ViewHolder> {

    Context context;
    List<ViewAllModel> viewAllModelList;

    public ViewAllAdapters(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    @NonNull
    @Override
    public ViewAllAdapters.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.viewall_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewAllAdapters.ViewHolder holder, int position) {
        Glide.with(context).load(viewAllModelList.get(position).getImg_url()).into(holder.imageView);
        holder.name.setText(viewAllModelList.get(position).getName());
        holder.gname.setText(viewAllModelList.get(position).getGname());
        holder.price.setText(viewAllModelList.get(position).getPrice()+" Taka");
        holder.discount.setText(viewAllModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity.class);
                intent.putExtra("detail", viewAllModelList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView imageView;
        TextView name, price, gname, discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_name);
            gname = itemView.findViewById(R.id.item_gname);
            discount = itemView.findViewById(R.id.item_discount);
            price = itemView.findViewById(R.id.item_price);
        }
    }
}
