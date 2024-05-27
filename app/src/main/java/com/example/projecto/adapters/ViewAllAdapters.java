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
import com.example.projecto.models.ViewAllModel;

import java.io.Serializable;
import java.util.List;

/**
 * Adapter class for managing the display of all products in a RecyclerView.
 * This adapter bridges the data in ViewAllModel with the view representation in the RecyclerView.
 */
public class ViewAllAdapters extends RecyclerView.Adapter<ViewAllAdapters.ViewHolder> {
    private Context context;
    private List<ViewAllModel> viewAllModelList;

    /**
     * Constructor for ViewAllAdapters.
     * @param context The current context.
     * @param viewAllModelList The list of all product models to be displayed.
     */
    public ViewAllAdapters(Context context, List<ViewAllModel> viewAllModelList) {
        this.context = context;
        this.viewAllModelList = viewAllModelList;
    }

    /**
     * Inflates the view for each product item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds the View for each product item.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.viewall_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Binds data to the view holder at the specified position in the RecyclerView.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ViewAllModel model = viewAllModelList.get(position);
        Glide.with(context).load(model.getImg_url()).into(holder.imageView);
        holder.name.setText(model.getName());
        holder.gname.setText(model.getGname());
        holder.price.setText(model.getPrice() + " Taka");
        holder.discount.setText(model.getDiscount());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, DetailActivity.class);
            intent.putExtra("detail", (Serializable) model);
            context.startActivity(intent);
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return viewAllModelList.size();
    }

    /**
     * Provides a reference to the type of views that you are using (custom ViewHolder).
     */
    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView imageView;
        TextView name, price, gname, discount;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            imageView = itemView.findViewById(R.id.item_img);
            name = itemView.findViewById(R.id.item_name);
            gname = itemView.findViewById(R.id.item_gname);
            price = itemView.findViewById(R.id.item_price);
            discount = itemView.findViewById(R.id.item_discount);
        }
    }
}
