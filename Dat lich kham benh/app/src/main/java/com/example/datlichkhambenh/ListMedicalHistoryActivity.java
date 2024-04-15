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

import com.example.datlichkhambenh.dao.MedicalHistoryFirebaseDAO;

public class ListMedicalHistoryActivity extends AppCompatActivity {
    private Button btnCreateMedicalHistory, btnCancel;
    private MedicalHistoryFirebaseDAO medicalHistoryFirebaseDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_list_medical_history);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        medicalHistoryFirebaseDAO = new MedicalHistoryFirebaseDAO(this);
        RecyclerView recyclerView = findViewById(R.id.rvLMedicalHistory);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        medicalHistoryFirebaseDAO.getMedicalHistoryByEmail(recyclerView);
        btnCancel = findViewById(R.id.btnLMHCancel);
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void cancel() {
        Intent i = new Intent(ListMedicalHistoryActivity.this, MainActivity.class);
        startActivity(i);
    }
}
