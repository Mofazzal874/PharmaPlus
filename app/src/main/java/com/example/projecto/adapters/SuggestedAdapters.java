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
import com.example.projecto.activities.DetailActivity2;
import com.example.projecto.models.SuggestedModel;

import java.util.List;
import java.util.zip.Inflater;

public class SuggestedAdapters extends RecyclerView.Adapter<SuggestedAdapters.ViewHolder>{

    private Context context;
    private List<SuggestedModel> suggestedModelList;

    public SuggestedAdapters(Context context, List<SuggestedModel> suggestedModelList) {
        this.context = context;
        this.suggestedModelList = suggestedModelList;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.suggested_item,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Glide.with(context).load(suggestedModelList.get(position).getImg_url()).into(holder.sugImg);
        holder.name.setText(suggestedModelList.get(position).getName());
        holder.description.setText(suggestedModelList.get(position).getDescription());
        holder.gname.setText(suggestedModelList.get(position).getGname());
        holder.discount.setText(suggestedModelList.get(position).getDiscount());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, DetailActivity2.class);
                intent.putExtra("detail", suggestedModelList.get(holder.getAdapterPosition()));
                context.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return suggestedModelList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView sugImg;
        TextView name,description,gname,discount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            sugImg = itemView.findViewById(R.id.sug_img);
            name = itemView.findViewById(R.id.sug_name);
            description = itemView.findViewById(R.id.sug_des);
            gname = itemView.findViewById(R.id.sug_gname);
            discount = itemView.findViewById(R.id.sug_discount);
        }
    }
}
