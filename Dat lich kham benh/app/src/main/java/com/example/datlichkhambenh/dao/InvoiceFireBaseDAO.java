package com.example.datlichkhambenh.dao;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.InvoiceAdapter;
import com.example.datlichkhambenh.model.Information;
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

public class InvoiceFireBaseDAO {
    FirebaseFirestore db;
    Context context;
    public InvoiceFireBaseDAO(Context context){
        //Kết nối với database hiện tại
        db = FirebaseFirestore.getInstance();
        this.context = context;
    }
    public void insert(Information i){
        //Thêm một document mới với một generated ID
        i.setId(UUID.randomUUID().toString());
        HashMap<String, Object> mapInformation = i.convertHashMap();
        db.collection("Information").document(i.getId())
                .set(mapInformation)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Thêm thành công!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Thêm không thành công", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void delete(String informationId){
        db.collection("Information").document(informationId)
                .delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
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
    public void getInvoiceByEmail(final RecyclerView recyclerView) {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            String email = user.getEmail();
            db.collection("Information")
                    .whereEqualTo("email", email)
                    .get()
                    .addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
                        @Override
                        public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                            List<Information> informationList = new ArrayList<>();
                            for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                                Information information = documentSnapshot.toObject(Information.class);
                                informationList.add(information);
                            }
                            InvoiceAdapter adapter = new InvoiceAdapter(context ,InvoiceFireBaseDAO.this, informationList);
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
