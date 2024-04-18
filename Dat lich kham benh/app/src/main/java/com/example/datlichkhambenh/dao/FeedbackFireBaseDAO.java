package com.example.datlichkhambenh.dao;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.DListFeedbackAdapter;
import com.example.datlichkhambenh.adapter.EvaluateAdapter;
import com.example.datlichkhambenh.adapter.ListFeedbackAdapter;
import com.example.datlichkhambenh.model.Evaluate;
import com.example.datlichkhambenh.model.Feedback;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class FeedbackFireBaseDAO {
    FirebaseFirestore db;
    Context context;
    public FeedbackFireBaseDAO(Context context){
        this.context = context;
        db = FirebaseFirestore.getInstance();
    }
    public void insert(Feedback f){
        f.setId(UUID.randomUUID().toString());
        HashMap<String, Object> mapFeedback = f.convertHashMap();
        db.collection("Feedback").document(f.getId()).set(mapFeedback).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context,"Thêm không thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    private void delete(String feedbackId){
        db.collection("Feedback").document(feedbackId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Xóa không thành công!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getFeedbackByEmailSent(final RecyclerView recyclerView){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            String email = user.getEmail();
            db.collection("Feedback")
                    .whereEqualTo("emailSent", email)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Feedback> listFeedback = new ArrayList<>();
                            for(QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                Feedback feedback = documentSnapshot.toObject(Feedback.class);
                                listFeedback.add(feedback);
                            }
                            ListFeedbackAdapter adapter = new ListFeedbackAdapter(context, FeedbackFireBaseDAO.this, listFeedback);
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
    public void getAllFeedback(final RecyclerView recyclerView){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            db.collection("Feedback")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Feedback> listFeedback= new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                Feedback feedback = documentSnapshot.toObject(Feedback.class);
                                listFeedback.add(feedback);
                            }
                            DListFeedbackAdapter adapter = new DListFeedbackAdapter(context, FeedbackFireBaseDAO.this, listFeedback);
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
