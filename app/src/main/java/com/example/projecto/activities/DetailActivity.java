package com.example.projecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.projecto.R;
import com.example.projecto.models.ViewAllModel;
import com.example.projecto.observer.Observer;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity extends AppCompatActivity implements Observer {

    ImageView detailedImg;
    TextView name, price, gname, description;
    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;
    TextView quantity;

    int totalQuantity = 1;
    double totalPrice = 0.0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    ViewAllModel viewAllModel = null;

    ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        final Object object = getIntent().getSerializableExtra("detail");

        if (object instanceof ViewAllModel) {
            viewAllModel = (ViewAllModel) object;
        }

        detailedImg = findViewById(R.id.detail_img);
        addItem = findViewById(R.id.add_item);
        removeItem = findViewById(R.id.remove_item);

        price = findViewById(R.id.detail_price);
        name = findViewById(R.id.detail_name);
        gname = findViewById(R.id.detail_gname);
        description = findViewById(R.id.detail_des);
        quantity = findViewById(R.id.quantity);
        addToCart = findViewById(R.id.add_to_cart);

        firestore = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        if (viewAllModel != null) {
            viewAllModel.registerObserver(this);
            progressBar.setVisibility(View.GONE);
            update();
        }

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10) {
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = (double) (viewAllModel.getPrice() * totalQuantity);
                }
            }
        });

        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 0) {
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = (double) (viewAllModel.getPrice() * totalQuantity);
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() == null) {
                    Toast.makeText(DetailActivity.this, "Please log in to add items to your cart.", Toast.LENGTH_SHORT).show();
                    return;
                }
                addedToCart();
            }
        });
    }

    @Override
    public void update() {
        if (viewAllModel != null) {
            Glide.with(getApplicationContext()).load(viewAllModel.getImg_url()).into(detailedImg);
            name.setText(viewAllModel.getName());
            gname.setText(viewAllModel.getGname());
            description.setText(viewAllModel.getDescription());
            price.setText("Unit Price " + viewAllModel.getPrice() + " Tk");
            totalPrice = (double) (viewAllModel.getPrice() * totalQuantity);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (viewAllModel != null) {
            viewAllModel.removeObserver(this);
        }
    }

    private void addedToCart() {
        String saveCurrentDate, saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String, Object> cartMap = new HashMap<>();

        cartMap.put("productName", viewAllModel.getName());
        cartMap.put("productPrice", price.getText().toString());
        cartMap.put("currentDate", saveCurrentDate);
        cartMap.put("currentTime", saveCurrentTime);
        cartMap.put("totalQuantity", quantity.getText().toString());
        cartMap.put("totalPrice", totalPrice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DetailActivity.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                });
    }
}
