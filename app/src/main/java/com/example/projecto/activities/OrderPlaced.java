package com.example.projecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projecto.MainActivity;
import com.example.projecto.R;
import com.example.projecto.ui.home.HomeFragment;

public class OrderPlaced extends AppCompatActivity {

    Button BackToHome;

    public void onBackPressed() {
        super.onBackPressed();
        startActivity(new Intent(OrderPlaced.this, MainActivity.class));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_placed);

        BackToHome = findViewById(R.id.backtohome);

        BackToHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(OrderPlaced.this, MainActivity.class));
            }
        });
    }
}