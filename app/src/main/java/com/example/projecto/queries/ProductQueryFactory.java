package com.example.projecto.queries;

import com.google.firebase.firestore.FirebaseFirestore;

public class ProductQueryFactory {
    public static ProductQuery createQuery(String type, FirebaseFirestore firestore) {
        switch (type.toLowerCase()) {
            case "medication":
                return new MedicationQuery(firestore);
            case "health":
                return new HealthQuery(firestore);
            case "personal":
                return new PersonalQuery(firestore);
            default:
                throw new IllegalArgumentException("Unknown type: " + type);
        }
    }
}
