package com.example.projecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.example.projecto.R;

public class UseDetails extends AppCompatActivity {

    TextView use;

    String bruh;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_use_details);

        use = findViewById(R.id.useTextview);

        Intent intent = getIntent();
        if (intent != null) {
            bruh = intent.getStringExtra("use");
        }
        use.setText(bruh);
    }
}