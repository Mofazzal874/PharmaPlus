package com.example.projecto.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.projecto.R;

public class AdminLoginActivity extends AppCompatActivity {

    EditText username,password;
    Button login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_admin_login);

        username = findViewById(R.id.etUsername);
        password = findViewById(R.id.etPassword);
        login = findViewById(R.id.btnLogin);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adminLogin();
            }
        });
    }

    private void adminLogin() {
        String tusername = username.getText().toString();
        String tpassword = password.getText().toString();

        if ("admin".equals(tusername) && "admin".equals(tpassword)){
            startActivity(new Intent(AdminLoginActivity.this,AdminPanel.class));
        }
    }
}