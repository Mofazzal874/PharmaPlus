package com.example.projecto.adapters;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import android.content.Context;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.test.core.app.ApplicationProvider;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.platform.app.InstrumentationRegistry;

import com.example.projecto.R;
import com.example.projecto.models.ViewAllModel;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.ArrayList;
import java.util.List;

@RunWith(AndroidJUnit4.class)
public class ViewAllAdaptersTest {

    private ViewAllAdapters adapter;
    private List<ViewAllModel> viewAllModelList;
    private Context context;

    @Before
    public void setUp() {
        context = ApplicationProvider.getApplicationContext();
        viewAllModelList = new ArrayList<>();
        ViewAllModel model = ViewAllModel.getInstance();
        model.setName("Test Name");
        model.setGname("Test Gname");
        model.setPrice(10.0);
        model.setDiscount("Test Discount");
        viewAllModelList.add(model);

        adapter = new ViewAllAdapters(context, viewAllModelList);
    }

    @Test
    public void testGetItemCount() {
        assertEquals(1, adapter.getItemCount());
    }

    @Test
    public void testOnCreateViewHolder() {
        ViewGroup parent = new RecyclerView(context);
        // Set a LayoutManager for the RecyclerView
        ((RecyclerView) parent).setLayoutManager(new LinearLayoutManager(context));
        ViewAllAdapters.ViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);
        assertNotNull(viewHolder);
    }

    @Test
    public void testOnBindViewHolder() {
        ViewGroup parent = new RecyclerView(context);
        // Set a LayoutManager for the RecyclerView
        ((RecyclerView) parent).setLayoutManager(new LinearLayoutManager(context));
        ViewAllAdapters.ViewHolder viewHolder = adapter.onCreateViewHolder(parent, 0);

        InstrumentationRegistry.getInstrumentation().runOnMainSync(() -> {
            adapter.onBindViewHolder(viewHolder, 0);

            TextView nameTextView = viewHolder.itemView.findViewById(R.id.item_name);
            TextView gnameTextView = viewHolder.itemView.findViewById(R.id.item_gname);
            TextView priceTextView = viewHolder.itemView.findViewById(R.id.item_price);
            TextView discountTextView = viewHolder.itemView.findViewById(R.id.item_discount);

            assertEquals("Test Name", nameTextView.getText().toString());
            assertEquals("Test Gname", gnameTextView.getText().toString());
            assertEquals("10.0 Taka", priceTextView.getText().toString());
            assertEquals("Test Discount", discountTextView.getText().toString());
        });
    }
}
