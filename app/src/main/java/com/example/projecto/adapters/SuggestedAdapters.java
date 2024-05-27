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
import com.example.projecto.activities.DetailActivity2;
import com.example.projecto.models.SuggestedModel;

import java.util.List;

/**
 * Adapter class for displaying suggested products in a RecyclerView.
 * This class adapts SuggestedModel data into viewable items, implementing the Adapter design pattern.
 */
public class SuggestedAdapters extends RecyclerView.Adapter<SuggestedAdapters.ViewHolder>{

    private Context context;
    private List<SuggestedModel> suggestedModelList;

    /**
     * Constructs the SuggestedAdapters instance.
     * @param context The current context.
     * @param suggestedModelList The data model list of suggested products.
     */
    public SuggestedAdapters(Context context, List<SuggestedModel> suggestedModelList) {
        this.context = context;
        this.suggestedModelList = suggestedModelList;
    }

    /**
     * Inflates the layout for each item of the RecyclerView.
     * @param parent The ViewGroup into which the new view will be added after it is bound to an adapter position.
     * @param viewType The view type of the new view.
     * @return A new ViewHolder that holds the View for each item.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.suggested_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Binds the data to the ViewHolder in each position of the RecyclerView.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        SuggestedModel model = suggestedModelList.get(position);
        Glide.with(context).load(model.getImg_url()).into(holder.sugImg);
        holder.name.setText(model.getName());
        holder.description.setText(model.getDescription());
        holder.gname.setText(model.getGname());
        holder.discount.setText(model.getDiscount());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity2.class);
            intent.putExtra("detail", model);
            context.startActivity(intent);
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return suggestedModelList.size();
    }

    /**
     * Provides a reference to the type of views that you are using (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView sugImg;
        TextView name, description, gname, discount;

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
