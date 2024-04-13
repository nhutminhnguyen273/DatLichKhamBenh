package com.example.datlichkhambenh.dao;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.datlichkhambenh.model.User;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.ktx.Firebase;

import java.util.HashMap;
import java.util.Objects;
import java.util.UUID;

public class UserFireBaseDAO {
    FirebaseFirestore db;
    Context context;
    public UserFireBaseDAO(Context context){
        //kết nối với DB hiện tại
        db = FirebaseFirestore.getInstance();
        this.context = context;
    }
    public void insert(User u){
        u.setId(UUID.randomUUID().toString());
        HashMap<String, Object> mapUser = u.convertHashMap();
        db.collection("User").document(u.getId()).set(mapUser).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Add information successfully!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Add information failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void delete(String userId){
        db.collection("User").document(userId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Delete information successfully!", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Delete information failed!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
