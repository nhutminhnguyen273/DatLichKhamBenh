package com.example.datlichkhambenh.dao;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.ProfileAdapter;
import com.example.datlichkhambenh.model.Profile;
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

public class ProfileFireBaseDAO {
    FirebaseFirestore db;
    Context context;
    public ProfileFireBaseDAO(Context context){
        db= FirebaseFirestore.getInstance();
    }
    public void insert(Profile p){
        p.setId(UUID.randomUUID().toString());
        HashMap<String, Object> mapProfile = p.convertHashMap();
        db.collection("Profile").document(p.getId()).set(mapProfile).addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Add successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Add failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void delete(String profileId){
        db.collection("Profile").document(profileId).delete().addOnSuccessListener(new OnSuccessListener<Void>() {
            @Override
            public void onSuccess(Void unused) {
                Toast.makeText(context, "Delete successfully", Toast.LENGTH_SHORT).show();
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                Toast.makeText(context, "Delete Failed", Toast.LENGTH_SHORT).show();
            }
        });
    }
    public void getProfileByEmail(final RecyclerView recyclerView) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            db.collection("Profile")
                    .whereEqualTo("email", email)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Profile> profileList = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                Profile profile = documentSnapshot.toObject(Profile.class);
                                profileList.add(profile);
                            }
                            ProfileAdapter adapter = new ProfileAdapter(context ,ProfileFireBaseDAO.this, profileList);
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