package com.example.datlichkhambenh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.firebase.auth.FirebaseAuth;

public class DoctorActivity extends AppCompatActivity {
    Button btnListMEF, btnLogout, btnInformation, btnListFeedback, btnEvaluate;
    private FirebaseAuth auth;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_doctor);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        auth = FirebaseAuth.getInstance();
        btnLogout = findViewById(R.id.btnDLogout);
        btnListMEF = findViewById(R.id.btnDListMedicalExaminationForm);
        btnInformation = findViewById(R.id.btnDMEInfor);
        btnListFeedback = findViewById(R.id.btnDListFeedback);
        btnEvaluate = findViewById(R.id.btnDEvaluate);
        btnListMEF.setOnClickListener(v -> listMedicalExaminationForm());
        btnLogout.setOnClickListener(v -> logout());
        btnInformation.setOnClickListener(v -> createInfor());
        btnListFeedback.setOnClickListener(v -> listFeedback());
        btnEvaluate.setOnClickListener(v -> listEvaluate());
    }
    private void listMedicalExaminationForm(){
        Intent i = new Intent(DoctorActivity.this, DListProfileActivity.class);
        startActivity(i);
    }
    private void logout(){
        auth.signOut();
        Intent intent = new Intent(DoctorActivity.this, LoginActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }
    private void createInfor(){
        Intent i = new Intent(DoctorActivity.this, EnterInformationActivity.class);
        startActivity(i);
    }
    private void listFeedback(){
        Intent i = new Intent(DoctorActivity.this, DListFeedbackActivity.class);
        startActivity(i);
    }
    private void listEvaluate(){
        Intent i = new Intent(DoctorActivity.this, ListEvaluateActivity.class);
        startActivity(i);
    }
}