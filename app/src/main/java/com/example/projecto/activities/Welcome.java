package com.example.projecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.projecto.MainActivity;
import com.example.projecto.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Welcome extends AppCompatActivity {
    Button ubutton,abutton;
    FirebaseUser user;

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finishAffinity(); // Finish all activities in the app and exit.
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
        ubutton = findViewById(R.id.lauser);
        abutton = findViewById(R.id.laadmin);

        user = FirebaseAuth.getInstance().getCurrentUser();

        ubutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (user!= null) {
                    startActivity(new Intent(Welcome.this, MainActivity.class));
                }
                else{
                    startActivity(new Intent(Welcome.this, LoginActivity.class));
                }
            }
        });

        abutton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Welcome.this,AdminLoginActivity.class));
            }
        });
    }
}