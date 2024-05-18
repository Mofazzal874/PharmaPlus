package com.example.projecto.queries;

import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.QuerySnapshot;

public interface ProductQuery {
    Task<QuerySnapshot> fetchProducts();
}
