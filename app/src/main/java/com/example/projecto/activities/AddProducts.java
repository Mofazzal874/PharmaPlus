package com.example.projecto.activities;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.projecto.R;
import com.example.projecto.models.ViewAllModel;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class AddProducts extends AppCompatActivity {

    EditText name, description, gname, price, type, discount;
    Button uploadImg, addProduct;

    FirebaseFirestore db;
    FirebaseStorage storage;

    String imageUrl;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_products);

        db = FirebaseFirestore.getInstance();
        storage = FirebaseStorage.getInstance();

        name = findViewById(R.id.etName);
        description = findViewById(R.id.etDescription);
        gname = findViewById(R.id.etGenericName);
        price = findViewById(R.id.etPrice);
        type = findViewById(R.id.etType);
        discount = findViewById(R.id.etDiscount);

        uploadImg = findViewById(R.id.btnUploadImage);
        addProduct = findViewById(R.id.btnAddProduct);

        uploadImg.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openImagePicker();
            }
        });

        addProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addP();
            }
        });
    }

    private void openImagePicker() {
        Intent intent = new Intent();
        intent.setAction(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        startActivityForResult(intent, 33);
    }

    private void addP() {
            String productName = name.getText().toString().trim();
            String productDescription = description.getText().toString().trim();
            String productGenericName = gname.getText().toString().trim();
            String productPrice = price.getText().toString().trim();
            String productType = type.getText().toString().trim();
            String productDiscount = discount.getText().toString().trim();

            if (productName.isEmpty() || productDescription.isEmpty() || productGenericName.isEmpty()
                    || productPrice.isEmpty() || productType.isEmpty() || productDiscount.isEmpty()) {
                Toast.makeText(this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                return;
            }

            // Create an instance of your ViewAllModel
            ViewAllModel product = new ViewAllModel(
                    productName, productGenericName, Double.parseDouble(productPrice),
                    productDescription, imageUrl, productType, productDiscount);

            // Assuming 'Allproducts' is the Firestore collection
            db.collection("Allproducts")
                    .add(product)
                    .addOnSuccessListener(documentReference -> {
                        Toast.makeText(AddProducts.this, "Product added successfully", Toast.LENGTH_SHORT).show();
                        finish(); // Finish the activity after adding the product
                    })
                    .addOnFailureListener(e -> {
                        Toast.makeText(AddProducts.this, "Failed to add product", Toast.LENGTH_SHORT).show();
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
                            Toast.makeText(AddProducts.this, "Image uploaded successfully", Toast.LENGTH_SHORT).show();
                        }
                    });
                }
            });
        }
    }
}
