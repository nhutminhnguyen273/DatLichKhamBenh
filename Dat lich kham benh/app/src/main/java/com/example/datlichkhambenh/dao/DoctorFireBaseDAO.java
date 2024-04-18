package com.example.datlichkhambenh.dao;

import android.content.Context;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.example.datlichkhambenh.model.Doctor;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.UUID;

public class DoctorFireBaseDAO {
    FirebaseFirestore db;
    Context context;
    public DoctorFireBaseDAO(Context context){
        db = FirebaseFirestore.getInstance();
        this.context = context;
    }
    public void insert(Doctor d){
        d.setId(UUID.randomUUID().toString());
        HashMap<String, Object> mapDoctor = d.convertHashMap();
        db.collection("Doctor").document(d.getId())
                .set(mapDoctor)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Thêm thông tin thành công!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Thêm thông tin thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
    public void Delete(String doctorId){
        db.collection("Doctor").document(doctorId)
                .delete().addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void unused) {
                        Toast.makeText(context, "Xóa thông tin thành công!", Toast.LENGTH_SHORT).show();
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context, "Xóa thất bại", Toast.LENGTH_SHORT).show();
                    }
                });
    }
}
