package com.example.projecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projecto.R;

public class AdminPanel extends AppCompatActivity {

    Button Add,Modify,Delete;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(AdminPanel.this, Welcome.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_panel);

        Add = findViewById(R.id.btnAddProducts);
        Modify = findViewById(R.id.btnModifyProducts);
        Delete = findViewById(R.id.btnDeleteProducts);

        Add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPanel.this, AddProducts.class));
            }
        });
        Modify.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPanel.this,ModifyProducts.class));
            }
        });
        Delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AdminPanel.this,DeleteProducts.class));
            }
        });
    }
}