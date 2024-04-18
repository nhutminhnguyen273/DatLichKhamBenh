package com.example.datlichkhambenh.dao;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.EvaluateAdapter;
import com.example.datlichkhambenh.model.Evaluate;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

public class EvaluateFireBaseDAO {
    FirebaseFirestore db;
    Context context;
    public EvaluateFireBaseDAO(Context context){
        db = FirebaseFirestore.getInstance();
    }
    public void insert(Evaluate e){
        e.setId(UUID.randomUUID().toString());
        HashMap<String, Object> mapEvaluate = e.convertHashMap();
        db.collection("Evaluate").document(e.getId()).set(mapEvaluate).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Thêm thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void delete(String evaluateId){
        db.collection("Evaluate").document(evaluateId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Xóa thành công!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Xóa thất bại!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getAllEvaluate(final RecyclerView recyclerView){
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if(user != null){
            db.collection("Evaluate")
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Evaluate> listEvaluate = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots){
                                Evaluate evaluate = documentSnapshot.toObject(Evaluate.class);
                                listEvaluate.add(evaluate);
                            }
                            EvaluateAdapter adapter = new EvaluateAdapter(context, EvaluateFireBaseDAO.this, listEvaluate);
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
