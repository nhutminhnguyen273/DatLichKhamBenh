package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.adapter.DListProfileAdapter;
import com.example.datlichkhambenh.dao.ProfileFireBaseDAO;

public class DListProfileActivity extends AppCompatActivity {
    private DListProfileAdapter adapter;
    private ProfileFireBaseDAO profileFireBaseDAO;
    Button btnCancel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dlist_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        RecyclerView recyclerView = findViewById(R.id.rvDLProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileFireBaseDAO = new ProfileFireBaseDAO(this);
        profileFireBaseDAO.getAllProfiles(recyclerView);
        btnCancel = findViewById(R.id.btnDLPCancel);
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void cancel(){
        Intent i = new Intent(DListProfileActivity.this, DoctorActivity.class);
        startActivity(i);
    }
}