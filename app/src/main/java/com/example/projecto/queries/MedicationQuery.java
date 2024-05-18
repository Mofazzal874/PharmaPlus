package com.example.projecto.queries;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

public class MedicationQuery implements ProductQuery {
    private FirebaseFirestore firestore;

    public MedicationQuery(FirebaseFirestore firestore) {
        this.firestore = firestore;
    }

    @Override
    public Task<QuerySnapshot> fetchProducts() {
        return firestore.collection("Allproducts").whereEqualTo("type", "medication").get();
    }
}
