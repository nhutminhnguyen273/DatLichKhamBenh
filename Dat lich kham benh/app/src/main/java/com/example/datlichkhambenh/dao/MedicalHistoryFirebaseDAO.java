package com.example.datlichkhambenh.dao;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.MedicalHistoryAdapter;
import com.example.datlichkhambenh.model.MedicalHistory;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MedicalHistoryFirebaseDAO {
    FirebaseFirestore db;
    Context context;
    public MedicalHistoryFirebaseDAO(Context context){
        db= FirebaseFirestore.getInstance();
    }

    public void getMedicalHistoryByEmail(final RecyclerView recyclerView) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            db.collection("MedicalHistory")
                    .whereEqualTo("email", email)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<MedicalHistory> medicalHistoryList = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                MedicalHistory medicalHistory = documentSnapshot.toObject(MedicalHistory.class);
                                medicalHistoryList.add(medicalHistory);
                            }
                            MedicalHistoryAdapter adapter = new MedicalHistoryAdapter(context, MedicalHistoryFirebaseDAO.this, medicalHistoryList);
                            recyclerView.setAdapter(adapter);
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Toast.makeText(context, "Load data failed", Toast.LENGTH_SHORT).show();
                        }
                    });
        }
    }

}
