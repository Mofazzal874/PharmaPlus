package com.example.projecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projecto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class DeleteProducts extends AppCompatActivity {

    EditText etProductName;
    Button btnSearchAndDelete;

    FirebaseFirestore db;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_delete_products);

        db = FirebaseFirestore.getInstance();

        etProductName = findViewById(R.id.etProductName);
        btnSearchAndDelete = findViewById(R.id.btnSearchAndDelete);

        btnSearchAndDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = etProductName.getText().toString().trim();
                if (!productName.isEmpty()) {
                    searchAndDeleteProduct(productName);
                } else {
                    Toast.makeText(DeleteProducts.this, "Please enter a product name", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void searchAndDeleteProduct(String productName) {
        Query query = db.collection("Allproducts").whereEqualTo("name", productName);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        deleteProduct(document.getId());
                    }
                } else {
                    Toast.makeText(DeleteProducts.this, "Product not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void deleteProduct(String productId) {
        db.collection("Allproducts").document(productId)
                .delete()
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(DeleteProducts.this, "Product deleted successfully", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(DeleteProducts.this, "Failed to delete product", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
}
