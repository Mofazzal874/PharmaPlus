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
import com.example.projecto.activities.ViewAllActivity;
import com.example.projecto.models.CategoryModel;

import java.util.List;

/**
 * Adapter class for displaying categories in a RecyclerView.
 * This adapter manages the binding of CategoryModel data into the UI elements within a RecyclerView.
 */
public class CategoryAdapters extends RecyclerView.Adapter<CategoryAdapters.ViewHolder> {

    private Context context;
    private List<CategoryModel> categoryModelList;

    /**
     * Constructor for CategoryAdapters.
     * @param context The current context.
     * @param categoryModelList The list of categories to be displayed.
     */
    public CategoryAdapters(Context context, List<CategoryModel> categoryModelList) {
        this.context = context;
        this.categoryModelList = categoryModelList;
    }

    /**
     * Inflates the view for each category item.
     * @param parent The ViewGroup into which the new View will be added after it is bound to an adapter position.
     * @param viewType The view type of the new View.
     * @return A new ViewHolder that holds the View for each category item.
     */
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.category_item, parent, false);
        return new ViewHolder(itemView);
    }

    /**
     * Binds data to the view holder at the specified position in the RecyclerView.
     * @param holder The ViewHolder which should be updated to represent the contents of the item at the given position in the data set.
     * @param position The position of the item within the adapter's data set.
     */
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        CategoryModel model = categoryModelList.get(position);
        Glide.with(context).load(model.getImg_url()).into(holder.catImg);
        holder.name.setText(model.getName());

        holder.itemView.setOnClickListener(v -> {
            Intent intent = new Intent(context, ViewAllActivity.class);
            intent.putExtra("type", model.getType());
            context.startActivity(intent);
        });
    }

    /**
     * Returns the total number of items in the data set held by the adapter.
     * @return The total number of items in this adapter.
     */
    @Override
    public int getItemCount() {
        return categoryModelList.size();
    }

    /**
     * Provides a reference to the type of views that you are using (custom ViewHolder).
     */
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
