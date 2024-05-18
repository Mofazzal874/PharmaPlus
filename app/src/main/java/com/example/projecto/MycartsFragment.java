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
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.projecto.activities.AddressActivity;
import com.example.projecto.adapters.MyCartAdapters;
import com.example.projecto.models.MyCartModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;


public class MycartsFragment extends Fragment {

    FirebaseFirestore db;
    FirebaseAuth auth;

    RecyclerView recyclerView;
    MyCartAdapters cartAdapters;

    List<MyCartModel> cartModelList;

    TextView overTotalAmount;

    ProgressBar progressBar;

    Button buyNow;

    ConstraintLayout constraint1,constraint2;
    public MycartsFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_mycarts, container, false);

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

        buyNow = root.findViewById(R.id.buy_now);

        cartModelList = new ArrayList<>();
        cartAdapters = new MyCartAdapters(getActivity(),cartModelList);

        recyclerView.setAdapter(cartAdapters);

        buyNow.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getActivity(), AddressActivity.class);
                intent.putExtra("TOTAL_AMOUNT_KEY", calculateTotalAmount(cartModelList));
                startActivity(intent);
            }
        });

        db.collection("CurrentUser").document(auth.getCurrentUser().getUid())
                .collection("AddToCart").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        if (task.isSuccessful()){
                            for (DocumentSnapshot documentSnapshot: task.getResult().getDocuments()){

                                String documentId = documentSnapshot.getId();

                                MyCartModel cartModel = documentSnapshot.toObject(MyCartModel.class);

                                cartModel.setDocumentId(documentId);
                                cartModelList.add(cartModel);
                                cartAdapters.notifyDataSetChanged();
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
                                calculateTotalAmount(cartModelList);
                            }
                        }
                        else{
                            Toast.makeText(getActivity(), task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });


        return root;

    }

    private double calculateTotalAmount(List<MyCartModel> cartModelList) {
        double totalAmount = 0.0;
        for (MyCartModel myCartModel: cartModelList){
            totalAmount+= myCartModel.getTotalPrice();
        }
        overTotalAmount.setText("Total Amount: "+totalAmount+" Taka");
        return totalAmount;
    }
}