package com.example.projecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.example.projecto.R;
import com.example.projecto.models.SuggestedModel;
import com.example.projecto.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;

public class DetailActivity2 extends AppCompatActivity {

    ImageView detailedImg;
    TextView name, price, gname,description;

    Button addToCart;
    ImageView addItem, removeItem;
    Toolbar toolbar;

    TextView quantity;

    int totalQuantity = 1;
    double totalPrice = 0.0;

    FirebaseFirestore firestore;
    FirebaseAuth auth;

    SuggestedModel suggestedModel = null;

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

        if (object instanceof SuggestedModel){
            suggestedModel = (SuggestedModel) object;
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

        if (suggestedModel != null){
            progressBar.setVisibility(View.GONE);
            Glide.with(getApplicationContext()).load(suggestedModel.getImg_url()).into(detailedImg);
            name.setText(suggestedModel.getName());
            gname.setText(suggestedModel.getGname());
            description.setText(suggestedModel.getDescription());
            price.setText("Unit Price "+suggestedModel.getPrice()+" Tk");

            totalPrice = (double) (suggestedModel.getPrice() * totalQuantity);

        }

        addItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity < 10){
                    totalQuantity++;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = (double) (suggestedModel.getPrice() * totalQuantity);
                }
            }
        });
        removeItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (totalQuantity > 0){
                    totalQuantity--;
                    quantity.setText(String.valueOf(totalQuantity));
                    totalPrice = (double) (suggestedModel.getPrice() * totalQuantity);
                }
            }
        });

        addToCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (auth.getCurrentUser() == null) {
                    Toast.makeText(DetailActivity2.this, "Please log in to add items to your cart.", Toast.LENGTH_SHORT).show();
                    return;
                }
                addedToCart();
            }
        });


    }

    private void addedToCart() {
        String saveCurrentDate,saveCurrentTime;
        Calendar calForDate = Calendar.getInstance();

        SimpleDateFormat currentDate = new SimpleDateFormat("MM dd, yyyy");
        saveCurrentDate = currentDate.format(calForDate.getTime());

        SimpleDateFormat currentTime = new SimpleDateFormat("HH:mm:ss a");
        saveCurrentTime = currentTime.format(calForDate.getTime());

        final HashMap<String,Object> cartMap = new HashMap<>();

        cartMap.put("productName",suggestedModel.getName());
        cartMap.put("productPrice",price.getText().toString());
        cartMap.put("currentDate",saveCurrentDate);
        cartMap.put("currentTime",saveCurrentTime);
        cartMap.put("totalQuantity",quantity.getText().toString());
        cartMap.put("totalPrice",totalPrice);

        firestore.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").add(cartMap).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentReference> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DetailActivity2.this, "Added to Cart", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DetailActivity2.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                        finish();
                    }
                });

    }
}