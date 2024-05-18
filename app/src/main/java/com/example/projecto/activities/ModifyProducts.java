package com.example.projecto.activities;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecto.R;
import com.example.projecto.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class ModifyProducts extends AppCompatActivity {

    EditText etProductName, etGenericName, etPrice, etDiscount, etType,etDescription;
    Button btnUploadImage, btnModify;
    String imageUrl;

    FirebaseFirestore db;
    FirebaseStorage storage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_modify_products);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        etProductName = findViewById(R.id.etName);
        etGenericName = findViewById(R.id.etGenericName);
        etDescription = findViewById(R.id.etDescription);
        etPrice = findViewById(R.id.etPrice);
        etDiscount = findViewById(R.id.etDiscount);
        etType = findViewById(R.id.etType);
        btnUploadImage = findViewById(R.id.btnUploadImage);
        btnModify = findViewById(R.id.btnModifyProducts);


        btnModify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String productName = etProductName.getText().toString().trim();
                if (!productName.isEmpty()) {
                    searchAndModifyProduct(productName);
                } else {
                    Toast.makeText(ModifyProducts.this, "Please enter a product name", Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });


    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 33);
    }

    private void searchAndModifyProduct(String productName) {
        Query query = db.collection("Allproducts").whereEqualTo("name", productName);

        query.get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if (task.isSuccessful()) {
                    for (QueryDocumentSnapshot document : task.getResult()) {
                        modifyProduct(document.getId());
                    }
                } else {
                    Toast.makeText(ModifyProducts.this, "Product not found", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    private void modifyProduct(String productId) {
        // Fetch the modified data from EditText fields
        String modifiedName = etProductName.getText().toString().trim();
        String modifiedGenericName = etGenericName.getText().toString().trim();
        String modifiedDescription = etDescription.getText().toString().trim();
        String modifiedPrice = etPrice.getText().toString().trim();
        String modifiedDiscount = etDiscount.getText().toString().trim();
        String modifiedType = etType.getText().toString().trim();

        // Create a ViewAllModel object with the modified data
        ViewAllModel modifiedProduct = new ViewAllModel(
                modifiedName, modifiedGenericName, Double.parseDouble(modifiedPrice),
                modifiedDescription, imageUrl, modifiedType, modifiedDiscount);

        // Update the document in Firestore with the modified object
        db.collection("Allproducts").document(productId)
                .set(modifiedProduct)
                .addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            Toast.makeText(ModifyProducts.this, "Product modified successfully", Toast.LENGTH_SHORT).show();
                            finish(); // Finish the activity after modifying the product
                        } else {
                            Toast.makeText(ModifyProducts.this, "Failed to modify product", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (data != null && data.getData() != null) {
            Uri productUri = data.getData();

            // Create a unique name for the image file in Firebase Storage
            String imageName = System.currentTimeMillis() + "_" + productUri.getLastPathSegment();

            // Specify the path in Firebase Storage where the image will be stored
            StorageReference storageReference = storage.getReference().child("product_images/" + imageName);

            // Upload the image to Firebase Storage
            storageReference.putFile(productUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    // Image uploaded successfully
                    // Now you can get the download URL and use it in your Firestore document
                    storageReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            imageUrl = uri.toString();
                            // Now you can use 'imageUrl' to store in Firestore or do further processing
                            Toast.makeText(ModifyProducts.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }

}
