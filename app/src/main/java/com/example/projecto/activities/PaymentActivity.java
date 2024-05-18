package com.example.projecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.widget.Toolbar;
import com.example.projecto.R;
import com.example.projecto.ui.home.HomeFragment;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.snapshot.DoubleNode;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;

import java.util.Map;

public class PaymentActivity extends AppCompatActivity {


    Button paybtn;
    TextView subTotal, discount, shipping, totalAmount;

    Double receivedTotalAmount;

    int gTotal;
    Toolbar toolbar;

    FirebaseFirestore db;
    FirebaseAuth auth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        toolbar = findViewById(R.id.payment_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        db = FirebaseFirestore.getInstance();
        auth = FirebaseAuth.getInstance();

        subTotal = findViewById(R.id.sub_total);
        discount = findViewById(R.id.textView17);
        shipping = findViewById(R.id.textView18);
        totalAmount = findViewById(R.id.total_amt);
        paybtn = findViewById(R.id.pay_btn);

        Intent intent = getIntent();
        if (intent != null) {
            receivedTotalAmount = intent.getDoubleExtra("TOTAL_AMOUNT_KEY", 0.0);
            // Now 'receivedTotalAmount' holds your total amount as a double, use it as needed
        }

        subTotal.setText(String.valueOf(receivedTotalAmount)+" Tk");
        discount.setText("10%");
        shipping.setText("50 Tk");


        int gtotal = (int) ((receivedTotalAmount-(receivedTotalAmount * 0.1 )) + 50);

        totalAmount.setText(String.valueOf(gtotal)+" Tk");

        paybtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                bruh();
            }
        });

    }

    private void bruh() {
        db.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("AddToCart")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful()) {
                        for (QueryDocumentSnapshot document : task.getResult()) {
                            // Step 2: Create MyOrders Collection
                            Map<String, Object> data = document.getData();
                            db.collection("CurrentUser").document(auth.getCurrentUser().getUid()).collection("MyOrders")
                                    .add(data) // Copy the document to MyOrders
                                    .addOnSuccessListener(documentReference -> {
                                        // Step 3: Delete from AddToCart
                                        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                                                .collection("AddToCart").document(document.getId())
                                                .delete(); // Delete the document from AddToCart
                                    })
                                    .addOnFailureListener(e -> {
                                        // Handle any errors while copying
                                    });
                        }
                    } else {
                        // Handle failure to read documents from AddToCart
                    }
                });
        startActivity(new Intent(PaymentActivity.this, OrderPlaced.class));
    }
}