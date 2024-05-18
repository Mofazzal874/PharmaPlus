package com.example.projecto;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecto.adapters.MyCartAdapters;
import com.example.projecto.adapters.MyOrderAdapters;
import com.example.projecto.models.MyCartModel;
import com.example.projecto.ui.home.HomeFragment;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MyordersFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    MyOrderAdapters cartAdapters;

    List<MyCartModel> cartModelList;

    TextView overTotalAmount;

    ProgressBar progressBar;

    ConstraintLayout constraint1,constraint2;




    public MyordersFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_myorders, container, false);

        progressBar = root.findViewById(R.id.progressbar);
        progressBar.setVisibility(View.VISIBLE);

        constraint1 = root.findViewById(R.id.constaint1);
        constraint2 = root.findViewById(R.id.constaint2);


        db = FirebaseFirestore.getInstance();
        auth =FirebaseAuth.getInstance();
        recyclerView = root.findViewById(R.id.cart_recyclerview);
        recyclerView.setVisibility(View.GONE);
        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));

        overTotalAmount = root.findViewById(R.id.textView6);

        cartModelList = new ArrayList<>();
        cartAdapters = new MyOrderAdapters(getActivity(),cartModelList);

        recyclerView.setAdapter(cartAdapters);



        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("MyOrders").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){

                                String documentId = documentSnapshot.getId();

                                MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                                cartModel.setDocumentId(documentId);
                                cartModelList.add(cartModel);
                                cartAdapters.notifyDataSetChanged();
                                progressBar.setVisibility(View.GONE);
                                recyclerView.setVisibility(View.VISIBLE);
                            }

                            if (cartModelList.isEmpty()) {
                                // Cart is empty, show constaint1
                                constraint1.setVisibility(View.VISIBLE);
                                constraint2.setVisibility(View.GONE);
                                progressBar.setVisibility(View.GONE);

                            } else {
                                // Cart has items, show constaint2
                                constraint1.setVisibility(View.GONE);
                                constraint2.setVisibility(View.VISIBLE);
                                progressBar.setVisibility(View.GONE);
                            }

                        }
                        else{
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });

        return root;
    }
}