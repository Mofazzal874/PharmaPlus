package com.example.projecto.queries;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class HealthQuery implements ProductQuery {
    private FirebaseFirestore firestore;

    public HealthQuery(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Task<QuerySnapshot> fetchProducts() {
        return firestore.collection("Allproducts").whereEqualTo("type", "health").get();
    }
}
