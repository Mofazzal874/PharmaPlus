package com.example.projecto.activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.example.projecto.R;
import com.example.projecto.adapters.ViewAllAdapters;
import com.example.projecto.models.ViewAllModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.ktx.Firebase;

import java.util.ArrayList;
import java.util.List;

public class ViewAllActivity extends AppCompatActivity {


    FirebaseFirestore firestore;
    RecyclerView recyclerView;
    ViewAllAdapters viewAllAdapters;

    List<ViewAllModel> viewAllModelList;

    Toolbar toolbar;

    ProgressBar progressBar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);

        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        progressBar = findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        firestore = FirebaseFirestore.getInstance();

        String type = getIntent().getStringExtra("type");
        recyclerView = findViewById(R.id.view_all_rec);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        viewAllModelList = new ArrayList<>();
        viewAllAdapters = new ViewAllAdapters(this, viewAllModelList);
        recyclerView.setAdapter(viewAllAdapters);

        if (type != null && type.equalsIgnoreCase("medication")){
            firestore.collection("Allproducts").whereEqualTo("type","medication").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                            viewAllModelList.add(viewAllModel);
                            viewAllAdapters.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                    else{
                        Toast.makeText(ViewAllActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("health")){
            firestore.collection("Allproducts").whereEqualTo("type","health").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                            viewAllModelList.add(viewAllModel);
                            viewAllAdapters.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                    else{
                        Toast.makeText(ViewAllActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

        if (type != null && type.equalsIgnoreCase("personal")){
            firestore.collection("Allproducts").whereEqualTo("type","personal").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                @Override
                public void onComplete(@NonNull Task<QuerySnapshot> task) {
                    if (task.isSuccessful()) {
                        for (DocumentSnapshot documentSnapshot : task.getResult().getDocuments()) {
                            ViewAllModel viewAllModel = documentSnapshot.toObject(ViewAllModel.class);
                            viewAllModelList.add(viewAllModel);
                            viewAllAdapters.notifyDataSetChanged();
                            progressBar.setVisibility(View.GONE);
                            recyclerView.setVisibility(View.VISIBLE);
                        }
                    }
                    else{
                        Toast.makeText(ViewAllActivity.this, ""+task.getException(), Toast.LENGTH_SHORT).show();
                    }
                }
            });
        }

    }
}