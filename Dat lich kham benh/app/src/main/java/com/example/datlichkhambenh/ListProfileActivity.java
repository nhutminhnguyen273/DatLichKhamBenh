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

import com.example.datlichkhambenh.dao.ProfileFireBaseDAO;

public class ListProfileActivity extends AppCompatActivity {
    Button btnCreateProfile, btnCancel;
    private ProfileFireBaseDAO profileFireBaseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_profile);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        profileFireBaseDAO = new ProfileFireBaseDAO(this);
        RecyclerView recyclerView = findViewById(R.id.rvLPProfile);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        profileFireBaseDAO.getProfileByEmail(recyclerView);
        btnCreateProfile = findViewById(R.id.btnLPProfile);
        btnCancel = findViewById(R.id.btnLPCancel);
        btnCreateProfile.setOnClickListener(v -> createProfile());
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void createProfile(){
        Intent i = new Intent(ListProfileActivity.this, ProfileActivity.class);
        startActivity(i);
    }
    private void cancel(){
        Intent i = new Intent(ListProfileActivity.this, MainActivity.class);
        startActivity(i);
    }
}