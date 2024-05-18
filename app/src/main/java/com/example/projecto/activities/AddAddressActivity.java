package com.example.projecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.widget.Toolbar;

import com.example.projecto.MainActivity;
import com.example.projecto.MycartsFragment;
import com.example.projecto.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class AddAddressActivity extends AppCompatActivity {

    EditText name,address,city,postalCode,phoneNumber;
    Toolbar toolbar;

    Button addAddressBtn;

    FirebaseFirestore db;
    FirebaseAuth auth;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_address);

        toolbar = findViewById(R.id.add_address_toolbar);
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

        name = findViewById(R.id.ad_name);
        address = findViewById(R.id.ad_address);
        city = findViewById(R.id.ad_city);
        postalCode = findViewById(R.id.ad_code);
        phoneNumber = findViewById(R.id.ad_phone);

        addAddressBtn = findViewById(R.id.ad_add_address);

        addAddressBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userName = name.getText().toString();
                String userCity = city.getText().toString();
                String userCode = postalCode.getText().toString();
                String userAddress = address.getText().toString();
                String userNumber = phoneNumber.getText().toString();

                String final_address = "";

                if (!userName.isEmpty()){
                    final_address+=userName;
                    final_address+=", ";
                }
                if (!userAddress.isEmpty()){
                    final_address+=userAddress;
                    final_address+=", ";
                }
                if (!userCode.isEmpty()){
                    final_address+=userCode;
                    final_address+=", ";
                }
                if (!userCity.isEmpty()){
                    final_address+=userCity;
                    final_address+=", ";
                }
                if (!userNumber.isEmpty()){
                    final_address+=userNumber;
                }

                if (!userName.isEmpty() && !userCity.isEmpty() && !userCode.isEmpty() && !userAddress.isEmpty() && !userNumber.isEmpty()){
                    Map<String,String> map = new HashMap<>();

                    map.put("userAddress",final_address);

                    db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                            .collection("Address").add(map).addOnCompleteListener(new OnCompleteListener<DocumentReference>() {
                                @Override
                                public void onComplete(@NonNull Task<DocumentReference> task) {
                                    
                                    if (task.isSuccessful()){
                                        Toast.makeText(AddAddressActivity.this, "Address Added", Toast.LENGTH_SHORT).show();
                                    }
            
                                }
                            });
                    startActivity(new Intent(AddAddressActivity.this, MainActivity.class));
                }
                else{
                    Toast.makeText(AddAddressActivity.this, "Please fill all info", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}