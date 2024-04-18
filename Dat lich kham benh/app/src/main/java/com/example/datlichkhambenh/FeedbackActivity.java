package com.example.datlichkhambenh;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.datlichkhambenh.dao.FeedbackFireBaseDAO;
import com.example.datlichkhambenh.model.Feedback;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class FeedbackActivity extends AppCompatActivity {
    Button btnFeedback, btnCancel;
    EditText txtFullName, txtEmailSent, txtContent, txtEmailDoctor;
    Spinner spDoctor;
    private FirebaseFirestore db;
    private FeedbackFireBaseDAO feedbackFireBaseDAO;
    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_feedback);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        db = FirebaseFirestore.getInstance();
        btnFeedback = findViewById(R.id.btnFFeedback);
        btnCancel = findViewById(R.id.btnFCancel);
        spDoctor = findViewById(R.id.spFDoctor);
        txtFullName = findViewById(R.id.txtFFullName);
        txtEmailSent = findViewById(R.id.txtFEmail);
        txtEmailDoctor = findViewById(R.id.txtFEmailDoctor);
        txtContent = findViewById(R.id.txtFContent);
        loadDoctors();
        btnFeedback.setOnClickListener(v -> feedback());
        btnCancel.setOnClickListener(v -> cancel());
    }
    private void loadDoctors() {
        db.collection("Doctor")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            List<String> doctorsList = new ArrayList<>();
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                String doctorName = document.getString("fullName");
                                doctorsList.add(doctorName);
                            }
                            ArrayAdapter<String> adapter = new ArrayAdapter<>(FeedbackActivity.this,
                                    android.R.layout.simple_spinner_item, doctorsList);
                            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                            spDoctor.setAdapter(adapter);
                        } else {
                            Toast.makeText(FeedbackActivity.this, "Lỗi khi tải danh sách bác sĩ", Toast.LENGTH_SHORT).show();
                        }
                    }
                });
    }
    private void feedback(){
        String fullName, emailSent, content, doctor, emailDoctor;
        fullName = txtFullName.getText().toString();
        emailSent = txtEmailSent.getText().toString().trim();
        emailDoctor = txtEmailDoctor.getText().toString().trim();
        doctor = spDoctor.getSelectedItem().toString();
        content = txtContent.getText().toString();
        Feedback newFeedback = new Feedback();
        newFeedback.setFullName(fullName);
        newFeedback.setEmailSent(emailSent);
        newFeedback.setEmailDoctor(emailDoctor);
        newFeedback.setDoctor(doctor);
        newFeedback.setContent(content);
        feedbackFireBaseDAO = new FeedbackFireBaseDAO(FeedbackActivity.this);
        feedbackFireBaseDAO.insert(newFeedback);
        Toast.makeText(getApplicationContext(), "Phản hồi thành công!", Toast.LENGTH_SHORT).show();
        Intent i = new Intent(FeedbackActivity.this, MainActivity.class);
        startActivity(i);
    }
    private void cancel(){
        Intent i = new Intent(FeedbackActivity.this, MainActivity.class);
        startActivity(i);
    }
}