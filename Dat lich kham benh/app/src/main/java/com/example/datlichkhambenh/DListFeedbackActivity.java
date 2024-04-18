package com.example.datlichkhambenh;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.datlichkhambenh.dao.FeedbackFireBaseDAO;
import com.google.firebase.auth.FirebaseAuth;

import java.util.Objects;

public class DListFeedbackActivity extends AppCompatActivity {
    Button btnCancel;
    private FeedbackFireBaseDAO feedbackFireBaseDAO;
    private String currentDoctorName;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_dlist_feedback);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        feedbackFireBaseDAO = new FeedbackFireBaseDAO(this);
        RecyclerView recyclerView = findViewById(R.id.rvDLFeedback);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        feedbackFireBaseDAO.getFeedbackByEmailSent(recyclerView);
        btnCancel = findViewById(R.id.btnDLFCancel);
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void cancel(){
        Intent i = new Intent(DListFeedbackActivity.this, DoctorActivity.class);
        startActivity(i);
    }
}